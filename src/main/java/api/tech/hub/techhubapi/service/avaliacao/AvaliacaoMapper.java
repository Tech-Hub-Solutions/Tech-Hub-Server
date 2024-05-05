package api.tech.hub.techhubapi.service.avaliacao;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.entity.perfil.flag.FlagUsuario;
import api.tech.hub.techhubapi.service.arquivo.ftp.FtpService;
import api.tech.hub.techhubapi.service.avaliacao.dto.AvaliacaoDetalhadoDto;
import api.tech.hub.techhubapi.service.avaliacao.dto.AvaliacaoTotal;
import api.tech.hub.techhubapi.service.avaliacao.dto.avaliacaoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AvaliacaoMapper {
    private final FtpService ftpService;
    public Avaliacao of(avaliacaoDto dto) {
        Avaliacao novaAvaliacao = new Avaliacao();

        novaAvaliacao.setComentario(dto.comentario());
        novaAvaliacao.setQtdEstrela(dto.qtdEstrela());

        return novaAvaliacao;
    }

    public AvaliacaoDetalhadoDto dtoOf(Avaliacao avaliacao) {
        return new AvaliacaoDetalhadoDto(avaliacao, ftpService);
    }
}
