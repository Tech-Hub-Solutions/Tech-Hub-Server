package br.com.techhub.server.techub.api.entity.avaliacao;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAvaliacaoDto(
        @NotNull
        @Max(value = 5,message = "Valor deve estar entre 0 e 5")
        @Min(value = 0, message = "Valor deve estar entre 0 e 5")
        int nota,
        @NotBlank
        String comentario

) {
}
