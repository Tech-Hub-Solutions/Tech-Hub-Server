package api.tech.hub.techhubapi.service.arquivo;

import api.tech.hub.techhubapi.entity.Arquivo;
import api.tech.hub.techhubapi.entity.conversa.Mensagem;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.repository.ArquivoRepository;
import api.tech.hub.techhubapi.repository.UsuarioRepository;
import api.tech.hub.techhubapi.service.arquivo.ftp.FtpService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ArquivoService {

    private final ArquivoRepository arquivoRepository;
    private final UsuarioRepository usuarioRepository;
    private final FtpService ftpService;

    public Arquivo getArquivo(Integer id) {
        Optional<Arquivo> arquivoOptional = arquivoRepository.findById(id);

        if (arquivoOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return arquivoOptional.get();
    }

    public Arquivo getArquivo(Integer idUsuario, TipoArquivo tipoArquivo) {
        Usuario usuario = this.usuarioRepository.findById(idUsuario)
              .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                          "Usuário não encontrado")
              );

        Perfil perfil = usuario.getPerfil();

        if (perfil == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado");
        }

        return perfil.getArquivos().stream()
              .filter(arq -> arq.getTipoArquivo().equals(tipoArquivo))
              .findFirst()
              .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                          "Arquivo não encontrado")
              );
    }


    public Arquivo salvarArquivo(Arquivo arquivo) {
        return this.arquivoRepository.save(arquivo);
    }

    public String criarUrlArquivo(Perfil perfil, TipoArquivo tipoArquivo, boolean download) {
        if (perfil == null) {
            return null;
        }

        List<Arquivo> arquivos = perfil.getArquivos();
        Optional<Arquivo> arquivo = arquivos.stream()
              .filter(a -> a.getTipoArquivo().equals(tipoArquivo))
              .findFirst();

        if (arquivo.isEmpty()) {
            return null;
        }

        return ftpService.getArquivoUrl(arquivo.get().getId(), download);

    }

    public Resource gerarCsvConversa(List<Mensagem> mensagens, List<Usuario> usuarios) {
        FileWriter arquivo = null;
        Formatter saida = null;

        File tempFile = null;

        try {
            tempFile = File.createTempFile("conversa", ".csv");
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String nomeArq = tempFile.getAbsolutePath();

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
                      mensagem.getArquivos().isEmpty() ? ""
                            : mensagem.getArquivos().get(0).getNomeArquivoOriginal(),
                      mensagem.getArquivos().isEmpty() ? ""
                            : mensagem.getArquivos().get(0).getTipoArquivo().toString()
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

    public static Integer getArquivoOfPerfil(Perfil perfil, TipoArquivo tipoArquivo) {
        if(Objects.isNull(perfil)) {
            return null;
        }
        return perfil.getArquivos()
              .stream()
              .filter(arquivo -> arquivo.getTipoArquivo().equals(tipoArquivo))
              .findFirst()
              .map(Arquivo::getId)
              .orElse(null);
    }


}
