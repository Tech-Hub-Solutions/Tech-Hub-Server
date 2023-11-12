package api.tech.hub.techhubapi.service.referencia.dto;

import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;

public record ReferenciaDetalhadoDto(
        Integer idReferencia,
        String nomeAvaliador,
        String nomeAvaliado,
        Boolean isFavoritado,
        Boolean isRecomendado
) {

    public ReferenciaDetalhadoDto(ReferenciaPerfil referenciaPerfil) {
        this(
                referenciaPerfil.getId(),
                referenciaPerfil.getAvaliador().getUsuario().getNome(),
                referenciaPerfil.getAvaliado().getUsuario().getNome(),
                referenciaPerfil.isFavorito(), referenciaPerfil.isRecomendado());
    }
}
