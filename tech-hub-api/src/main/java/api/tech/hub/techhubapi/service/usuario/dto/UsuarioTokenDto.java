package api.tech.hub.techhubapi.service.usuario.dto;

import api.tech.hub.techhubapi.entity.usuario.Usuario;

public record UsuarioTokenDto (
        Integer id,
        String nome,
        String funcao,
        String token
){
    public UsuarioTokenDto(Usuario usuario, String token){
        this(usuario.getId(), usuario.getNome(),usuario.getFuncao(), token);
    }
}
