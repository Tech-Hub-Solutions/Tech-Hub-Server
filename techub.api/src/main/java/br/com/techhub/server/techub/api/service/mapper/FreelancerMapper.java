package br.com.techhub.server.techub.api.service.mapper;

import br.com.techhub.server.techub.api.entity.freelancer.DadosAtualizacaoFreelancerDto;
import br.com.techhub.server.techub.api.entity.freelancer.DadosCadastroFreelancerDto;
import br.com.techhub.server.techub.api.entity.freelancer.DadosDetalhamentoFreelancerDto;
import br.com.techhub.server.techub.api.entity.freelancer.Freelancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreelancerMapper {

    @Autowired
    EnderecoMapper enderecoMapper;

    public Freelancer DadoscadastroEmFreelancerDtoToFreelancer(DadosCadastroFreelancerDto dto) {
        Freelancer freelancer = new Freelancer();
        freelancer.setEmailLogin(dto.emailLogin());
        freelancer.setSenha(dto.senha());
        freelancer.setNome(dto.nome());
        freelancer.setCpf(dto.cpf());
        freelancer.setEmailContato(dto.emailContato());
        freelancer.setTelefone(dto.telefone());
        freelancer.setAreaDeAtuacaoList(dto.areaDeAtuacaoList());
        freelancer.setEndereco(enderecoMapper.cadastroEnderecoDtoToEndereco(dto.endereco()));

        return freelancer;
    }

    public DadosDetalhamentoFreelancerDto freelancerToDadosDetalhamentoFreelancerDto(Freelancer freelancer) {
        return new DadosDetalhamentoFreelancerDto(
                freelancer.getNome(),
                freelancer.getDtNascimento(),
                freelancer.getTelefone(),
                freelancer.getEmailContato(),
                freelancer.getAreaDeAtuacaoList()
        );
    }

    public Freelancer DadosatualizacaoFreelancerDtoToFreelancer(Freelancer freelancer,
                                                                DadosAtualizacaoFreelancerDto dto) {
        freelancer.setTelefone(dto.telefone());
        freelancer.setEmailContato(dto.email());
        freelancer.setAreaDeAtuacaoList(dto.areaDeAtuacaoList());
        freelancer.setEndereco(enderecoMapper.cadastroEnderecoDtoToEndereco(dto.endereco()));

        return freelancer;
    }
}
