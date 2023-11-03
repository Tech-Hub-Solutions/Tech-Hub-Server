package api.tech.hub.techhubapi.service.perfil.dto;

import api.tech.hub.techhubapi.entity.usuario.UsuarioFuncao;
import api.tech.hub.techhubapi.service.avaliacao.dto.AvaliacaoDetalhadoDto;
import api.tech.hub.techhubapi.service.flag.dto.FlagDto;
import api.tech.hub.techhubapi.service.referencia.dto.ReferenciaDetalhadoDto;

import java.util.List;

public record PerfilGeralDetalhadoDto(
        Integer idUsuario,
        Integer idPerfil,
        String nome,
        String email,
        String pais,
        UsuarioFuncao funcao,
        String sobreMim,
        String experiencia,
        String descricao,
        Double precoMedio,
        String nomeGithub,
        String linkGithub,
        String linkLinkedin,
        List<FlagDto> flags,
        List<AvaliacaoDetalhadoDto> avaliacoes,
        List<ReferenciaDetalhadoDto> referencias
) {
}
