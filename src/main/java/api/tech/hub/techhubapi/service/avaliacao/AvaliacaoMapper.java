package api.tech.hub.techhubapi.service.avaliacao;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.service.avaliacao.dto.avaliacaoDto;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoMapper {
    public Avaliacao of(avaliacaoDto dto) {
        Avaliacao novaAvaliacao = new Avaliacao();

        novaAvaliacao.setComentario(dto.comentario());
        novaAvaliacao.setQtdEstrela(dto.qtdEstrela());

        return novaAvaliacao;
    }
}
