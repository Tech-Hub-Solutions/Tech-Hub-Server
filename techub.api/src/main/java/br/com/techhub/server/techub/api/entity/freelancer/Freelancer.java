package br.com.techhub.server.techub.api.entity.freelancer;

import br.com.techhub.server.techub.api.entity.endereco.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Freelancer {

    private String emailLogin;
    private String senha;
    private String nome;
    private String cpf;
    private Date dtNascimento;
    private String telefoneContato;
    private String emailContato;
    private List<String> areaDeAtuacaoList;
    private Endereco endereco;

}
