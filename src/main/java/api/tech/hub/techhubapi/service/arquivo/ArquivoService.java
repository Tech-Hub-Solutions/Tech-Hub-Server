package api.tech.hub.techhubapi.service.arquivo;

import api.tech.hub.techhubapi.entity.conversa.Arquivo;
import api.tech.hub.techhubapi.repository.ArquivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArquivoService {

    private Path diretorioBase = Path.of(System.getProperty("user.dir") + "/arquivos");
    private final ArquivoRepository arquivoRepository;

    public void salvarArquivo(MultipartFile arquivo, TipoArquivo tipo) {

        if (arquivo.isEmpty()) {
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
        arquivoRepository.save(newArquivo);
    }

    private String gerarNomeArquivo(String nomeOriginal) {
        return String.format("%s_%s", UUID.randomUUID(), nomeOriginal);
    }


}
