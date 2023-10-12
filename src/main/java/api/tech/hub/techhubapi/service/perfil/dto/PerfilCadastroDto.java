package api.tech.hub.techhubapi.service.perfil.dto;

import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.entity.perfil.flag.FlagUsuario;
import jakarta.validation.constraints.DecimalMin;

import java.util.List;

public record PerfilCadastroDto(
         String sobreMim,
         String experiencia,
         String descricao,
         String pathPerfilImage,
         String pathWallpaperImage,
         @DecimalMin("0.0")
         Double precoMedio,
         String linkGithub,
         String linkLinkedin,
         List<Flag> flagList
) {
}

