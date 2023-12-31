package api.tech.hub.techhubapi.service.usuario;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilDetalhadoDto;
import api.tech.hub.techhubapi.service.usuario.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioDetalhadoDto dtoOf (Usuario usuario) {
        return new UsuarioDetalhadoDto(usuario);
    }

    public Usuario of(UsuarioCriacaoDto dto) {
        Usuario usuario = new Usuario();

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setNumeroCadastroPessoa(dto.numeroCadastroPessoa());
        usuario.setPais(dto.pais());
        usuario.setFuncao(dto.funcao());
        usuario.setAtivo(true);

        return usuario;
    }

    public Usuario of(Usuario usuario, UsuarioAtualizacaoDto dto) {

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setPais(dto.pais());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));

        return usuario;
    }

    public static UsuarioTokenDto of(Usuario usuario, String token) {
        return new UsuarioTokenDto(usuario, token);
    }


    public UsuarioGeralDto usuarioGeralDtoOf(Usuario usuario, PerfilDetalhadoDto perfil) {
        UsuarioDetalhadoDto dto = this.dtoOf(usuario);
        return new UsuarioGeralDto(dto,perfil);
    }
}
