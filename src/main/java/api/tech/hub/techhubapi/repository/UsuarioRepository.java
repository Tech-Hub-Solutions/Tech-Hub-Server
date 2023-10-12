package api.tech.hub.techhubapi.repository;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String username);

    Optional<Usuario> findUsuarioByEmailAndNumeroCadastroPessoa(String email, String numeroCadastroPessoa);

    Optional<Usuario> findUsuarioByIdAndIsAtivoTrue(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.perfil = :perfil WHERE u.id = :idUsuario")
    void atualizarPerfilDoUsuario(Integer idUsuario, Perfil perfil);

}
