package br.com.techhub.server.techub.api.domain.endereco;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Endereco {

    private String cep;
    private int numero;
    private String complemento;

    public Endereco(DadosCadastroEndereco dados) {
        this.cep = dados.cep();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
    }
}
