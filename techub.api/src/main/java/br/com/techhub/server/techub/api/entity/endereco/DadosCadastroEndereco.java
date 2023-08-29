package br.com.techhub.server.techub.api.entity.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroEndereco(
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotNull
        int numero,
        @NotBlank
        String complemento
) {
}
