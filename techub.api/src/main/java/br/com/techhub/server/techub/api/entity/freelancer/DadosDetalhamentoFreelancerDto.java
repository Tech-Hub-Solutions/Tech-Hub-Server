package br.com.techhub.server.techub.api.entity.freelancer;

import java.util.Date;
import java.util.List;

public record DadosDetalhamentoFreelancerDto(String nome, Date dtNascimento, String telefone, String emailContato,
                                             List<String> areaDeAtuacaoList) {

    public DadosDetalhamentoFreelancerDto (Freelancer freelancer){
        this(freelancer.getNome(), freelancer.getDtNascimento(), freelancer.getTelefone(), freelancer.getEmailContato(),
                freelancer.getAreaDeAtuacaoList());
    }


}
