package api.tech.hub.techhubapi.service.referencia.dto;

import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;

public record ReferenciaDetalhadoDto(Integer idReferencia, String nomeAvaliador, String nomeAvaliado,
                                     Boolean isFavoritado, Boolean isRecomendado) {

    public ReferenciaDetalhadoDto (ReferenciaPerfil referenciaPerfil){
        this(referenciaPerfil.getId(),referenciaPerfil.getAvaliador().getNomeGithub(),
                referenciaPerfil.getAvaliado().getNomeGithub(),referenciaPerfil.isFavorito(),referenciaPerfil.isRecomendado());
    }
}
