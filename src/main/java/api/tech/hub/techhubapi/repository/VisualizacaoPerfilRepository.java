package api.tech.hub.techhubapi.repository;

import api.tech.hub.techhubapi.entity.VisualizacaoPerfil;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface VisualizacaoPerfilRepository extends JpaRepository<VisualizacaoPerfil, Integer>{

    Optional<VisualizacaoPerfil> findByDtHoraBetweenAndUsuarioAndPerfil(LocalDateTime comeco, LocalDateTime fim, Usuario usuarioLogado, Perfil perfil);

    Integer countByDtHoraBetweenAndPerfil(LocalDateTime inicioSemana, LocalDateTime fimSemana, Perfil perfil);
}
