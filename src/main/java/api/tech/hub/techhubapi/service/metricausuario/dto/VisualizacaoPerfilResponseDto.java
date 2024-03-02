package api.tech.hub.techhubapi.service.metricausuario.dto;

import api.tech.hub.techhubapi.entity.VisualizacaoPerfil;

import java.time.LocalDateTime;

public record VisualizacaoPerfilResponseDto (
        Integer id,
        LocalDateTime dtHora,
        Integer usuarioId,
        Integer perfilId
) {
    public VisualizacaoPerfilResponseDto (VisualizacaoPerfil visualizacaoPerfil){
        this(
                visualizacaoPerfil.getId(),
                visualizacaoPerfil.getDtHora(),
                visualizacaoPerfil.getUsuario().getId(),
                visualizacaoPerfil.getPerfil().getId()
        );
    }
}
