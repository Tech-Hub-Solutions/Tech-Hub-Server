package api.tech.hub.techhubapi.service.flag;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.entity.perfil.flag.FlagUsuario;
import api.tech.hub.techhubapi.repository.FlagRepository;
import api.tech.hub.techhubapi.repository.FlagUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlagUsuarioService {

    private final FlagUsuarioRepository flagUsuarioRepository;
    private final FlagRepository flagRepository;

    public void salvarFlagUsuario(Perfil perfilValidado, List<Flag> list) {
        for (Flag f : list) {
            FlagUsuario flagUsuario = new FlagUsuario();
            flagUsuario.setPerfil(perfilValidado);

            if (this.flagRepository.existsById(f.getId())) {
                Flag flag = this.flagRepository.findById(f.getId()).get();
                flagUsuario.setFlag(flag);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Flag não encontrada!");
            }
            this.flagUsuarioRepository.save(flagUsuario);
        }
    }
}
