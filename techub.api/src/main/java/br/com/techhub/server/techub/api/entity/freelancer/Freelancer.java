package br.com.techhub.server.techub.api.entity.freelancer;

import br.com.techhub.server.techub.api.entity.avaliacao.Avaliacao;
import br.com.techhub.server.techub.api.entity.endereco.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Freelancer {

    private String email;
    private String senha;
    private String nome;
    private String cpf;
    private Date dtNascimento;
    private String telefoneContato;
    private List<String> areaDeAtuacaoList;
    private Endereco endereco;
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    public void receberAvaliacao(Avaliacao avaliacao){
        avaliacoes.add(avaliacao);
    }

}
