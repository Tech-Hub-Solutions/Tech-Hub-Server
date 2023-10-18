package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.entity.Arquivo;
import api.tech.hub.techhubapi.entity.conversa.Mensagem;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.arquivo.ArquivoService;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import api.tech.hub.techhubapi.service.conversa.ConversaService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.List;

@RestController
@RequestMapping("/arquivos")
@RequiredArgsConstructor

public class ArquivoController {
    private final ArquivoService arquivoService;
    private final ConversaService conversaService;

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

    @GetMapping("/conversa/{room}/gerar-csv")
    public ResponseEntity<Resource> gerarCsvConversa(@PathVariable String room) {
        List<Mensagem> mensagens = this.conversaService.listarMensagensBanco(room);
        List<Usuario> usuarios = this.conversaService.listarUsuarios(room);

        Resource resource = this.arquivoService.gerarCsvConversa(mensagens, usuarios);
        String contentType = this.arquivoService.getContentType(resource);
        long fileSize;

        // Getting file size
        try {
            fileSize = resource.contentLength(); // It's important that resource should be able to provide this info
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }

        String nome = "Conversa-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss")) + ".csv";


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        + nome + "\"")
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(fileSize)) // Adding size to the headers
                .body(resource);
    }


}
