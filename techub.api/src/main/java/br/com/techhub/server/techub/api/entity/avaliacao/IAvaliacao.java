package br.com.techhub.server.techub.api.entity.avaliacao;

import br.com.techhub.server.techub.api.entity.empresa.Empresa;
import br.com.techhub.server.techub.api.entity.freelancer.Freelancer;

public interface IAvaliacao {

    Avaliacao avaliar(Empresa empresa, Freelancer freelancer, DadosAvaliacaoDto dto);
}
