package api.tech.hub.techhubapi.service.usuario.dto;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.arquivo.ArquivoService;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;

import api.tech.hub.techhubapi.service.arquivo.ftp.FtpService;
import java.util.List;

public record UsuarioBuscaDto(
      Integer id,
      String nome,
      String descricao,
      Double qtdEstrela,
      Double precoMedio,
      String urlFotoPerfil
) {

    public UsuarioBuscaDto(Usuario usuario, FtpService ftpService) {
        this(
              usuario.getId(),
              usuario.getNome(),
              usuario.getPerfil().getDescricao(),
              calcularEstrelasMedia(usuario.getPerfil().getAvaliacaoList()),
              usuario.getPerfil().getPrecoMedio(),
              ftpService.getArquivoUrl(
                    ArquivoService.getArquivoOfPerfil(usuario.getPerfil(), TipoArquivo.PERFIL),
                    false)
        );

    }


    private static Double calcularEstrelasMedia(List<Avaliacao> avaliacaoList) {
        return avaliacaoList.stream()
              .mapToDouble(Avaliacao::getQtdEstrela)
              .average()
              .orElse(0.0);
    }
}
