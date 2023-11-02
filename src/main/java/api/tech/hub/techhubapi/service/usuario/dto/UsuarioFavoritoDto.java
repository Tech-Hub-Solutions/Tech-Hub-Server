package api.tech.hub.techhubapi.service.usuario.dto;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.flag.FlagUsuario;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.arquivo.ArquivoService;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import api.tech.hub.techhubapi.service.flag.dto.FlagDto;

import java.util.List;

public record UsuarioFavoritoDto(
        Integer id,
        String nome,
        String descricao,
        Double qtdEstrela,
        Double precoMedio,
        String urlFotoPerfil,
        List<FlagDto> flags
) {

    public UsuarioFavoritoDto(Perfil perfil) {
        this(
                perfil.getUsuario().getId(),
                perfil.getUsuario().getNome(),
                perfil.getDescricao(),
                calcularEstrelasMedia(perfil.getAvaliacaoList()),
                perfil.getPrecoMedio(),
                ArquivoService.criarUrlFoto(perfil, TipoArquivo.PERFIL),
                criarListaFlags(perfil.getFlagUsuarioList())
        );
    }


    private static Double calcularEstrelasMedia(List<Avaliacao> avaliacaoList) {
        return avaliacaoList.stream()
                .mapToDouble(Avaliacao::getQtdEstrela)
                .average()
                .orElse(0.0);
    }

    private static List<FlagDto> criarListaFlags(List<FlagUsuario> flagUsuarios) {
        return flagUsuarios.stream()
                .map(FlagUsuario::getFlag)
                .map(FlagDto::new)
                .toList();
    }
}
