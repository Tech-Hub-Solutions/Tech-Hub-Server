package br.com.techhub.server.techub.api.entity.empresa;

import br.com.techhub.server.techub.api.entity.endereco.DadosCadastroEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoEmpresaDto(
        @NotNull
        @Pattern(regexp = "\\d{11}")
        String telefoneContato,
        @Email
        String email,
        @NotNull
        String representante,
        @NotNull
        @Valid
        DadosCadastroEndereco endereco
) {
}
