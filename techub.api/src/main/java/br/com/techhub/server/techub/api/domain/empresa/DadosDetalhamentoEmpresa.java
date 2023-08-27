package br.com.techhub.server.techub.api.domain.empresa;

public record DadosDetalhamentoEmpresa(String nomeEmpresa, String cnpj, String emailContato, String telefoneContato) {

    public DadosDetalhamentoEmpresa(Empresa emp) {
        this(emp.getNomeEmpresa(),emp.getCnpj(),emp.getEmailContato(),emp.getTelefoneContato());
    }
}
