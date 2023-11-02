package api.tech.hub.techhubapi.service.referencia;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.repository.PerfilRepository;
import api.tech.hub.techhubapi.repository.ReferenciaPerfilRepository;
import api.tech.hub.techhubapi.service.referencia.dto.ReferenciaDetalhadoDto;
import api.tech.hub.techhubapi.service.referencia.dto.ReferenciaPerfilCriacaoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReferenciaPerfilService {

    private final ReferenciaPerfilRepository referenciaPerfilRepository;
    private final PerfilRepository perfilRepository;
    private final ReferenciaPerfilMapper referenciaPerfilMapper;

    public ReferenciaDetalhadoDto criarReferenciaPerfil(Integer idAvaliador, ReferenciaPerfilCriacaoDto dto) {

        ReferenciaPerfil referenciaPerfil = this.referenciaPerfilMapper.of(dto);

        Perfil avaliado = this.perfilRepository.findById(dto.avaliado().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Perfil de avaliado não encontrado")
        );

        Perfil avaliador = this.perfilRepository.findById(dto.avaliador().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Perfil de avaliador não encontrado")
        );

        referenciaPerfil.setAvaliado(avaliado);
        referenciaPerfil.setAvaliador(avaliador);

        ReferenciaPerfil referenciaPerfilSalvo = this.referenciaPerfilRepository.save(referenciaPerfil);

        return this.referenciaPerfilMapper.dtoOf(referenciaPerfilSalvo);
    }

    public List<ReferenciaDetalhadoDto> encontrarReferenciasPerfil(Integer idUsuario) {
        Perfil perfil = this.perfilRepository.encontrarPerfilPorIdUsuario(idUsuario).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado")
        );

        List<ReferenciaPerfil> listaReferenciaPerfil = this.referenciaPerfilRepository
                .findByAvaliado(perfil);

        if(listaReferenciaPerfil.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Perfil não possui referencias");
        }

        return listaReferenciaPerfil.stream().map(referenciaPerfilMapper::dtoOf).toList();
    }

    public Page<ReferenciaPerfil> listarFavoritos(Usuario usuarioLogado, Pageable pageable) {
        return this.referenciaPerfilRepository.findByAvaliador(usuarioLogado.getPerfil(), pageable);
    }
}
