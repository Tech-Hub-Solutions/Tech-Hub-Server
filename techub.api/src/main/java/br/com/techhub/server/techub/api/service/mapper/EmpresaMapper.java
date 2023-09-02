package br.com.techhub.server.techub.api.service.mapper;

import br.com.techhub.server.techub.api.entity.empresa.DadosAtualizacaoEmpresaDto;
import br.com.techhub.server.techub.api.entity.empresa.DadosCadastroEmpresaDto;
import br.com.techhub.server.techub.api.entity.empresa.DadosDetalhamentoEmpresaDto;
import br.com.techhub.server.techub.api.entity.empresa.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaMapper {
    @Autowired
    EnderecoMapper enderecoMapper;


    public Empresa DadoscadastroEmpresaDtoToEmpresa(DadosCadastroEmpresaDto dto){
        Empresa empresa = new Empresa();
        empresa.setEmail(dto.email());
        empresa.setSenha(dto.senha());
        empresa.setNomeEmpresa(dto.nomeEmpresa());
        empresa.setCnpj(dto.cnpj());
        empresa.setTelefoneContato(dto.telefoneContato());
        empresa.setRepresentante(dto.representante());
        return empresa;
    }

    public DadosDetalhamentoEmpresaDto empresaToDadosDetalhamentoEmpresaDto (Empresa empresa){
        return new DadosDetalhamentoEmpresaDto(
                empresa.getNomeEmpresa(),
                empresa.getCnpj(),
                empresa.getEmail(),
                empresa.getTelefoneContato()
        );
    }

    public Empresa DadosatualizacaoEmpresaDtoToEmpresa(Empresa empresa, DadosAtualizacaoEmpresaDto dto){
        empresa.setTelefoneContato(dto.telefoneContato());
        empresa.setEmail(dto.email());
        empresa.setRepresentante(dto.representante());
        empresa.setEndereco(enderecoMapper.cadastroEnderecoDtoToEndereco(dto.endereco()));

        return empresa;
    }

}
