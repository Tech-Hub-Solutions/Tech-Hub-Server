package api.tech.hub.techhubapi.service.perfil.dto;

import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.entity.perfil.flag.FlagUsuario;
import api.tech.hub.techhubapi.service.flag.dto.FlagDto;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Null;

import java.util.List;

public record PerfilCadastroDto(
        @Nullable
        String sobreMim,
        @Nullable
        String experiencia,
        @Nullable
        String descricao,
        @DecimalMin("0.0")
        Double precoMedio,
        @Nullable
        String nomeGithub,
        @Nullable
        String linkGithub,
        @Nullable
        String linkLinkedin,
        List<Flag> flagList
) {
}

