package api.tech.hub.techhubapi.service.projeto.dto;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record projetoCriacaoDto(
        @NotNull
        Perfil perfil,
        @NotBlank
        String titulo,
        @NotBlank
        String descricao,
        @NotBlank
        String pathImage,
        @NotBlank
        String linkAcesso
) {
}
