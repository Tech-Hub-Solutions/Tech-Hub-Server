package api.tech.hub.techhubapi.service.referencia.dto;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

public record ReferenciaPerfilCriacaoDto(

        @NotNull
        Perfil avaliador,
        @NotNull
        Perfil avaliado,
        @NotNull
        boolean isFavorito,
        @NotNull
        boolean isRecomendado
) {
}
