package api.tech.hub.techhubapi.service.usuario;

import api.tech.hub.techhubapi.configuration.security.jwt.GerenciadorTokenJwt;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.repository.UsuarioRepository;
import api.tech.hub.techhubapi.service.usuario.dto.UsuarioAtualizacaoDto;
import api.tech.hub.techhubapi.service.usuario.dto.UsuarioCriacaoDto;
import api.tech.hub.techhubapi.service.usuario.dto.UsuarioLoginDto;
import api.tech.hub.techhubapi.service.usuario.dto.UsuarioTokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioMapper usuarioMapper;

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.email(), usuarioLoginDto.senha()
        );
        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado = usuarioRepository.findByEmail(usuarioLoginDto.email())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatusCode.valueOf(404), "Email do usuário não encontrado")
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }

    public Usuario verificarUsuarioCadastro(UsuarioCriacaoDto dto) {
        Usuario validado = usuarioMapper.of(dto);
        Optional<Usuario> usuarioOpt = this.usuarioRepository.findUsuarioByEmailAndNumeroCadastroPessoa(validado.getEmail(),
                validado.getNumeroCadastroPessoa());

        if (usuarioOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(409), "Usuário já existe!");
        }

        return validado;
    }

    public Usuario validarAtualizacaoUsuario(Integer id, UsuarioAtualizacaoDto dto) {
        Usuario usuario = usuarioRepository.findUsuarioByIdAndIsAtivoTrue(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Usuário não encontrado!"));

        return usuarioMapper.of(usuario, dto);
    }

    public void deletarUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findUsuarioByIdAndIsAtivoTrue(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404),"Usuário não encontrado"));

        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }

    public List<Usuario> listar() {
        return this.usuarioRepository.findAll();
    }
}
