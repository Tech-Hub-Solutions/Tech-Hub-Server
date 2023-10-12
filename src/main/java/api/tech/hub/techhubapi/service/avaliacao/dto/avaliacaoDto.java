package api.tech.hub.techhubapi.service.avaliacao.dto;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record avaliacaoDto(
        @NotNull
        Perfil perfil,
        @NotBlank
        String comentario,
        @NotNull
        @Min(0)
        Integer qtdEstrela
) {
}
