package br.com.techhub.server.techub.api.service.mapper;

import br.com.techhub.server.techub.api.entity.endereco.DadosCadastroEndereco;
import br.com.techhub.server.techub.api.entity.endereco.Endereco;
import org.springframework.stereotype.Service;

@Service
public class EnderecoMapper {

    public Endereco cadastroEnderecoDtoToEndereco (DadosCadastroEndereco dto){
        Endereco end = new Endereco();
        end.setCep(dto.cep());
        end.setNumero(dto.numero());
        end.setComplemento(dto.complemento());

        return end;
    }
}
