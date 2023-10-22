package api.tech.hub.techhubapi.service.flag;

import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.entity.perfil.flag.FlagUsuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlagUsuarioMapper {

    public List<Flag> retornarFlagList(List<FlagUsuario> lista){
        return lista.stream().map(FlagUsuario::getFlag).toList();
    }
}
