package api.tech.hub.techhubapi.service.perfil;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.entity.perfil.flag.FlagUsuario;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.repository.FlagRepository;
import api.tech.hub.techhubapi.repository.FlagUsuarioRepository;
import api.tech.hub.techhubapi.repository.PerfilRepository;
import api.tech.hub.techhubapi.repository.UsuarioRepository;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilCadastroDto;
import api.tech.hub.techhubapi.service.avaliacao.dto.avaliacaoDto;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilDetalhadoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;
    private final PerfilMapper perfilMapper;
    private final FlagUsuarioRepository flagUsuarioRepository;
    private final FlagRepository flagRepository;

    public PerfilDetalhadoDto buscarPerfilPorIdUsuario(Integer idUsuario) {
        if (!this.usuarioRepository.existsById(idUsuario)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        Perfil perfil = this.perfilRepository.encontrarPerfilPorIdUsuario(idUsuario)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado")
                );

        return perfilMapper.dtoOf(perfil);
    }

    public Integer encontrarIdPerfil(int idUsuario) {
        if (this.usuarioRepository.existsById(idUsuario)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado");
        }
        return this.usuarioRepository.findById(idUsuario).get().getPerfil().getId();
    }

//    public Perfil atualizarSobreMim(int idUsuario, String sobreMim) {
//        Integer perfilId = encontrarIdPerfil(idUsuario);
//
//        this.perfilRepository.atualizarSobreMimPorId(perfilId, sobreMim);
//
//        return this.perfilRepository.findById(perfilId).get();
//    }
//
//    public Perfil atualizarExperiencia(int idUsuario, String experiencia) {
//        Integer perfilId = encontrarIdPerfil(idUsuario);
//
//        this.perfilRepository.atualizarExperienciaPorId(perfilId, experiencia);
//
//        return this.perfilRepository.findById(perfilId).get();
//    }
//
//    public Perfil atualizarDescricao(int idUsuario, String descricao) {
//        Integer perfilId = encontrarIdPerfil(idUsuario);
//
//        this.perfilRepository.atualizarDescricaoPorId(perfilId, descricao);
//
//        return this.perfilRepository.findById(perfilId).get();
//    }
//
//    public Perfil atualizarPathPerfilImage(int idUsuario, String pathPerfilImage) {
//        Integer perfilId = encontrarIdPerfil(idUsuario);
//
//        this.perfilRepository.atualizarPathPerfilImagePorId(perfilId, pathPerfilImage);
//
//        return this.perfilRepository.findById(perfilId).get();
//    }
//
//    public Perfil atualizarPathWallpaperImage(int idUsuario, String pathWallpaperImage) {
//        Integer perfilId = encontrarIdPerfil(idUsuario);
//
//        this.perfilRepository.atualizarPathWallpaperImagePorId(perfilId, pathWallpaperImage);
//
//        return this.perfilRepository.findById(perfilId).get();
//    }
//
//    public Perfil atualizarPrecoMedio(int idUsuario, String precoMedio) {
//        Integer perfilId = encontrarIdPerfil(idUsuario);
//
//        this.perfilRepository.atualizarPrecoMedioPorId(perfilId, precoMedio);
//
//        return this.perfilRepository.findById(perfilId).get();
//    }
//
//    public Perfil atualizarLinkGithub(int idUsuario, String linkGithub) {
//        Integer perfilId = encontrarIdPerfil(idUsuario);
//
//        this.perfilRepository.atualizarLinkGithub(perfilId, linkGithub);
//
//        return this.perfilRepository.findById(perfilId).get();
//    }
//
//    public Perfil atualizarLinkLinkedin(int idUsuario, String linkLinkedin) {
//        Integer perfilId = encontrarIdPerfil(idUsuario);
//
//        this.perfilRepository.atualizarlinkLinkedinPorId(perfilId, linkLinkedin);
//
//        return this.perfilRepository.findById(perfilId).get();
//    }

    public PerfilDetalhadoDto validarDtoCadastro(int idUsuario, PerfilCadastroDto dto) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado")
        );

        Perfil perfilValidado = this.perfilRepository.save(this.perfilMapper.of(usuario,dto));
        List<FlagUsuario> flagUsuarioList = new ArrayList<>();

        for (Flag f : dto.flagList()) {
            FlagUsuario flagUsuario = new FlagUsuario();
            flagUsuario.setPerfil(perfilValidado);

            if (this.flagRepository.existsById(f.getId())) {
                Flag flag = this.flagRepository.findById(f.getId()).get();
                flagUsuario.setFlag(flag);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Flag não encontrada!");
            }
            this.flagUsuarioRepository.save(flagUsuario);
            flagUsuarioList.add(flagUsuario);
        }

        perfilValidado.setFlagUsuarioList(flagUsuarioList);

        return this.perfilMapper.dtoOf(perfilValidado);
    }

}
