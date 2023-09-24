package api.tech.hub.techhubapi.service.usuario;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.usuario.dto.UsuarioCriacaoDto;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario usuarioCriacaoDtoToUsuario(UsuarioCriacaoDto dto) {
        Usuario usuario = new Usuario();

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setNumeroCadastroPessoa(dto.numeroCadastroPessoa());
        usuario.setPais(dto.pais());
        usuario.setFuncao(dto.funcao());
        usuario.setAtivo(true);

        return usuario;
    }

}
