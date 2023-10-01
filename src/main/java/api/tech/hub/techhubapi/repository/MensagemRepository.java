package api.tech.hub.techhubapi.repository;

import api.tech.hub.techhubapi.entity.conversa.Conversa;
import api.tech.hub.techhubapi.entity.conversa.Mensagem;
import api.tech.hub.techhubapi.entity.conversa.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {
    Optional<Mensagem> findFirstBySalaOrderByDtMensagemDesc(Sala sala);
}
