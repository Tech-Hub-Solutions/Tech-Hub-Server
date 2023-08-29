package br.com.techhub.server.techub.api.entity.empresa;

public record DadosDetalhamentoEmpresaDto(String nomeEmpresa, String cnpj, String emailContato, String telefoneContato) {

    public DadosDetalhamentoEmpresaDto(Empresa emp) {
        this(emp.getNomeEmpresa(),emp.getCnpj(),emp.getEmailContato(),emp.getTelefoneContato());
    }
}
