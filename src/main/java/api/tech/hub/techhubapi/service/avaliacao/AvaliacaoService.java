package api.tech.hub.techhubapi.service.avaliacao;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.repository.AvaliacaoRepository;
import api.tech.hub.techhubapi.repository.PerfilRepository;
import api.tech.hub.techhubapi.service.avaliacao.dto.avaliacaoDto;
import api.tech.hub.techhubapi.service.perfil.PerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoMapper mapper;
    private final AvaliacaoRepository repository;
    private final PerfilRepository perfilRepository;

    public Avaliacao validarDtoAvaliacao(avaliacaoDto dto) {
        Avaliacao novaAvaliacao = this.mapper.of(dto);

        novaAvaliacao.setPerfil(this.perfilRepository.findById(dto.perfil().getId())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Perfil não encontrado")
                ));

        return this.repository.save(novaAvaliacao);
    }
}
