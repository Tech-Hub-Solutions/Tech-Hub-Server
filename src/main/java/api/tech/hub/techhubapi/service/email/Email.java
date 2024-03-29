package api.tech.hub.techhubapi.service.email;

import jakarta.validation.constraints.NotBlank;

public record Email(
        @NotBlank
        String destinatario,
        @NotBlank
        String assunto,
        @NotBlank
        String conteudo
) {
}
