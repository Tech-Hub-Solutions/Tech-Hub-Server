package api.tech.hub.techhubapi.repository;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {

    List<Avaliacao> findAvaliacaoByPerfilId(Integer id);
}
