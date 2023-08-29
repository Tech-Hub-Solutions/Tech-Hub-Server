package br.com.techhub.server.techub.api.entity.empresa;

import br.com.techhub.server.techub.api.entity.endereco.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {

    private String emailLogin;
    private String senha;
    private String nomeEmpresa;
    private String razaoSocial;
    private String cnpj;
    private String telefoneContato;
    private String emailContato;
    private String representante;
    private Endereco endereco;

}
