package api.tech.hub.techhubapi.service.usuario.dto;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.entity.usuario.UsuarioFuncao;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDetalhadoDto(
        String nome,
        String email,
        String numeroCadastroPessoa,

        String pais,
        UsuarioFuncao funcao
) {

    public UsuarioDetalhadoDto(Usuario usuario) {
        this(usuario.getNome(), usuario.getEmail(), usuario.getNumeroCadastroPessoa(),
                usuario.getPais(), usuario.getFuncao());
    }
}
