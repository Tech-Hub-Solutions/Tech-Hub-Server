package api.tech.hub.techhubapi.repository;

import api.tech.hub.techhubapi.entity.views.VwEmpresasInteressadas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VwEmpresasInteressadasRepository extends JpaRepository<VwEmpresasInteressadas, Integer> {

    List<VwEmpresasInteressadas> findTop5ByAvaliadoId(Integer id);
}
