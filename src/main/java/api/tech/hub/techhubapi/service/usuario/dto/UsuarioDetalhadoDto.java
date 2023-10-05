package api.tech.hub.techhubapi.service.usuario.dto;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDetalhadoDto(String nome, String email, String numeroCadastroPessoa,
                                  String pais, String funcao) {

    public UsuarioDetalhadoDto (Usuario usuario){
        this(usuario.getNome(),usuario.getEmail(), usuario.getNumeroCadastroPessoa(),
                usuario.getPais(), usuario.getFuncao());
    }
}
