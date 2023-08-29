package br.com.techhub.server.techub.api.controller;

import br.com.techhub.server.techub.api.entity.freelancer.DadosAtualizacaoFreelancerDto;
import br.com.techhub.server.techub.api.entity.freelancer.DadosCadastroFreelancerDto;
import br.com.techhub.server.techub.api.entity.freelancer.DadosDetalhamentoFreelancerDto;
import br.com.techhub.server.techub.api.entity.freelancer.Freelancer;
import br.com.techhub.server.techub.api.service.mapper.FreelancerMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/freelancers")
public class FreelancerController {
    private List<Freelancer> freelancers = new ArrayList<>();

    @Autowired
    private FreelancerMapper freelancerMapper;

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoFreelancerDto>> listar(){
        return ResponseEntity.status(200).body(freelancers.stream().map(x -> freelancerMapper.
                        freelancerToDadosDetalhamentoFreelancerDto(x)).toList());
    }

    @GetMapping("/{indice}")
    public ResponseEntity<Freelancer> getEmpresaPorIndice(@PathVariable int indice){
        return ResponseEntity.status(200).body(freelancers.get(indice));
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoFreelancerDto> cadastrar (@RequestBody @Valid DadosCadastroFreelancerDto dados){
        Freelancer freelancer = freelancerMapper.DadoscadastroEmFreelancerDtoToFreelancer(dados);
        freelancers.add(freelancer);

        return ResponseEntity.status(201).body(freelancerMapper.freelancerToDadosDetalhamentoFreelancerDto(freelancer));
    }

    @PutMapping("/{indice}")
    public ResponseEntity<DadosDetalhamentoFreelancerDto> atualizarDadosDeEmpresa(@PathVariable int indice,
                                                                               @RequestBody DadosAtualizacaoFreelancerDto dados){
        Freelancer freelancer = freelancerMapper.DadosatualizacaoFreelancerDtoToFreelancer(
                freelancers.get(indice),dados);

        freelancers.set(indice,freelancer);

        return ResponseEntity.status(200).body(freelancerMapper.freelancerToDadosDetalhamentoFreelancerDto(freelancer));
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletarEmpresaPorIndice(@PathVariable int indice) {
        freelancers.remove(indice);
        return ResponseEntity.status(204).build();
    }
}
