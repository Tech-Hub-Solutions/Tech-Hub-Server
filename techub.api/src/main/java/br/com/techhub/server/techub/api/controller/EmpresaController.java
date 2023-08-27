package br.com.techhub.server.techub.api.controller;

import br.com.techhub.server.techub.api.domain.empresa.DadosCadastroEmpresa;
import br.com.techhub.server.techub.api.domain.empresa.DadosDetalhamentoEmpresa;
import br.com.techhub.server.techub.api.domain.empresa.Empresa;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private List<Empresa> empresas = new ArrayList<>();
    @PostMapping
    public ResponseEntity cadastrar (@RequestBody @Valid DadosCadastroEmpresa dados){
        Empresa emp = new Empresa(dados);
        empresas.add(emp);

        return ResponseEntity.ok().body(new DadosDetalhamentoEmpresa(emp));
    }

    @GetMapping
    public List<Empresa> listar(){
        return empresas;
    }
}
