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
    private final FlagUsuarioService flagUsuarioService;


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

        return criarPerfilDetalhadoDto(idUsuario);
    }

    private PerfilDetalhadoDto criarPerfilDetalhadoDto(int idUsuario) {
        Perfil perfil = this.perfilRepository.encontrarPerfilPorIdUsuario(idUsuario).get();
        return this.perfilMapper.dtoOf(perfil);
    }

    public PerfilDetalhadoDto buscarPerfilDetalhadoPorIdUsuario(Integer idUsuario) {
        if (!this.usuarioRepository.existsById(idUsuario)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        return criarPerfilDetalhadoDto(idUsuario);
    }

}
