package api.tech.hub.techhubapi.service.usuario.dto;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.entity.usuario.UsuarioFuncao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record UsuarioTokenDto (
        Integer id,
        String nome,
        @Enumerated(EnumType.STRING)
        UsuarioFuncao funcao,
        String token
){
    public UsuarioTokenDto(Usuario usuario, String token){
        this(usuario.getId(), usuario.getNome(),usuario.getFuncao(), token);
    }
}
