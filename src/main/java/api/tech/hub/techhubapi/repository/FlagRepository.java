package api.tech.hub.techhubapi.repository;

import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.service.flag.dto.FlagDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlagRepository extends JpaRepository<Flag,Integer> {
    List<Flag> findByIdIn(List<Integer> ids);
}
