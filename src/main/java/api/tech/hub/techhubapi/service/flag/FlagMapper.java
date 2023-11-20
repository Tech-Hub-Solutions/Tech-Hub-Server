package api.tech.hub.techhubapi.service.flag;

import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.service.flag.dto.FlagDto;
import api.tech.hub.techhubapi.service.flag.dto.FlagRequestDto;
import org.springframework.stereotype.Component;

@Component
public class FlagMapper {
    public static Flag of(FlagRequestDto dto) {
        Flag flag = new Flag();

        flag.setNome(dto.nome());
        flag.setArea(dto.area());
        flag.setCategoria(dto.categoria());

        return flag;
    }

    public static FlagDto dtoOf(Flag flag) {
        return new FlagDto(flag);
    }
}
