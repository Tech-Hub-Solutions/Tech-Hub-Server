package api.tech.hub.techhubapi.service.usuario;

import api.tech.hub.techhubapi.configuration.security.jwt.GerenciadorTokenJwt;
import api.tech.hub.techhubapi.entity.Arquivo;
import api.tech.hub.techhubapi.entity.ListaObj;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.repository.PerfilRepository;
import api.tech.hub.techhubapi.repository.UsuarioRepository;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import api.tech.hub.techhubapi.service.conversa.dto.UsuarioConversaDto;
import api.tech.hub.techhubapi.service.usuario.dto.*;
import api.tech.hub.techhubapi.service.usuario.specification.UsuarioSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private PerfilRepository perfilRepository;

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

    public Usuario salvarUsuarioCadastro(UsuarioCriacaoDto dto) {
        Usuario validado = usuarioMapper.of(dto);
        Optional<Usuario> usuarioOpt = this.usuarioRepository.findUsuarioByEmailAndNumeroCadastroPessoa(validado.getEmail(),
                validado.getNumeroCadastroPessoa());

        if (usuarioOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(409), "Usuário já existe!");
        }

        Perfil perfilUsuario = this.perfilRepository.save(new Perfil());
        Usuario usuarioSalvo = this.usuarioRepository.save(validado);

        this.perfilRepository.atualizarUsuarioDoPerfil(perfilUsuario.getId(),usuarioSalvo);
        this.usuarioRepository.atualizarPerfilDoUsuario(usuarioSalvo.getId(), perfilUsuario);

        return usuarioSalvo;
    }

    public UsuarioDetalhadoDto atualizarInformacaoUsuarioPorId(Integer id, UsuarioAtualizacaoDto dto) {
        Usuario usuario = usuarioRepository.findUsuarioByIdAndIsAtivoTrue(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));

        return usuarioMapper.dtoOf(
                this.usuarioRepository.save(
                        usuarioMapper.of(usuario, dto)
                )
        );
    }

    public UsuarioDetalhadoDto buscarPorId(Integer id) {
        return usuarioMapper.dtoOf(
                this.usuarioRepository.findUsuarioByIdAndIsAtivoTrue(id).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
                )
        );
    }

    public void deletarUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findUsuarioByIdAndIsAtivoTrue(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Usuário não encontrado"));

        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }

    public ListaObj<UsuarioDetalhadoDto> listar() {
        ListaObj<UsuarioDetalhadoDto> usuarios = new ListaObj<>(10);


        for (Usuario u : this.usuarioRepository.findAll()) {
            usuarios.adiciona(new UsuarioDetalhadoDto(u));
        }

        // Ordernar por nome
        for (int i = 0; i < usuarios.getTamanho() - 1; i++) {
            UsuarioDetalhadoDto menorNome = usuarios.getElemento(i);
            for (int j = i + 1; j < usuarios.getTamanho(); j++) {
                UsuarioDetalhadoDto usuario = usuarios.getElemento(j);
                if (usuario.nome().compareTo(menorNome.nome()) < 0) {
                    menorNome = usuario;
                }
            }
            UsuarioDetalhadoDto aux = usuarios.getElemento(i);
            usuarios.setElemento(i, menorNome);
            usuarios.setElemento(usuarios.getTamanho() - 1, aux);

        }


        return usuarios;
    }

    public ListaObj<UsuarioConversaDto> listarTeste() {
        ListaObj<UsuarioConversaDto> usuarios = new ListaObj<>(10);


        for (Usuario u : this.usuarioRepository.findAll()) {
            usuarios.adiciona(new UsuarioConversaDto(u));
        }

        return usuarios;
    }

    public Page<UsuarioBuscaDto> listarPor(UsuarioFiltroDto usuarioFiltroDto, Pageable pageable) {
        Specification<Usuario> specification = Specification
                .allOf(
                        UsuarioSpecification.hasArea(usuarioFiltroDto.area()),
                        UsuarioSpecification.hasPrecoBetween(usuarioFiltroDto.precoMin(), usuarioFiltroDto.precoMax()),
                        UsuarioSpecification.hasFlags(usuarioFiltroDto.tecnologias())
                );

        return usuarioRepository.findAll(specification, pageable)
                .map(UsuarioBuscaDto::new);
    }


}
