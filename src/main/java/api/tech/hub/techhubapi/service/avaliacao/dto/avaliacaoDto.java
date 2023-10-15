package api.tech.hub.techhubapi.service.avaliacao.dto;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record avaliacaoDto(

        Perfil perfil,
        @NotBlank
        String comentario,
        @NotNull
        @Min(0)
        @Max(5)
        Integer qtdEstrela
) {
}
