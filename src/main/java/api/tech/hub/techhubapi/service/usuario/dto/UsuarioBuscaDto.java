package api.tech.hub.techhubapi.service.usuario.dto;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.arquivo.ArquivoService;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;

import java.util.List;

public record UsuarioBuscaDto(
        Integer id,
        String nome,
        String descricao,
        Double qtdEstrela,
        Double precoMedio,
        String urlFotoPerfil
) {
    public UsuarioBuscaDto (Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getPerfil().getDescricao(),
                calcularEstrelasMedia(usuario.getPerfil().getAvaliacaoList()),
                usuario.getPerfil().getPrecoMedio(),
                ArquivoService.criarUrlArquivo(usuario.getPerfil(), TipoArquivo.PERFIL)
        );
    }


    private static Double calcularEstrelasMedia(List<Avaliacao> avaliacaoList) {
        return avaliacaoList.stream()
                .mapToDouble(Avaliacao::getQtdEstrela)
                .average()
                .orElse(0.0);
    }
}
