package api.tech.hub.techhubapi.service.referencia;

import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;
import api.tech.hub.techhubapi.service.referencia.dto.ReferenciaDetalhadoDto;
import api.tech.hub.techhubapi.service.referencia.dto.ReferenciaPerfilCriacaoDto;
import org.springframework.stereotype.Component;

@Component
public class ReferenciaPerfilMapper {
    public ReferenciaPerfil of(ReferenciaPerfilCriacaoDto dto) {
        ReferenciaPerfil referenciaPerfil = new ReferenciaPerfil();

        referenciaPerfil.setFavorito(dto.isFavorito());
        referenciaPerfil.setRecomendado(dto.isRecomendado());

        return referenciaPerfil;
    }

    public ReferenciaDetalhadoDto dtoOf(ReferenciaPerfil referenciaPerfilSalvo) {
        return new ReferenciaDetalhadoDto(referenciaPerfilSalvo);
    }
}