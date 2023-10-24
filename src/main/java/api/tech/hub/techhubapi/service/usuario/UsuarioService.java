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

        this.perfilRepository.atualizarUsuarioDoPerfil(perfilUsuario.getId(), usuarioSalvo);
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
        ListaObj<UsuarioDetalhadoDto> usuarios = new ListaObj<>(40);


        for (Usuario u : this.usuarioRepository.findAll()) {
            usuarios.adiciona(new UsuarioDetalhadoDto(u));
        }

        // OrdernarPorNome (SELECTION SORT)
        for (int i = 0; i < usuarios.getTamanho() - 1; i++) {
            int menor = i;
            String menorNome = usuarios.getElemento(menor).nome();

            for (int j = i + 1; j < usuarios.getTamanho(); j++) {
                String nomeAtual = usuarios.getElemento(j).nome();
                if (nomeAtual.compareTo(menorNome) < 0) {
                    menor = j;
                    menorNome = usuarios.getElemento(menor).nome();
                }
            }

            if (menor != i) {
                UsuarioDetalhadoDto aux = usuarios.getElemento(i);
                usuarios.setElemento(i, usuarios.getElemento(menor));
                usuarios.setElemento(menor, aux);
            }
        }

        System.out.println("Implementação pesquisa binária");
        System.out.println("Busca pelo nome Murilo");
        System.out.println(pesquisaBinaria(usuarios, "Murilo"));
        System.out.println("Pesquisar pelo nome Yoshi");
        System.out.println(pesquisaBinaria(usuarios, "Yoshi"));


        return usuarios;
    }

    public int pesquisaBinaria(ListaObj<UsuarioDetalhadoDto> usuarios, String nome) {
        int inicio = 0;
        int fim = usuarios.getTamanho() - 1;
        int meio;

        while (inicio <= fim) {
            meio = (inicio + fim) / 2;

            if (usuarios.getElemento(meio).nome().equals(nome)) {
                return meio;
            } else if (usuarios.getElemento(meio).nome().compareTo(nome) < 0) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }

        return -1;
    }

    public ListaObj<UsuarioConversaDto> listarTeste() {
        ListaObj<UsuarioConversaDto> usuarios = new ListaObj<>(40);


        for (Usuario u : this.usuarioRepository.findAll()) {
            usuarios.adiciona(new UsuarioConversaDto(u));
        }

        return usuarios;
    }

    public Page<UsuarioBuscaDto> listarPor(UsuarioFiltroDto usuarioFiltroDto, Pageable pageable) {
        Specification<Usuario> specification = Specification
                .allOf(
                        UsuarioSpecification.hasNome(usuarioFiltroDto.nome()),
                        UsuarioSpecification.hasArea(usuarioFiltroDto.area()),
                        UsuarioSpecification.hasPrecoBetween(usuarioFiltroDto.precoMin(), usuarioFiltroDto.precoMax()),
                        UsuarioSpecification.hasFlags(usuarioFiltroDto.tecnologias())
                );

        return usuarioRepository.findAll(specification, pageable)
                .map(UsuarioBuscaDto::new);
    }


}
