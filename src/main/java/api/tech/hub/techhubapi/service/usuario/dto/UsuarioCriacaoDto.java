package api.tech.hub.techhubapi.service.usuario.dto;

import api.tech.hub.techhubapi.entity.usuario.UsuarioFuncao;
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

        String numeroCadastroPessoa,

        @NotBlank
        String pais,

        @NotBlank
        UsuarioFuncao funcao


        // Possivelmente vai ter que criar uma anotação aqui
        // Mas antes precisamos verificar com os professores se realmente precisa criar
        //Mandei email pro reis e agora estou esperando resposta

) {
}
