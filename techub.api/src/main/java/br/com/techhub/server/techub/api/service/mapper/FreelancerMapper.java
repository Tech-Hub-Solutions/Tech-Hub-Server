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
        freelancer.setDtNascimento(dto.dtNascimento());
        freelancer.setEmailContato(dto.emailContato());
        freelancer.setTelefoneContato(dto.telefoneContato());
        freelancer.setAreaDeAtuacaoList(dto.areaDeAtuacaoList());
        freelancer.setEndereco(enderecoMapper.cadastroEnderecoDtoToEndereco(dto.endereco()));

        return freelancer;
    }

    public DadosDetalhamentoFreelancerDto freelancerToDadosDetalhamentoFreelancerDto(Freelancer freelancer) {
        return new DadosDetalhamentoFreelancerDto(
                freelancer.getNome(),
                freelancer.getDtNascimento(),
                freelancer.getTelefoneContato(),
                freelancer.getEmailContato(),
                freelancer.getAreaDeAtuacaoList()
        );
    }

    public Freelancer DadosatualizacaoFreelancerDtoToFreelancer(Freelancer freelancer,
                                                                DadosAtualizacaoFreelancerDto dto) {
        freelancer.setTelefoneContato(dto.telefoneContato());
        freelancer.setEmailContato(dto.emailContato());
        freelancer.setAreaDeAtuacaoList(dto.areaDeAtuacaoList());
        freelancer.setEndereco(enderecoMapper.cadastroEnderecoDtoToEndereco(dto.endereco()));

        return freelancer;
    }
}
