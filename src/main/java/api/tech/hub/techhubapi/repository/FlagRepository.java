package api.tech.hub.techhubapi.repository;

import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlagRepository extends JpaRepository<Flag,Integer> {
}
