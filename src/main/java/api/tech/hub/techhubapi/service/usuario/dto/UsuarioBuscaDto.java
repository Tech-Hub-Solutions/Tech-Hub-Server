package api.tech.hub.techhubapi.service.usuario.dto;

import api.tech.hub.techhubapi.entity.Arquivo;
import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import api.tech.hub.techhubapi.service.usuario.UsuarioService;

import java.util.List;

public record UsuarioBuscaDto(
        Integer id,
        String nome,
        Double qtdEstrela,
        Double precoMedio,
        String urlFotoPerfil
) {
    public UsuarioBuscaDto (Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                calcularEstrelasMedia(usuario.getPerfil().getAvaliacaoList()),
                usuario.getPerfil().getPrecoMedio(),
                UsuarioService.criarUrlFotoPerfil(usuario.getPerfil().getArquivos())
        );
    }


    private static Double calcularEstrelasMedia(List<Avaliacao> avaliacaoList) {
        return avaliacaoList.stream()
                .mapToDouble(Avaliacao::getQtdEstrela)
                .average()
                .orElse(0.0);
    }
}
