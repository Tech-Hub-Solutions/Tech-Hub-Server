package api.tech.hub.techhubapi.repository;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario> {
    Optional<Usuario> findByEmail(String username);

    Optional<Usuario> findUsuarioByEmailAndNumeroCadastroPessoa(String email, String numeroCadastroPessoa);

    List<Usuario> findByIdIn(List<Integer> ids);

    Optional<Usuario> findUsuarioByIdAndIsAtivoTrue(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.perfil = :perfil WHERE u.id = :id")
    void atualizarPerfilDoUsuario(Integer id, Perfil perfil);


//    @Modifying
//    @Transactional
//    @Query("UPDATE Usuario u SET u.perfil = :perfil WHERE u.id = :idUsuario")
//    void atualizarPerfilDoUsuario(Integer idUsuario, Perfil perfil);

}
