package api.tech.hub.techhubapi.repository;

import api.tech.hub.techhubapi.entity.conversa.Conversa;
import api.tech.hub.techhubapi.entity.conversa.Mensagem;
import api.tech.hub.techhubapi.entity.conversa.Sala;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.conversa.dto.ConversaDto;
import api.tech.hub.techhubapi.service.conversa.dto.MensagemASerEnviadaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversaRepository extends JpaRepository<Conversa, Integer> {
    boolean existsByUsuarioAndSalaAndIsAtivoTrue(Usuario usuario, Sala sala);

    Conversa findByUsuarioAndSala(Usuario usuarioAutenticado, Sala sala);



    List<Conversa> findByUsuario(Usuario usuarioAutenticado);

    List<Conversa> findByAndSalaIn(List<Sala> salasDoUsuario);
}
