package br.com.techhub.server.techub.api.entity.empresa;

import br.com.techhub.server.techub.api.entity.avaliacao.Avaliacao;
import br.com.techhub.server.techub.api.entity.endereco.Endereco;
import br.com.techhub.server.techub.api.service.mapper.EmpresaMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {

    private String email;
    private String senha;
    private String nomeEmpresa;
    private String cnpj;
    private String telefoneContato;
    private String representante;
    private Endereco endereco;
    private List<Avaliacao> avaliacoes;

    public void receberAvaliacao(Avaliacao avaliacao){
        avaliacoes.add(avaliacao);
    }
}
