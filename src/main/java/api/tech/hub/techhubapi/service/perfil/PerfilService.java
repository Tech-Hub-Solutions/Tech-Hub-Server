package api.tech.hub.techhubapi.service.perfil;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.repository.PerfilRepository;
import api.tech.hub.techhubapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;

    public Perfil buscarPerfilPorIdUsuario(Integer idUsuario) {
        if (this.usuarioRepository.existsById(idUsuario)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado");
        }
        return this.usuarioRepository.findById(idUsuario).get().getPerfil();
    }

    public Integer encontrarIdPerfil(int idUsuario) {
        if (this.usuarioRepository.existsById(idUsuario)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado");
        }
        return this.usuarioRepository.findById(idUsuario).get().getPerfil().getId();
    }

    public Perfil atualizarSobreMim(int idUsuario, String sobreMim) {
        Integer perfilId = encontrarIdPerfil(idUsuario);

        this.perfilRepository.atualizarSobreMimPorId(perfilId, sobreMim);

        return this.perfilRepository.findById(perfilId).get();
    }

    public Perfil atualizarExperiencia(int idUsuario, String experiencia) {
        Integer perfilId = encontrarIdPerfil(idUsuario);

        this.perfilRepository.atualizarExperienciaPorId(perfilId, experiencia);

        return this.perfilRepository.findById(perfilId).get();
    }

    public Perfil atualizarDescricao(int idUsuario, String descricao) {
        Integer perfilId = encontrarIdPerfil(idUsuario);

        this.perfilRepository.atualizarDescricaoPorId(perfilId, descricao);

        return this.perfilRepository.findById(perfilId).get();
    }

    public Perfil atualizarPathPerfilImage(int idUsuario, String pathPerfilImage) {
        Integer perfilId = encontrarIdPerfil(idUsuario);

        this.perfilRepository.atualizarPathPerfilImagePorId(perfilId, pathPerfilImage);

        return this.perfilRepository.findById(perfilId).get();
    }

    public Perfil atualizarPathWallpaperImage(int idUsuario, String pathWallpaperImage) {
        Integer perfilId = encontrarIdPerfil(idUsuario);

        this.perfilRepository.atualizarPathWallpaperImagePorId(perfilId, pathWallpaperImage);

        return this.perfilRepository.findById(perfilId).get();
    }

    public Perfil atualizarPrecoMedio(int idUsuario, String precoMedio) {
        Integer perfilId = encontrarIdPerfil(idUsuario);

        this.perfilRepository.atualizarPrecoMedioPorId(perfilId, precoMedio);

        return this.perfilRepository.findById(perfilId).get();
    }

    public Perfil atualizarLinkGithub(int idUsuario, String linkGithub) {
        Integer perfilId = encontrarIdPerfil(idUsuario);

        this.perfilRepository.atualizarLinkGithub(perfilId, linkGithub);

        return this.perfilRepository.findById(perfilId).get();
    }

    public Perfil atualizarLinkLinkedin(int idUsuario, String linkLinkedin) {
        Integer perfilId = encontrarIdPerfil(idUsuario);

        this.perfilRepository.atualizarlinkLinkedinPorId(perfilId, linkLinkedin);

        return this.perfilRepository.findById(perfilId).get();
    }
}
