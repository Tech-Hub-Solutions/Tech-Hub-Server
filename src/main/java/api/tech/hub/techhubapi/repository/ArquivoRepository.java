package api.tech.hub.techhubapi.repository;

import api.tech.hub.techhubapi.entity.conversa.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArquivoRepository extends JpaRepository<Arquivo,Integer> {
}
