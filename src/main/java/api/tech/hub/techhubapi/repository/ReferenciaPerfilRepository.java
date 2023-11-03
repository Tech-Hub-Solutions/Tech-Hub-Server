package api.tech.hub.techhubapi.repository;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReferenciaPerfilRepository extends JpaRepository<ReferenciaPerfil, Integer> {

    Boolean existsByAvaliadoAndAvaliador(Perfil avaliado, Perfil Avaliador);

    ReferenciaPerfil findReferenciaPerfilByAvaliadoAndAvaliador(Perfil avaliado, Perfil Avaliador);

    @Query("select r from ReferenciaPerfil r where r.avaliado = :avaliado and r.avaliador = :avaliador")
    ReferenciaPerfil teste(Perfil avaliado, Perfil avaliador);

    List<ReferenciaPerfil> findByAvaliado(Perfil avaliado);

    List<ReferenciaPerfil> findByAvaliador(Perfil perfil);

    Page<ReferenciaPerfil> findByAvaliadorAndIsFavoritoTrue(Perfil perfil, Pageable pageable);
}
