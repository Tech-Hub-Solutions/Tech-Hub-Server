package api.tech.hub.techhubapi.service.perfil;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;
import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.entity.perfil.flag.FlagUsuario;
import api.tech.hub.techhubapi.repository.*;
import api.tech.hub.techhubapi.service.flag.FlagUsuarioService;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilCadastroDto;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilDetalhadoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;
    private final PerfilMapper perfilMapper;
    private final FlagUsuarioRepository flagUsuarioRepository;
    private final FlagUsuarioService flagUsuarioService;
    private final AvaliacaoRepository avaliacaoRepository;
    private final ReferenciaPerfilRepository referenciaPerfilRepository;


    public Perfil buscarPerfilPorIdUsuario(Integer idUsuario) {
        if (!this.usuarioRepository.existsById(idUsuario)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        Perfil perfil = this.perfilRepository.encontrarPerfilPorIdUsuario(idUsuario)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado")
                );

        return perfil;
    }

    public PerfilDetalhadoDto atualizarPerfil(int idUsuario, PerfilCadastroDto dto) {
        if (!this.usuarioRepository.existsById(idUsuario)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        Perfil perfil = this.perfilRepository.encontrarPerfilPorIdUsuario(idUsuario).get();

        perfil = this.perfilMapper.of(perfil, dto);

        this.perfilRepository.save(perfil);

        if (!dto.flagList().isEmpty()) {
            flagUsuarioService.salvarFlagUsuario(perfil, dto.flagList());
        }

        return criarPerfilDetalhadoDto(perfil);
    }

    private PerfilDetalhadoDto criarPerfilDetalhadoDto(Perfil perfil) {

        List<Flag> flagList = this.flagUsuarioRepository.encontrarFlagPorPerfil(perfil);

        List<Avaliacao> avaliacaoDetalhadoDtoList = this.avaliacaoRepository.findAvaliacaoByPerfil(perfil);

        List<ReferenciaPerfil> referenciaPerfil = this.referenciaPerfilRepository
                .findByAvaliado(perfil);

        return this.perfilMapper.dtoOf(perfil, flagList, avaliacaoDetalhadoDtoList,
                referenciaPerfil);
    }

    public PerfilDetalhadoDto buscarPerfilDetalhadoPorIdUsuario(Integer idUsuario) {
        if (!this.usuarioRepository.existsById(idUsuario)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        Perfil perfil = this.perfilRepository.encontrarPerfilPorIdUsuario(idUsuario)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado")
                );

        return criarPerfilDetalhadoDto(perfil);
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
}
