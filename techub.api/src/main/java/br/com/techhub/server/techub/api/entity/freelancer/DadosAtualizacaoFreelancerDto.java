package br.com.techhub.server.techub.api.entity.freelancer;

import br.com.techhub.server.techub.api.entity.endereco.DadosCadastroEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.util.Date;
import java.util.List;

public record DadosAtualizacaoFreelancerDto(
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String telefone,
        @Email
        String email,
        @NotBlank
        List<String> areaDeAtuacaoList,
        @NotBlank
        @Valid
        DadosCadastroEndereco endereco
) {
}
