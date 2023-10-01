package api.tech.hub.techhubapi.service.conversa;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.conversa.dto.MensagemASerEnviadaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocketService {

    private final SimpMessagingTemplate template;

    public void enviarMensagem(String roomCode, MensagemASerEnviadaDto mensagemASerEnviadaDto) {
        String routeWebSocket = String.format("/topic/sala/%s", roomCode);
        template.convertAndSend(routeWebSocket, mensagemASerEnviadaDto);
    }

    public void iniciarConversa(Usuario usuarioAutenticado) {
        String routeWebSocket = String.format("/topic/usuario/%s", usuarioAutenticado.getId());
        template.convertAndSend(routeWebSocket);
    }
}
