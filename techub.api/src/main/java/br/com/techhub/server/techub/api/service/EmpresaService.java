package br.com.techhub.server.techub.api.service;

import br.com.techhub.server.techub.api.entity.avaliacao.Avaliacao;
import br.com.techhub.server.techub.api.entity.avaliacao.DadosAvaliacaoDto;
import br.com.techhub.server.techub.api.entity.avaliacao.IAvaliacao;
import br.com.techhub.server.techub.api.entity.empresa.Empresa;
import br.com.techhub.server.techub.api.entity.freelancer.Freelancer;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmpresaService implements IAvaliacao {
    @Override
    public Avaliacao avaliar(Empresa empresa, Freelancer freelancer, DadosAvaliacaoDto dto) {
        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setAutor(empresa.getNomeEmpresa());
        avaliacao.setDate(LocalDate.now());
        avaliacao.setNota(dto.nota());
        avaliacao.setComentario(dto.comentario());

        return avaliacao;
    }
}
