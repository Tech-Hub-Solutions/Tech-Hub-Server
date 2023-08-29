package br.com.techhub.server.techub.api.entity.endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
