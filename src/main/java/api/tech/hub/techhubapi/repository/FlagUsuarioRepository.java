package api.tech.hub.techhubapi.repository;

import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.entity.perfil.flag.FlagUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlagUsuarioRepository extends JpaRepository<FlagUsuario,Integer> {

    @Query("SELECT f.flag FROM FlagUsuario f WHERE f.perfil.id = :idPerfil")
    List<Flag> encontrarFlagPorIdPerfil(Integer idPerfil);
}
