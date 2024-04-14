package api.tech.hub.techhubapi.service.avaliacao.dto;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.service.arquivo.ArquivoService;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import api.tech.hub.techhubapi.service.arquivo.ftp.FtpService;

public record AvaliacaoDetalhadoDto(
      int id,
      String nomePerfil,
      Integer idAvaliador,
      String avaliador,
      String urlFotoPerfil,
      String pais,
      String comentario,
      Integer qtdEstrela
) {

    public AvaliacaoDetalhadoDto(Avaliacao avaliacao, FtpService ftpService) {

        this(
              avaliacao.getId(),
              avaliacao.getPerfil().getUsuario().getNome(),
              avaliacao.getAvaliador().getId(),
              avaliacao.getAvaliador().getUsuario().getNome(),
              ftpService.getArquivoUrl(
                    ArquivoService.getArquivoOfPerfil(avaliacao.getAvaliador().getUsuario().getPerfil(),
                          TipoArquivo.PERFIL), false),
              avaliacao.getAvaliador().getUsuario().getPais(),
              avaliacao.getComentario(), avaliacao.getQtdEstrela());
    }
}
