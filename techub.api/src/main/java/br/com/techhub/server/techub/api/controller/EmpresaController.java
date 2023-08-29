package br.com.techhub.server.techub.api.controller;

import br.com.techhub.server.techub.api.entity.empresa.DadosAtualizacaoEmpresaDto;
import br.com.techhub.server.techub.api.entity.empresa.DadosCadastroEmpresaDto;
import br.com.techhub.server.techub.api.entity.empresa.DadosDetalhamentoEmpresaDto;
import br.com.techhub.server.techub.api.entity.empresa.Empresa;
import br.com.techhub.server.techub.api.service.mapper.EmpresaMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    private List<Empresa> empresas = new ArrayList<>();

    @Autowired
    private EmpresaMapper empresaMapper;

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoEmpresaDto>> listar(){
        return ResponseEntity.status(200).body(empresas.stream().map(x -> empresaMapper.empresaToDadosDetalhamentoEmpresaDto(x))
                .toList());
    }

    @GetMapping("/{indice}")
    public ResponseEntity<Empresa> getEmpresaPorIndice(@PathVariable int indice){
        return ResponseEntity.status(200).body(empresas.get(indice));
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoEmpresaDto> cadastrar (@RequestBody @Valid DadosCadastroEmpresaDto dados){
        Empresa empresa = empresaMapper.DadoscadastroEmpresaDtoToEmpresa(dados);
        empresas.add(empresa);

        return ResponseEntity.status(201).body(empresaMapper.empresaToDadosDetalhamentoEmpresaDto(empresa));
    }

    @PutMapping("/{indice}")
    public ResponseEntity<DadosDetalhamentoEmpresaDto> atualizarDadosDeEmpresa(@PathVariable int indice,
                                                                              @RequestBody DadosAtualizacaoEmpresaDto dados){

        Empresa empresa = empresaMapper.DadosatualizacaoEmpresaDtoToEmpresa(empresas.get(indice),dados);
        empresas.set(indice, empresa);

        return ResponseEntity.status(200).body(empresaMapper.empresaToDadosDetalhamentoEmpresaDto(empresa));
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletarEmpresaPorIndice(@PathVariable int indice) {
        empresas.remove(indice);
        return ResponseEntity.status(204).build();
    }
}
