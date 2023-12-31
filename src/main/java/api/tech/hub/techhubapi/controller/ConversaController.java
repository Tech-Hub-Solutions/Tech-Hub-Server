package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import api.tech.hub.techhubapi.service.conversa.ConversaService;
import api.tech.hub.techhubapi.service.conversa.dto.ConversaDto;
import api.tech.hub.techhubapi.service.conversa.dto.MensagemASerEnviadaDto;
import api.tech.hub.techhubapi.service.conversa.dto.RoomCodeDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/conversas")
@RequiredArgsConstructor
public class
ConversaController {

    private final ConversaService conversaService;

    @GetMapping
    public ResponseEntity<List<ConversaDto>> carregarConversas() {
        List<ConversaDto> conversaDtos = this.conversaService.listarConversas();

        if (conversaDtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(conversaDtos);
    }

    @PostMapping("/iniciar/{id}")
    public ResponseEntity<RoomCodeDto> cadastrarSala(@PathVariable Integer id) {
        return ResponseEntity.ok(conversaService.iniciarConversa(id));
    }

    @PostMapping("/sala/{roomCode}")
    public ResponseEntity<Object> enviarMensagem(
            @PathVariable String roomCode,
            @RequestParam String mensagem,
            @RequestParam(required = false) MultipartFile arquivo,
            @RequestParam(required = false) TipoArquivo tipoArquivo) {

        this.conversaService.enviarMensagem(roomCode, mensagem, arquivo, tipoArquivo);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/sala/{roomCode}")
    public ResponseEntity<List<MensagemASerEnviadaDto>> getMensagens(@PathVariable String roomCode) {
        List<MensagemASerEnviadaDto> mensagens = this.conversaService.listarMensagens(roomCode);
        if (mensagens.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(mensagens);
    }

    @DeleteMapping("/sala/{roomCode}")
    public ResponseEntity<Object> apagarConversa(@PathVariable String roomCode) {
        this.conversaService.apagarConversa(roomCode);

        return ResponseEntity.noContent().build();
    }

}
