package api.tech.hub.techhubapi.service.arquivo;

import api.tech.hub.techhubapi.entity.conversa.Arquivo;
import api.tech.hub.techhubapi.repository.ArquivoRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArquivoService {

    private Path diretorioBase = Path.of(System.getProperty("user.dir") + "/arquivos");
    private final ArquivoRepository arquivoRepository;

    public Arquivo salvarArquivoLocal(MultipartFile arquivo, TipoArquivo tipo) {

        if (arquivo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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

    public byte[] getImage(String nomeArquivo) {
        try {
            Path path = Path.of(this.diretorioBase + "/IMAGEM");
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
}
