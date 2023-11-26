package api.tech.hub.techhubapi.service.projeto;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.Projeto;
import api.tech.hub.techhubapi.repository.PerfilRepository;
import api.tech.hub.techhubapi.repository.ProjetoRepository;
import api.tech.hub.techhubapi.service.projeto.dto.projetoCriacaoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProjetoService {
    private final ProjetoMapper mapper;
    private final ProjetoRepository projetorepository;
    private final PerfilRepository perfilRepository;

    public Projeto cadastrarProjeto(projetoCriacaoDto dto) {
        Projeto novoProjeto = this.mapper.of(dto);

        novoProjeto.setPerfil(this.perfilRepository.findById(dto.perfil().getId())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado")
                ));

        return this.projetorepository.save(novoProjeto);
    }
}
