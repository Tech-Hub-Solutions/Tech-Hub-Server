package api.tech.hub.techhubapi.service.projeto;

import api.tech.hub.techhubapi.entity.perfil.Projeto;
import api.tech.hub.techhubapi.service.projeto.dto.projetoCriacaoDto;
import org.springframework.stereotype.Component;

@Component
public class ProjetoMapper {
    public Projeto of(projetoCriacaoDto dto) {
        Projeto projeto = new Projeto();

        projeto.setTitulo(dto.titulo());
        projeto.setDescricao(dto.descricao());
        projeto.setLinkAcesso(dto.linkAcesso());
        projeto.setPathImage(dto.pathImage());

        return projeto;
    }
}
