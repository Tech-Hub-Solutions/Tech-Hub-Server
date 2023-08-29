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

    public Empresa cadastroEmpresaDtoToEmpresa (DadosCadastroEmpresaDto dto){
        Empresa emp = new Empresa();
        emp.setNomeEmpresa(dto.nomeEmpresa());
        emp.setRazaoSocial(dto.razaoSocial());
        emp.setCnpj(dto.cnpj());
        emp.setTelefoneContato(dto.telefoneContato());
        emp.setEmailContato(dto.emailContato());
        emp.setRepresentante(dto.representante());
        return emp;
    }

    public DadosDetalhamentoEmpresaDto empresaToDetalhamentoDto (Empresa dados){
        return new DadosDetalhamentoEmpresaDto(
                dados.getNomeEmpresa(),
                dados.getCnpj(),
                dados.getEmailContato(),
                dados.getTelefoneContato()
        );
    }

    public Empresa atualizacaoEmpresaDtoToEmpresa(Empresa emp ,DadosAtualizacaoEmpresaDto dto){
        emp.setTelefoneContato(dto.telefoneContato());
        emp.setEmailContato(dto.emailContato());
        emp.setRepresentante(dto.representante());
        emp.setEndereco(enderecoMapper.cadastroEnderecoDtoToEndereco(dto.endereco()));

        return emp;
    }

}
