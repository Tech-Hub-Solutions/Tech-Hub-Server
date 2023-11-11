package api.tech.hub.techhubapi.service.usuario.dto;

import api.tech.hub.techhubapi.entity.usuario.UsuarioFuncao;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public record UsuarioAtualizacaoDto(
        @NotBlank
        String nome,

        @Email
        String email,

        @NotBlank
        String senha
) {
}
