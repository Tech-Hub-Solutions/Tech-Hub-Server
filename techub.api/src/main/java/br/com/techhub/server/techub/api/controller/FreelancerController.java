package br.com.techhub.server.techub.api.controller;

import br.com.techhub.server.techub.api.entity.avaliacao.Avaliacao;
import br.com.techhub.server.techub.api.entity.avaliacao.DadosAvaliacaoDto;
import br.com.techhub.server.techub.api.entity.empresa.Empresa;
import br.com.techhub.server.techub.api.entity.freelancer.DadosAtualizacaoFreelancerDto;
import br.com.techhub.server.techub.api.entity.freelancer.DadosCadastroFreelancerDto;
import br.com.techhub.server.techub.api.entity.freelancer.DadosDetalhamentoFreelancerDto;
import br.com.techhub.server.techub.api.entity.freelancer.Freelancer;
import br.com.techhub.server.techub.api.service.FreelancerService;
import br.com.techhub.server.techub.api.service.mapper.FreelancerMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/freelancers")
public class FreelancerController {
    @Autowired
    private List<Freelancer> freelancers = new ArrayList<>();
    @Autowired
    private FreelancerMapper freelancerMapper;
    @Autowired
    private FreelancerService freelancerService;

    @Autowired
    private List<Empresa> empresas;


    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoFreelancerDto>> listar(){
        return ResponseEntity.status(200).body(freelancers.stream().map(x -> freelancerMapper.
                        freelancerToDadosDetalhamentoFreelancerDto(x)).toList());
    }

    @GetMapping("/{indice}")
    public ResponseEntity<DadosDetalhamentoFreelancerDto> getEmpresaPorIndice(@PathVariable int indice){
        return ResponseEntity.status(200).body(freelancerMapper.freelancerToDadosDetalhamentoFreelancerDto(
                freelancers.get(indice)));
    }

    @GetMapping("avaliar/{indice}")
    public ResponseEntity<List<Avaliacao>> getAvaliacaoDeFreelancersPorIndice(@PathVariable int indice){
        return ResponseEntity.status(200).body(freelancers.get(indice).getAvaliacoes());
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoFreelancerDto> cadastrar (@RequestBody @Valid DadosCadastroFreelancerDto dados){
        Freelancer freelancer = freelancerMapper.DadoscadastroEmFreelancerDtoToFreelancer(dados);
        freelancers.add(freelancer);

        return ResponseEntity.status(201).body(freelancerMapper.freelancerToDadosDetalhamentoFreelancerDto(freelancer));
    }

    @PostMapping("avaliar/{indiceEmpresa}/{indiceFreelancer}")
    public ResponseEntity<Avaliacao> avaliarEmpresa(@PathVariable int indiceEmpresa, @PathVariable int indiceFreelancer,
                                                       @RequestBody @Valid DadosAvaliacaoDto dto){
        Empresa empresa = empresas.get(indiceEmpresa);
        Freelancer freelancer = freelancers.get(indiceFreelancer);

        Avaliacao avaliacao = freelancerService.avaliar(empresa,freelancer,dto);
        empresa.receberAvaliacao(avaliacao);

        return ResponseEntity.status(200).body(avaliacao);
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
