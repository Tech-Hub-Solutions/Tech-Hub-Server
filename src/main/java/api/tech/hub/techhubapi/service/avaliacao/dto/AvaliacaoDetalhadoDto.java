package api.tech.hub.techhubapi.service.avaliacao.dto;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import jakarta.persistence.ManyToOne;

public record AvaliacaoDetalhadoDto (int id, String nomePerfil, String avaliador, String comentario, Integer qtdEstrela) {

    public AvaliacaoDetalhadoDto (Avaliacao avaliacao) {
        this(avaliacao.getId(), avaliacao.getPerfil().getUsuario().getNome(), avaliacao.getAvaliador().getUsuario().getNome(),
                avaliacao.getComentario(), avaliacao.getQtdEstrela());
    }
}
