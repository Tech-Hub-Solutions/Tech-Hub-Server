package br.com.techhub.server.techub.api.domain.empresa;

import br.com.techhub.server.techub.api.domain.endereco.DadosCadastroEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroEmpresa(
        @NotNull
        String nomeEmpresa,
        @NotNull
        String razaoSocial,
        @NotNull
        @Pattern(regexp = "\\d{14}")
        String cnpj,
        @NotNull
        @Pattern(regexp = "\\d{11}")
        String telefoneContato,
        @Email
        String emailContato,
        @NotNull
        String representante,
        @NotNull
        @Valid
        DadosCadastroEndereco endereco

) {
}
