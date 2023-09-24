package api.tech.hub.techhubapi.service.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public record UsuarioCriacaoDto(
        @NotBlank
        String nome,

        @Email
        String email,

        @NotBlank
        String senha,

        @CNPJ
        @CPF
        String numeroCadastroPessoa,

        @NotBlank
        String pais,

        @NotBlank
        String funcao

) {
}
