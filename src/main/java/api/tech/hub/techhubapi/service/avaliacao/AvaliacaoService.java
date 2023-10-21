package api.tech.hub.techhubapi.service.avaliacao;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.repository.AvaliacaoRepository;
import api.tech.hub.techhubapi.repository.PerfilRepository;
import api.tech.hub.techhubapi.repository.UsuarioRepository;
import api.tech.hub.techhubapi.service.avaliacao.dto.AvaliacaoDetalhadoDto;
import api.tech.hub.techhubapi.service.avaliacao.dto.avaliacaoDto;
import api.tech.hub.techhubapi.service.perfil.PerfilService;
import api.tech.hub.techhubapi.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoMapper mapper;
    private final AvaliacaoRepository AvaliacaoRepository;
    private final PerfilRepository perfilRepository;

    public AvaliacaoDetalhadoDto avaliar(avaliacaoDto dto, Integer idUsuario) {
        Avaliacao novaAvaliacao = this.mapper.of(dto);

        Perfil perfil = this.perfilRepository.encontrarPerfilPorIdUsuario(idUsuario).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado")
        );

        novaAvaliacao.setPerfil(perfil);

        return this.mapper.dtoOf(this.AvaliacaoRepository.save(novaAvaliacao));
    }

    public List<AvaliacaoDetalhadoDto> encontrarAvaliacoesPerfil(Integer idUsuario) {
        Perfil perfil = this.perfilRepository.encontrarPerfilPorIdUsuario(idUsuario).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado")
        );

        List<Avaliacao> listaAvaliacoes = this.AvaliacaoRepository.findAvaliacaoByPerfil(perfil);

        if(listaAvaliacoes.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Perfil não possui avaliações");
        }

        return listaAvaliacoes.stream().map(mapper::dtoOf).toList();
    }

}
