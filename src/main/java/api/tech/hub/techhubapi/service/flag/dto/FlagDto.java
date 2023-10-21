package api.tech.hub.techhubapi.service.flag.dto;

import api.tech.hub.techhubapi.entity.perfil.flag.Flag;

public record FlagDto(
         Integer id,
         String nome,
         String area,
         String categoria
) {

    public FlagDto (Flag flag){
        this(
                flag.getId(), flag.getNome(), flag.getArea(), flag.getCategoria()
        );
    }
}
