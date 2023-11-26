package api.tech.hub.techhubapi.service.usuario.dto;

import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.service.flag.dto.FlagDto;
import jakarta.annotation.Nullable;

import java.util.List;

public record UsuarioFiltroDto(
        @Nullable
        String nome,
        @Nullable
        String area,
        @Nullable
        List<Integer> tecnologiasIds,
        @Nullable
        Double precoMax,
        @Nullable
        Double precoMin
) {
}
