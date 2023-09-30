package api.tech.hub.techhubapi.repository;

import api.tech.hub.techhubapi.entity.conversa.Mensagem;
import api.tech.hub.techhubapi.entity.conversa.Sala;
import api.tech.hub.techhubapi.service.conversa.dto.MensagemASerEnviadaDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalaRepository extends JpaRepository<Sala,Integer> {
    Optional<Sala> findByRoomCode(String roomCode);

}
