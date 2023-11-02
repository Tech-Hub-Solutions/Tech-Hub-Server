package api.tech.hub.techhubapi.service.usuario.dto;

import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import jakarta.annotation.Nullable;

import java.util.List;

public record UsuarioFiltroDto(
        @Nullable
        String nome,
        @Nullable
        String area,
        @Nullable
        List<Flag> tecnologias,
        @Nullable
        Double precoMax,
        @Nullable
        Double precoMin
) {
}
