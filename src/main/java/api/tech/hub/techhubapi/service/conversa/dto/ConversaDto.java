package api.tech.hub.techhubapi.service.conversa.dto;


import api.tech.hub.techhubapi.entity.conversa.Conversa;
import api.tech.hub.techhubapi.entity.conversa.Mensagem;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ConversaDto {
    private String roomCode;
    private UsuarioConversaDto usuario;
    private MensagemASerEnviadaDto mensagem;

    public ConversaDto(Conversa conversa) {
        this.roomCode = conversa.getSala().getRoomCode();
        this.usuario = new UsuarioConversaDto(conversa.getUsuario());
        this.mensagem = null;
    }

    public ConversaDto(Conversa conversa, Mensagem mensagem) {
        this(conversa.getSala().getRoomCode(), new UsuarioConversaDto(conversa.getUsuario()), new MensagemASerEnviadaDto(mensagem));
    }
}
