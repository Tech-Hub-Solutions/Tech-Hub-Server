package api.tech.hub.techhubapi.service.perfil;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.arquivo.ArquivoService;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import api.tech.hub.techhubapi.service.arquivo.ftp.FtpService;
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
    private final FtpService ftpService;

    public Perfil of(Perfil perfil, PerfilCadastroDto dto) {

        perfil.setSobreMim(dto.sobreMim());
        perfil.setExperiencia(dto.experiencia());
        perfil.setDescricao(dto.descricao());
        perfil.setPrecoMedio(dto.precoMedio());
        perfil.setLinkGithub(dto.linkGithub());
        perfil.setNomeGithub(dto.nomeGithub());
        perfil.setLinkLinkedin(dto.linkLinkedin());

        return perfil;
    }


    public PerfilDetalhadoDto dtoOf(Perfil perfil) {
        PerfilDetalhadoDto dto = new PerfilDetalhadoDto();

        dto.setId(perfil.getId());
        dto.setSobreMim(perfil.getSobreMim());
        dto.setExperiencia(perfil.getExperiencia());
        dto.setDescricao(perfil.getDescricao());
        dto.setPrecoMedio(perfil.getPrecoMedio());
        dto.setNomeGithub(perfil.getNomeGithub());
        dto.setLinkGithub(perfil.getLinkGithub());
        dto.setLinkLinkedin(perfil.getLinkLinkedin());
        dto.setFlags(this.flagUsuarioMapper.retornarFlagList(perfil.getFlagUsuarioList()).stream()
              .map(FlagDto::new).toList());

        return dto;
    }

    public PerfilGeralDetalhadoDto perfilGeralDtoOf(Usuario usuarioLogado, Perfil perfil) {
        Usuario usuario = perfil.getUsuario();
        List<ReferenciaPerfil> referenciaPerfilList = perfil.getReferenciaPerfilList();
        return new PerfilGeralDetalhadoDto(
              usuario.getId(),
              perfil.getId(),
              ftpService.getArquivoUrl(
                    ArquivoService.getArquivoOfPerfil(perfil, TipoArquivo.PERFIL), false),
              ftpService.getArquivoUrl(
                    ArquivoService.getArquivoOfPerfil(perfil, TipoArquivo.WALLPAPER), false),
              ftpService.getArquivoUrl(
                    ArquivoService.getArquivoOfPerfil(perfil, TipoArquivo.CURRICULO), false),
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
              this.flagUsuarioMapper.retornarFlagList(perfil.getFlagUsuarioList()).stream()
                    .map(FlagDto::new).toList(),
              this.referenciaPerfilMapper.retornarIsFavorito(usuarioLogado, referenciaPerfilList),
              this.referenciaPerfilMapper.retornarQtdFavoritos(usuarioLogado, referenciaPerfilList),
              this.referenciaPerfilMapper.retornarIsRecomendado(usuarioLogado,
                    referenciaPerfilList),
              this.referenciaPerfilMapper.retornarQtdRecomendacoes(usuarioLogado,
                    referenciaPerfilList)
        );
    }
}
