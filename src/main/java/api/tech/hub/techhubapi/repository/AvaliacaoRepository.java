package api.tech.hub.techhubapi.repository;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {

    Page<Avaliacao> findAvaliacaoByPerfil(Perfil perfil, Pageable pageable);

    @Query("SELECT a.qtdEstrela, COUNT(*) as quantidade FROM Avaliacao a WHERE a.perfil =:perfil GROUP BY a.qtdEstrela ORDER BY a.qtdEstrela")
    List<Object[]> quantidadeDeEstrelasAgrupadasPorEstrela(Perfil perfil);
}
