package api.tech.hub.techhubapi.repository;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String username);

    Optional<Usuario> findUsuarioByEmailAndNumeroCadastroPessoa(String email, String numeroCadastroPessoa);

    Optional<Usuario> findUsuarioByIdAndIsAtivoTrue(Integer id);
}
