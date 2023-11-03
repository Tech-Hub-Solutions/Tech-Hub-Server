package api.tech.hub.techhubapi.service.referencia;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.repository.PerfilRepository;
import api.tech.hub.techhubapi.repository.ReferenciaPerfilRepository;
import api.tech.hub.techhubapi.service.referencia.dto.ReferenciaDetalhadoDto;
import api.tech.hub.techhubapi.service.usuario.autenticacao.AutenticacaoService;
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
    private final AutenticacaoService autenticacaoService;

    public ReferenciaDetalhadoDto favoritarTerceiro(Integer idAvaliado) {
        Usuario usuarioLogado = this.autenticacaoService.getUsuarioFromUsuarioDetails();

        if (usuarioLogado.getId().equals(idAvaliado)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"O usuário não pode se favoritar!");
        }

        Perfil avaliado = this.perfilRepository.encontrarPerfilPorIdUsuario(idAvaliado)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil a ser avaliado não existe!")
                );


        if (!this.referenciaPerfilRepository.existsByAvaliadoAndAvaliador(avaliado, usuarioLogado.getPerfil())) {
            criarReferenciaPerfil(usuarioLogado.getPerfil(), avaliado);
        }

        ReferenciaPerfil referenciaPerfil = this.referenciaPerfilRepository.findReferenciaPerfilByAvaliadoAndAvaliador(avaliado,usuarioLogado.getPerfil());

        referenciaPerfil.setFavorito(!referenciaPerfil.isFavorito());

        ReferenciaPerfil referenciaPerfilSalva = this.referenciaPerfilRepository.save(referenciaPerfil);

        return this.referenciaPerfilMapper.dtoOf(referenciaPerfilSalva);
    }

    public void criarReferenciaPerfil(Perfil avaliador, Perfil avaliado) {
        ReferenciaPerfil referenciaPerfil = new ReferenciaPerfil();

        referenciaPerfil.setAvaliador(avaliador);
        referenciaPerfil.setAvaliado(avaliado);
        referenciaPerfil.setFavorito(false);
        referenciaPerfil.setRecomendado(false);

        ReferenciaPerfil ref = this.referenciaPerfilRepository.save(referenciaPerfil);
        System.out.println("Referencia foi criada!");
        System.out.println(ref);
    }

    public ReferenciaDetalhadoDto recomendarTerceiro(Integer idAvaliado) {
        Usuario usuarioLogado = this.autenticacaoService.getUsuarioFromUsuarioDetails();

        if (usuarioLogado.getId().equals(idAvaliado)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"O usuário não pode se recomendar!");
        }

        Perfil avaliado = this.perfilRepository.encontrarPerfilPorIdUsuario(idAvaliado)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil a ser avaliado não existe!")
                );

        if (!this.referenciaPerfilRepository.existsByAvaliadoAndAvaliador(avaliado, usuarioLogado.getPerfil())) {
            criarReferenciaPerfil(usuarioLogado.getPerfil(), avaliado);
        }

        ReferenciaPerfil referenciaPerfil = this.referenciaPerfilRepository.findReferenciaPerfilByAvaliadoAndAvaliador(avaliado,usuarioLogado.getPerfil());

        referenciaPerfil.setRecomendado(!referenciaPerfil.isRecomendado());

        ReferenciaPerfil referenciaPerfilSalva = this.referenciaPerfilRepository.save(referenciaPerfil);

        return this.referenciaPerfilMapper.dtoOf(referenciaPerfilSalva);
    }

    public List<ReferenciaPerfil> encontrarReferenciasPerfil() {
        Usuario usuarioLogado = this.autenticacaoService.getUsuarioFromUsuarioDetails();

        return this.referenciaPerfilRepository.findByAvaliador(usuarioLogado.getPerfil());
    }

    public Page<ReferenciaPerfil> listarFavoritos(Usuario usuarioLogado, Pageable pageable) {
        return this.referenciaPerfilRepository.findByAvaliadorAndIsFavoritoTrue(usuarioLogado.getPerfil(), pageable);
    }



}
