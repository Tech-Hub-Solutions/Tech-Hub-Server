package api.tech.hub.techhubapi.service.perfil;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;
import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.entity.perfil.flag.FlagUsuario;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.arquivo.ArquivoService;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import api.tech.hub.techhubapi.service.avaliacao.AvaliacaoMapper;
import api.tech.hub.techhubapi.service.flag.FlagUsuarioMapper;
import api.tech.hub.techhubapi.service.flag.dto.FlagDto;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilCadastroDto;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilDetalhadoDto;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilGeralDetalhadoDto;
import api.tech.hub.techhubapi.service.referencia.ReferenciaPerfilMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PerfilMapper {
    private final AvaliacaoMapper avaliacaoMapper;
    private final ReferenciaPerfilMapper referenciaPerfilMapper;
    private final FlagUsuarioMapper flagUsuarioMapper;

    public Perfil of(Perfil perfil, PerfilCadastroDto dto) {

        perfil.setSobreMim(dto.sobreMim());
        perfil.setExperiencia(dto.experiencia());
        perfil.setDescricao(dto.descricao());
        perfil.setPrecoMedio(dto.precoMedio());
        perfil.setLinkGithub(dto.linkGithub());
        perfil.setLinkLinkedin(dto.linkLinkedin());

        return perfil;
    }


    public PerfilDetalhadoDto dtoOf(Perfil perfil) {
        PerfilDetalhadoDto dto = new PerfilDetalhadoDto();

        dto.setId(perfil.getId());
        dto.setUrlFotoPerfil(ArquivoService.criarUrlFoto(perfil, TipoArquivo.PERFIL));
        dto.setUrlFotoWallpaper(ArquivoService.criarUrlFoto(perfil, TipoArquivo.WALLPAPER));
        dto.setSobreMim(perfil.getSobreMim());
        dto.setExperiencia(perfil.getExperiencia());
        dto.setDescricao(perfil.getDescricao());
        dto.setPrecoMedio(perfil.getPrecoMedio());
        dto.setNomeGithub(perfil.getNomeGithub());
        dto.setLinkGithub(perfil.getLinkGithub());
        dto.setLinkLinkedin(perfil.getLinkLinkedin());
        dto.setFlags(this.flagUsuarioMapper.retornarFlagList(perfil.getFlagUsuarioList()).stream().map(FlagDto::new).toList());
        dto.setAvaliacoes(this.avaliacaoMapper.retornarListaAvaliacoesDto(perfil.getAvaliacaoList()));
        dto.setReferencias(this.referenciaPerfilMapper.retornarListaReferenciasDto(perfil.getReferenciaPerfilList()));

        return dto;
    }

    public PerfilGeralDetalhadoDto perfilGeralDtoOf(Usuario usuario, Perfil perfil){
        return new PerfilGeralDetalhadoDto(
                usuario.getId(),
                perfil.getId(),
                ArquivoService.criarUrlFoto(perfil, TipoArquivo.PERFIL),
                ArquivoService.criarUrlFoto(perfil, TipoArquivo.WALLPAPER),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPais(),
                usuario.getFuncao(),
                perfil.getSobreMim(),
                perfil.getExperiencia(),
                perfil.getDescricao(),
                perfil.getPrecoMedio(),
                perfil.getNomeGithub(),
                perfil.getLinkGithub(),
                perfil.getLinkLinkedin(),
                this.flagUsuarioMapper.retornarFlagList(perfil.getFlagUsuarioList()).stream().map(FlagDto::new).toList(),
                this.avaliacaoMapper.retornarListaAvaliacoesDto(perfil.getAvaliacaoList()),
                this.referenciaPerfilMapper.retornarListaReferenciasDto(perfil.getReferenciaPerfilList())
        );
    }
}
