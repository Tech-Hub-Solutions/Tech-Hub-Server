package api.tech.hub.techhubapi.repository;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReferenciaPerfilRepository extends JpaRepository<ReferenciaPerfil, Integer> {

    List<ReferenciaPerfil> findByAvaliado(Perfil avaliado);

    Page<ReferenciaPerfil> findByAvaliador(Perfil perfil, Pageable pageable);
}
