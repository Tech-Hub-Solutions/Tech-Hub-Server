package api.tech.hub.techhubapi.service.avaliacao;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.repository.AvaliacaoRepository;
import api.tech.hub.techhubapi.repository.PerfilRepository;
import api.tech.hub.techhubapi.service.avaliacao.dto.AvaliacaoDetalhadoDto;
import api.tech.hub.techhubapi.service.avaliacao.dto.AvaliacaoTotal;
import api.tech.hub.techhubapi.service.avaliacao.dto.avaliacaoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoMapper mapper;
    private final AvaliacaoRepository avaliacaoRepository;
    private final PerfilRepository perfilRepository;


    public AvaliacaoDetalhadoDto avaliar(avaliacaoDto dto, Integer idUsuario) {
        Avaliacao novaAvaliacao = this.mapper.of(dto);

        Perfil perfil = this.perfilRepository.encontrarPerfilPorIdUsuario(idUsuario).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado")
        );

        novaAvaliacao.setPerfil(perfil);

        return this.mapper.dtoOf(this.avaliacaoRepository.save(novaAvaliacao));
    }

    public List<AvaliacaoDetalhadoDto> encontrarAvaliacoesPerfil(Integer idUsuario) {
        Perfil perfil = this.perfilRepository.encontrarPerfilPorIdUsuario(idUsuario).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado")
        );

        List<Avaliacao> listaAvaliacoes = this.avaliacaoRepository.findAvaliacaoByPerfil(perfil);

        if(listaAvaliacoes.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Perfil não possui avaliações");
        }

        return listaAvaliacoes.stream().map(mapper::dtoOf).toList();
    }

    public List<AvaliacaoTotal> buscarAvaliacaoGeral(Integer idUsuario) {
        Perfil perfil = this.perfilRepository.encontrarPerfilPorIdUsuario(idUsuario).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado")
        );

        List<Object[]> results = this.avaliacaoRepository.quantidadeDeEstrelasAgrupadasPorEstrela(perfil);

        List<AvaliacaoTotal> avaliacoesTotais = new ArrayList<>();

        for (Object[] result : results) {
            int qtdEstrela = (int) result[0];
            long quantidade = (long) result[1];
            avaliacoesTotais.add(new AvaliacaoTotal(qtdEstrela, quantidade));
        }

        return avaliacoesTotais;
    }
}
