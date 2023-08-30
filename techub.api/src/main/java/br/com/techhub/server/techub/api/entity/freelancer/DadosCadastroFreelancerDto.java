package br.com.techhub.server.techub.api.entity.freelancer;

import br.com.techhub.server.techub.api.entity.endereco.DadosCadastroEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.Date;
import java.util.List;


public record DadosCadastroFreelancerDto(
        @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotNull
        @Past
        Date dtNascimento,

        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String telefoneContato,
        @NotNull
        List<String> areaDeAtuacaoList,
        @NotNull
        @Valid
        DadosCadastroEndereco endereco
) {
}
