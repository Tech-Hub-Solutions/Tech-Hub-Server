package api.tech.hub.techhubapi.repository;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReferenciaPerfilRepository extends JpaRepository<ReferenciaPerfil, Integer> {

    Boolean existsByAvaliadoAndAvaliador(Perfil avaliado, Perfil Avaliador);

    ReferenciaPerfil findReferenciaPerfilByAvaliadoAndAvaliador(Perfil avaliado, Perfil Avaliador);

    List<ReferenciaPerfil> findByAvaliador(Perfil perfil);


    int countByAvaliadoIdAndIsRecomendadoTrue(Integer id);
}
