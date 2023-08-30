package br.com.techhub.server.techub.api.entity.empresa;

import br.com.techhub.server.techub.api.entity.endereco.DadosCadastroEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroEmpresaDto(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String nomeEmpresa,
        @NotBlank
        String razaoSocial,
        @NotBlank
        @Pattern(regexp = "\\d{14}")
        String cnpj,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String telefoneContato,

        @NotBlank
        String representante,

        @NotNull
        @Valid
        DadosCadastroEndereco endereco

) {
}
