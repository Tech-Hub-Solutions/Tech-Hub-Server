package api.tech.hub.techhubapi.service.avaliacao.dto;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.service.arquivo.ArquivoService;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import jakarta.persistence.ManyToOne;

public record AvaliacaoDetalhadoDto(
        int id,
        String nomePerfil,
        String avaliador,
        String urlFotoPerfil,
        String pais,
        String comentario,
        Integer qtdEstrela
) {

    public AvaliacaoDetalhadoDto(Avaliacao avaliacao) {
        this(
                avaliacao.getId(),
                avaliacao.getPerfil().getUsuario().getNome(),
                avaliacao.getAvaliador().getUsuario().getNome(),
                ArquivoService.criarUrlFoto(avaliacao.getAvaliador(), TipoArquivo.PERFIL),
                avaliacao.getAvaliador().getUsuario().getPais(),
                avaliacao.getComentario(), avaliacao.getQtdEstrela());
    }
}
