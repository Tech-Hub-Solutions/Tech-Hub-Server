package api.tech.hub.techhubapi.service.usuario.dto;

import api.tech.hub.techhubapi.service.perfil.dto.PerfilDetalhadoDto;

public record UsuarioGeralDto(
        UsuarioDetalhadoDto usuario,
        PerfilDetalhadoDto perfil
) {

}
