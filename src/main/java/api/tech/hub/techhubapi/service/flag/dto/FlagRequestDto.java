package api.tech.hub.techhubapi.service.flag.dto;

import jakarta.validation.constraints.NotBlank;

public record FlagRequestDto(
        @NotBlank
        String nome,
        @NotBlank
        String area,
        @NotBlank
        String categoria
) {
}
