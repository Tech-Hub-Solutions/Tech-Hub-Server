package api.tech.hub.techhubapi.service.metricausuario.dto;

import java.util.List;

public record MetricaUsuarioResponseDto(
        Integer usuarioId,
        List<EmpresaInteressadaDto> empresasInteressadas,
        Integer qtdVisualizacoesPerfilSemanal,
        Integer qtdRecomendacoes
) {
}
