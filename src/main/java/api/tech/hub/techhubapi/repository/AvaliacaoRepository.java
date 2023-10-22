package api.tech.hub.techhubapi.repository;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.service.avaliacao.dto.AvaliacaoTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {

    List<Avaliacao> findAvaliacaoByPerfil(Perfil perfil);

    @Query("SELECT a.qtdEstrela, COUNT(*) as quantidade FROM Avaliacao a WHERE a.perfil =:perfil GROUP BY a.qtdEstrela ORDER BY a.qtdEstrela")
    List<Object[]> quantidadeDeEstrelasAgrupadasPorEstrela(Perfil perfil);
}
