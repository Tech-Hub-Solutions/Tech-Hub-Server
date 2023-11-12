package api.tech.hub.techhubapi.service.referencia;

import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.referencia.dto.ReferenciaDetalhadoDto;
import api.tech.hub.techhubapi.service.referencia.dto.ReferenciaPerfilCriacaoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public Boolean retornarIsFavorito(Usuario usuarioLogado, List<ReferenciaPerfil> referenciaPerfilList) {
        return referenciaPerfilList.stream()
                .anyMatch(referenciaPerfil ->
                        referenciaPerfil.getAvaliador().getUsuario().equals(usuarioLogado) && referenciaPerfil.isFavorito()
                );
    }

    public long retornarQtdFavoritos(Usuario usuarioLogado, List<ReferenciaPerfil> referenciaPerfilList) {
        return referenciaPerfilList.stream()
                .filter(referenciaPerfil ->
                        referenciaPerfil.getAvaliador().getUsuario().equals(usuarioLogado) && referenciaPerfil.isFavorito()
                ).count();
    }

    public Boolean retornarIsRecomendado(Usuario usuarioLogado, List<ReferenciaPerfil> referenciaPerfilList) {
        return referenciaPerfilList.stream()
                .anyMatch(referenciaPerfil ->
                        referenciaPerfil.getAvaliador().getUsuario().equals(usuarioLogado) && referenciaPerfil.isRecomendado()
                );
    }

    public long retornarQtdRecomendacoes(Usuario usuarioLogado, List<ReferenciaPerfil> referenciaPerfilList) {
        return referenciaPerfilList.stream()
                .filter(referenciaPerfil ->
                        referenciaPerfil.getAvaliador().getUsuario().equals(usuarioLogado) && referenciaPerfil.isRecomendado()
                ).count();
    }
}
