package api.tech.hub.techhubapi.service.perfil;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;
import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.entity.perfil.flag.FlagUsuario;
import api.tech.hub.techhubapi.service.avaliacao.AvaliacaoMapper;
import api.tech.hub.techhubapi.service.flag.FlagUsuarioMapper;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilCadastroDto;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilDetalhadoDto;
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


    public PerfilDetalhadoDto dtoOf(Perfil perfil, List<Flag> flagList, List<Avaliacao> avaliacaoDetalhadoDtoList, List<ReferenciaPerfil> referenciaDetalhadoDtoList) {
        PerfilDetalhadoDto dto = new PerfilDetalhadoDto();

        dto.setId(perfil.getId());
        dto.setSobreMim(perfil.getSobreMim());
        dto.setExperiencia(perfil.getExperiencia());
        dto.setDescricao(perfil.getDescricao());
        dto.setPrecoMedio(perfil.getPrecoMedio());
        dto.setNomeGithub(perfil.getNomeGithub());
        dto.setLinkGithub(perfil.getLinkGithub());
        dto.setLinkLinkedin(perfil.getLinkLinkedin());

        if (flagList.isEmpty()) {
            dto.setFlags(null);
        } else {
            dto.setFlags(flagList);
        }

        if (avaliacaoDetalhadoDtoList.isEmpty()) {
            dto.setAvaliacoes(null);
        } else {
            dto.setAvaliacoes(this.avaliacaoMapper.retornarListaAvaliacoesDto(avaliacaoDetalhadoDtoList));
        }

        if (referenciaDetalhadoDtoList.isEmpty()) {
            dto.setReferencias(null);
        } else {
            dto.setReferencias(this.referenciaPerfilMapper.retornarListaReferenciasDto(referenciaDetalhadoDtoList));
        }

        return dto;
    }
}
