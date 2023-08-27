package br.com.techhub.server.techub.api.domain.empresa;

import br.com.techhub.server.techub.api.domain.endereco.DadosCadastroEndereco;
import br.com.techhub.server.techub.api.domain.endereco.Endereco;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {

    private String nomeEmpresa;
    private String razaoSocial;
    private String cnpj;
    private String telefoneContato;
    private String emailContato;
    private String representante;
    private Endereco endereco;

    public Empresa(DadosCadastroEmpresa dados) {
        this.nomeEmpresa = dados.nomeEmpresa();
        this.razaoSocial = dados.razaoSocial();
        this.cnpj = dados.cnpj();
        this.telefoneContato = dados.telefoneContato();
        this.emailContato = dados.emailContato();
        this.representante = dados.representante();
        this.endereco = new Endereco(dados.endereco());
    }
}
