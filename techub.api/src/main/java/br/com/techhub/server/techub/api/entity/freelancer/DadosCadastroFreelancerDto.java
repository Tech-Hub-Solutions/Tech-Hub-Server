package br.com.techhub.server.techub.api.entity.freelancer;

import br.com.techhub.server.techub.api.entity.endereco.DadosCadastroEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.Date;
import java.util.List;


public record DadosCadastroFreelancerDto(
        @NotBlank
        String emailLogin,
        @NotBlank
        String senha,
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "\\d{14}")
        String cpf,
        @NotBlank
        @Past
        Date dtNascimento,

        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String telefone,
        @Email
        String emailContato,
        @NotBlank
        List<String> areaDeAtuacaoList,
        @NotBlank
        @Valid
        DadosCadastroEndereco endereco
) {
}
