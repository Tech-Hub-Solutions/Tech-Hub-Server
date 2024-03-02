package api.tech.hub.techhubapi.service.email.dto;

import jakarta.validation.constraints.NotBlank;

public record EmailDto(
        @NotBlank
        String destinatario,
        @NotBlank
        String assunto,
        @NotBlank
        String conteudo
) {
}
