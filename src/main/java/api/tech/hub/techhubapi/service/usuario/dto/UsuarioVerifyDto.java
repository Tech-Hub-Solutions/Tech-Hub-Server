package api.tech.hub.techhubapi.service.usuario.dto;

import jakarta.validation.constraints.NotEmpty;

public record UsuarioVerifyDto(
        @NotEmpty
        String email,
        @NotEmpty
        String senha,
        @NotEmpty
        String code
) {
}
