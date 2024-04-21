package api.tech.hub.techhubapi.service.usuario;

import api.tech.hub.techhubapi.configuration.security.jwt.GerenciadorTokenJwt;
import api.tech.hub.techhubapi.entity.ListaObj;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.entity.usuario.UsuarioFuncao;
import api.tech.hub.techhubapi.repository.FlagRepository;
import api.tech.hub.techhubapi.repository.PerfilRepository;
import api.tech.hub.techhubapi.repository.UsuarioRepository;
import api.tech.hub.techhubapi.service.arquivo.ftp.FtpService;
import api.tech.hub.techhubapi.service.conversa.dto.UsuarioConversaDto;
import api.tech.hub.techhubapi.service.usuario.autenticacao.AutenticacaoService;
import api.tech.hub.techhubapi.service.usuario.dto.*;
import api.tech.hub.techhubapi.service.usuario.specification.UsuarioSpecification;
import lombok.RequiredArgsConstructor;
import org.jboss.aerogear.security.otp.Totp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;
    private final AutenticacaoService autenticacaoService;
    private final UsuarioMapper usuarioMapper;
    private final PerfilRepository perfilRepository;
    private final FlagRepository flagRepository;
    private final FtpService ftpService;

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {

        String token = autenticar(usuarioLoginDto.email(), usuarioLoginDto.senha());

        Usuario usuarioAutenticado = usuarioRepository.findByEmailAndIsAtivoTrue(
                    usuarioLoginDto.email())
              .orElseThrow(() ->
                    new ResponseStatusException(HttpStatusCode.valueOf(404),
                          "Email do usuário não encontrado")
              );

        if (usuarioAutenticado.isUsing2FA() && !usuarioAutenticado.isValid2FA()) {
            usuarioAutenticado.setUsing2FA(false);
            usuarioAutenticado.setSecret(null);
            usuarioRepository.save(usuarioAutenticado);
        }

        if (usuarioAutenticado.isUsing2FA()) {
            token = "";
        }

        return UsuarioMapper.of(usuarioAutenticado, token, "", "", ftpService);
    }


    public UsuarioTokenDto verify(UsuarioVerifyDto usuarioVerifyDto) {

        Usuario usuario = this.usuarioRepository.findByEmailAndIsAtivoTrue(usuarioVerifyDto.email())
              .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        String token = autenticar(usuarioVerifyDto.email(), usuarioVerifyDto.senha());

        if (!usuario.isUsing2FA()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                  "Usuário não está usando 2FA");
        }

        String verificationCode = usuarioVerifyDto.code();
        Totp totp = new Totp(usuario.getSecret());

        if (!totp.verify(verificationCode)) {
            throw new BadCredentialsException("Código de verificação inválido");
        }

        usuario.setValid2FA(true);
        usuarioRepository.save(usuario);

        return new UsuarioTokenDto(
              usuario,
              token,
              "", "", ftpService);
    }

    public UsuarioTokenDto salvarUsuarioCadastro(UsuarioCriacaoDto dto) {
        Usuario validado = usuarioMapper.of(dto);
        Optional<Usuario> usuarioOpt = this.usuarioRepository.findUsuarioByEmailAndNumeroCadastroPessoa(
              validado.getEmail(),
              validado.getNumeroCadastroPessoa());

        if (usuarioOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(409), "Usuário já existe!");
        }

        Perfil perfilUsuario = this.perfilRepository.save(new Perfil());
        Usuario usuarioSalvo = this.usuarioRepository.save(validado);

        this.perfilRepository.atualizarUsuarioDoPerfil(perfilUsuario.getId(), usuarioSalvo);
        this.usuarioRepository.atualizarPerfilDoUsuario(usuarioSalvo.getId(), perfilUsuario);

        String secretQrCodeUrl = "";
        String secret = "";
        String token = autenticar(usuarioSalvo.getEmail(), dto.senha());

        if (usuarioSalvo.isUsing2FA()) {
            secretQrCodeUrl = autenticacaoService.generateQRUrl(usuarioSalvo);
            secret = usuarioSalvo.getSecret();
            token = "";
        }

        return UsuarioMapper.of(usuarioSalvo, token, secretQrCodeUrl, secret, ftpService);
    }

    public String autenticar(String email, String senha) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
              email, senha
        );
        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return gerenciadorTokenJwt.generateToken(authentication);
    }

    public UsuarioTokenDto atualizarInformacaoUsuarioPorId(UsuarioAtualizacaoDto dto) {
        Usuario usuario = this.autenticacaoService.getUsuarioFromUsuarioDetails();
        var oldUserUses2FA = usuario.isUsing2FA();

        Usuario usuarioValidado = usuarioMapper.of(usuario, dto);

        boolean hasConflit = this.usuarioRepository.existsByEmailAndIdNot(
              usuarioValidado.getEmail(),
              usuario.getId());

        if (hasConflit) {
            throw new ResponseStatusException(
                  HttpStatus.CONFLICT, "Este email já esta em uso!"
            );
        }

        String secretQrCodeUrl = "";
        String secret = "";
        String token = "";

        if (dto.isUsing2FA() && !oldUserUses2FA) {
            secretQrCodeUrl = autenticacaoService.generateQRUrl(usuario);
            secret = usuario.getSecret();
        } else {
            token = autenticar(usuario.getEmail(), dto.senha());
        }
        usuario = this.usuarioRepository.save(usuarioValidado);

        return UsuarioMapper.of(
              usuario,
              token,
              secretQrCodeUrl,
              secret,
              ftpService
        );
    }

    public Usuario buscarPorId(Integer id) {
        return this.usuarioRepository.findUsuarioByIdAndIsAtivoTrue(id).orElseThrow(
              () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
        );
    }

    public void deletarUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findUsuarioByIdAndIsAtivoTrue(id)
              .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404),
                    "Usuário não encontrado"));

        perfilRepository.delete(usuario.getPerfil());
        usuarioRepository.delete(usuario);
    }

    public List<UsuarioDetalhadoDto> listar() {
        return this.listarUsuarios().stream().map(usuarioMapper::dtoOf).toList();
    }

    public List<Usuario> listarUsuarios() {
        return this.usuarioRepository.findAll();
    }

    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return this.usuarioRepository.findAll(pageable);
    }

    public ListaObj<UsuarioDetalhadoDto> listarObj() {
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
            usuarios.adiciona(new UsuarioConversaDto(u, ftpService));
        }

        return usuarios;
    }

    public Page<UsuarioBuscaDto> listarPor(UsuarioFiltroDto usuarioFiltroDto, Pageable pageable,
          String ordem) {

        List<Flag> flags = null;
        if (usuarioFiltroDto.tecnologiasIds() != null && !usuarioFiltroDto.tecnologiasIds()
              .isEmpty()) {
            flags = this.flagRepository.findByIdIn(usuarioFiltroDto.tecnologiasIds());
        }

        Specification<Usuario> specification = Specification
              .allOf(
                    UsuarioSpecification.hasFuncao(UsuarioFuncao.FREELANCER),
                    UsuarioSpecification.hasNome(usuarioFiltroDto.nome()),
                    UsuarioSpecification.hasArea(usuarioFiltroDto.area()),
                    UsuarioSpecification.hasPrecoBetween(usuarioFiltroDto.precoMin(),
                          usuarioFiltroDto.precoMax()),
                    UsuarioSpecification.hasFlags(flags),
                    UsuarioSpecification.sort(ordem)
              );

        return this.usuarioRepository.findAll(specification, pageable)
              .map(u -> new UsuarioBuscaDto(u, ftpService));
    }


    public Page<UsuarioFavoritoDto> listarFavoritos(Pageable pageable, String ordem) {
        Usuario usuarioLogado = this.autenticacaoService.getUsuarioFromUsuarioDetails();

        if (usuarioLogado.getFuncao().equals(UsuarioFuncao.FREELANCER)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                  "Freelancer não pode ter favoritos");
        }

        Specification<Usuario> specification = Specification.allOf(
              UsuarioSpecification.getFavoritos(usuarioLogado.getPerfil()),
              UsuarioSpecification.sort(ordem)
        );

        Page<Usuario> usuarios = this.usuarioRepository.findAll(specification, pageable);
        Page<Perfil> favoritos = usuarios.map(Usuario::getPerfil);

        return favoritos.map(p -> new UsuarioFavoritoDto(p, ftpService));

    }

    public void validarFuncaoUsuario(Usuario usuario, UsuarioFuncao funcao) {
        if (!usuario.getFuncao().equals(funcao)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                  "Usuário não tem permissão para acessar este recurso");
        }
    }

    public List<Usuario> buscarPorIds(List<Integer> idsEmpresasInteressadas) {
        return this.usuarioRepository.findByIdIn(idsEmpresasInteressadas);
    }

    public Page<Usuario> listarUsuariosFreelancers(Pageable pageable) {
        return this.usuarioRepository.findAllByFuncaoAndIsAtivoTrueAndEmailNotContains(
              UsuarioFuncao.FREELANCER,
              "example@example",
              pageable);
    }
}
