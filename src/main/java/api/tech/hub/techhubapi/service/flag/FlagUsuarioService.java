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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlagUsuarioService {

    private final FlagUsuarioRepository flagUsuarioRepository;
    private final FlagRepository flagRepository;

    public void salvarFlagUsuario(Perfil perfilValidado, List<Integer> flagsId) {
        this.flagUsuarioRepository.deleteFlagUsuarioByPerfil(perfilValidado);

        if (flagsId != null && !flagsId.isEmpty()) {

            List<FlagUsuario> flagUsuarioList = new ArrayList<>();

            List<Flag> flags = this.flagRepository.findByIdIn(flagsId);


            for (Flag flag : flags) {
                FlagUsuario flagUsuario = new FlagUsuario();
                flagUsuario.setPerfil(perfilValidado);
                flagUsuario.setFlag(flag);

                flagUsuarioList.add(flagUsuario);
            }

            this.flagUsuarioRepository.saveAll(flagUsuarioList);
        }
    }


}
