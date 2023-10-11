package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.entity.conversa.Arquivo;
import api.tech.hub.techhubapi.service.arquivo.ArquivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/arquivos")
@RequiredArgsConstructor

public class ArquivoController {
    private final ArquivoService arquivoService;

    //    @PostMapping("/arquivo")
//    public ResponseEntity<Object> arquivo(@RequestParam MultipartFile arquivo) {
//        this.arquivoService.salvarArquivo(arquivo, TipoArquivo.DOCUMENTO);
//        return ResponseEntity.noContent().build();
//    }


    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
        Arquivo arquivo = this.arquivoService.getArquivo(id);
        byte[] imagem = this.arquivoService.getImage(arquivo.getNomeArquivoSalvo());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + arquivo.getNomeArquivoOriginal());

        return ResponseEntity.status(200).headers(headers).body(imagem);

    }

    @GetMapping("/file/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable Integer id) {
        Arquivo arquivo = this.arquivoService.getArquivo(id);
        Resource resource = this.arquivoService.getFile(arquivo.getNomeArquivoSalvo());
        String contentType = this.arquivoService.getContentType(resource);
        long fileSize;
        // Getting file size
        try {

        fileSize = resource.contentLength(); // It's important that resource should be able to provide this info
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        + arquivo.getNomeArquivoOriginal() + "\"")
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(fileSize)) // Adding size to the headers
                .body(resource);
    }


}
