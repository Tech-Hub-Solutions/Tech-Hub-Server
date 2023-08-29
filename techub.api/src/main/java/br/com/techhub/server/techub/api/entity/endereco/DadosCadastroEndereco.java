package br.com.techhub.server.techub.api.entity.endereco;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroEndereco(
        @NotNull
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotNull
        int numero,
        @NotNull
        String complemento
) {
}
