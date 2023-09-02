package br.com.techhub.server.techub.api.entity.avaliacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAvaliacaoDto(
        @NotNull
        int nota,
        @NotBlank
        String comentario

) {
}
