package api.tech.hub.techhubapi.service.arquivo;

import api.tech.hub.techhubapi.entity.Arquivo;
import api.tech.hub.techhubapi.entity.conversa.Mensagem;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.repository.ArquivoRepository;
import api.tech.hub.techhubapi.repository.PerfilRepository;
import api.tech.hub.techhubapi.repository.UsuarioRepository;
import api.tech.hub.techhubapi.service.conversa.dto.MensagemASerEnviadaDto;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ArquivoService {

    private Path diretorioBase = Path.of(System.getProperty("user.dir") + "/arquivos");
    private final ArquivoRepository arquivoRepository;
    private final UsuarioRepository usuarioRepository;

    public Arquivo salvarArquivoLocal(MultipartFile arquivo, TipoArquivo tipo) {

        if (arquivo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (!diretorioBase.toFile().exists()) {
            diretorioBase.toFile().mkdir();
        }

        Path diretorioParaSalvar = Path.of(this.diretorioBase + "/" + tipo.toString());
        if (!diretorioParaSalvar.toFile().exists()) {
            diretorioParaSalvar.toFile().mkdir();
        }

        String nomeArquivoFormatado = gerarNomeArquivo(arquivo.getOriginalFilename());

        String diretorioFinal = diretorioParaSalvar + "/" + nomeArquivoFormatado;
        File destino = new File(diretorioFinal);

        try {
            arquivo.transferTo(destino);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(422, "Não foi possível salvar o arquivo", null);
        }

        Arquivo newArquivo = new Arquivo();
        newArquivo.setDataUpload(LocalDate.now());
        newArquivo.setNomeArquivoOriginal(arquivo.getOriginalFilename());
        newArquivo.setNomeArquivoSalvo(nomeArquivoFormatado);
        newArquivo.setTipoArquivo(tipo);
        return newArquivo;

    }

    private String gerarNomeArquivo(String nomeOriginal) {
        return String.format("%s_%s", UUID.randomUUID(), nomeOriginal);
    }

    public Arquivo getArquivo(Integer id) {
        Optional<Arquivo> arquivoOptional = arquivoRepository.findById(id);

        if (arquivoOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return arquivoOptional.get();
    }

    public Arquivo getArquivo(Integer idUsuario, TipoArquivo tipoArquivo) {
        Usuario usuario =  this.usuarioRepository.findById(idUsuario)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
                );

        Perfil perfil = usuario.getPerfil();

        if (perfil == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado");
        }

        return perfil.getArquivos().stream()
                .filter(arq -> arq.getTipoArquivo().equals(tipoArquivo))
                .findFirst()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Arquivo não encontrado")
                );
    }
    public byte[] getImage(String nomeArquivo, TipoArquivo tipoArquivo) {
        try {
            Path path = Path.of(this.diretorioBase + "/" + tipoArquivo.toString());
            Path filePath = Paths.get(path.toString(), nomeArquivo);
            return Files.readAllBytes(filePath);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found", e);
        }
    }

    public Resource getFile(String fileName) {
        try {
            Path path = Path.of(this.diretorioBase + "/DOCUMENTO");
            Path filePath = Paths.get(path.toString(), fileName);
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(422), "Erro ao ler o arquivo");
        }
    }

    public String getContentType(Resource resource) {
        try {
            return Files.probeContentType(Paths.get(resource.getURI()));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(422), "Erro ao determinar o tipo de arquivo");
        }
    }


    public Arquivo salvarArquivo(Arquivo arquivo) {
        return this.arquivoRepository.save(arquivo);
    }

    public static String criarUrlFoto(Perfil perfil, TipoArquivo tipoArquivo) {
        List<Arquivo> arquivos = perfil.getArquivos();

        String url = String.format("http://localhost:8080/arquivos/usuario/%d?tipoArquivo=%s",
                perfil.getUsuario().getId(),
                tipoArquivo.name()
        );

        return arquivos.stream()
                .filter(arquivo -> arquivo.getTipoArquivo().equals(TipoArquivo.PERFIL))
                .findFirst()
                .map(arquivo -> url)
                .orElse("");
    }

    public Resource gerarCsvConversa(List<Mensagem> mensagens, List<Usuario> usuarios) {
        FileWriter arquivo = null;
        Formatter saida = null;

        // Define the directory path
        String dirPath = "./arquivos/CSV";
        File directory = new File(dirPath);

        // Check if the directory exists, if not, create it
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Now define the file path with the directory
        String nomeArq = dirPath + "/" + mensagens.get(0).getSala().getRoomCode() + ".csv";

        boolean deuRuim = false;

        try {
            arquivo = new FileWriter(nomeArq);
            saida = new Formatter(arquivo);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        try {
            for (Usuario usuario : usuarios) {
                saida.format("C1;%d;%s;%s;%s;%s\n",
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getFuncao(),
                        usuario.getPais()
                );
            }

            for (Mensagem mensagem : mensagens) {
                saida.format("C2;%d;%d;%s;%s;%s;%s\n",
                        mensagem.getId(),
                        mensagem.getUsuario().getId(),
                        mensagem.getTexto(),
                        mensagem.getDtMensagem().toString(),
                        mensagem.getArquivos().isEmpty() ? "" : mensagem.getArquivos().get(0).getNomeArquivoOriginal(),
                        mensagem.getArquivos().isEmpty() ? "" : mensagem.getArquivos().get(0).getTipoArquivo().toString()
                );

            }
        } catch (FormatterClosedException e) {
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arquivo.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
        }

        if (deuRuim) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(422));
        }

        return new FileSystemResource(nomeArq);

    }

    public void deletarArquivo(Integer idArquivo, TipoArquivo tipoArquivo) {
        Arquivo arquivo = this.arquivoRepository.findById(idArquivo)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Arquivo não encontrado")
                );

        this.arquivoRepository.delete(arquivo);
        this.deletarArquivoLocal(arquivo);

    }

    private void deletarArquivoLocal(Arquivo arquivo) {
        Path path = Path.of(this.diretorioBase + "/" + arquivo.getTipoArquivo().toString());
        Path filePath = Paths.get(path.toString(), arquivo.getNomeArquivoSalvo());

        try {
            Files.delete(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(422, "Não foi possível deletar o arquivo", null);
        }
    }
}
