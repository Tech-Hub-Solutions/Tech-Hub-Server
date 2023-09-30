package api.tech.hub.techhubapi.service.conversa.dto;

import api.tech.hub.techhubapi.entity.conversa.Mensagem;

import java.time.LocalDateTime;

public record MensagemASerEnviadaDto(
        Integer idUsuario,
        String texto,
        LocalDateTime dtHora
) {
    public MensagemASerEnviadaDto(Mensagem mensagem) {
        this(mensagem.getUsuario().getId(), mensagem.getTexto(), mensagem.getDtMensagem());
    }
}
