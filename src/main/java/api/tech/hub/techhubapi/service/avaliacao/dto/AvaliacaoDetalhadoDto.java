package api.tech.hub.techhubapi.service.avaliacao.dto;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import jakarta.persistence.ManyToOne;

public record AvaliacaoDetalhadoDto (int id, String comentario, Integer qtdEstrela) {

    public AvaliacaoDetalhadoDto (Avaliacao avaliacao) {
        this(avaliacao.getId(), avaliacao.getComentario(), avaliacao.getQtdEstrela());
    }
}
