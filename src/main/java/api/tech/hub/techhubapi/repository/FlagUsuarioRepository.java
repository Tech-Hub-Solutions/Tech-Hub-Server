package api.tech.hub.techhubapi.repository;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.entity.perfil.flag.FlagUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlagUsuarioRepository extends JpaRepository<FlagUsuario,Integer> {

    @Query("SELECT f.flag FROM FlagUsuario f WHERE f.perfil = :perfil")
    List<Flag> encontrarFlagPorPerfil(Perfil perfil);

    List<FlagUsuario> findFlagUsuarioByPerfil(Perfil perfil);

    void deleteFlagUsuarioByPerfil(Perfil perfil);
}
