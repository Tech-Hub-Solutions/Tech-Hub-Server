package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.entity.ListaObj;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.conversa.dto.UsuarioConversaDto;
import api.tech.hub.techhubapi.service.perfil.PerfilService;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilDetalhadoDto;
import api.tech.hub.techhubapi.service.usuario.UsuarioMapper;
import api.tech.hub.techhubapi.service.usuario.dto.UsuarioBuscaDto;
import api.tech.hub.techhubapi.service.usuario.UsuarioService;
import api.tech.hub.techhubapi.service.usuario.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Slf4j
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PerfilService perfilService;
    private final UsuarioMapper usuarioMapper;

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        log.info("Fazendo login com email: {}", usuarioLoginDto.email());
        UsuarioTokenDto usuarioTokenDto = this.usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioTokenDto);
    }

    @PostMapping("/verify")
    public ResponseEntity<UsuarioTokenDto> verify(
          @RequestBody @Valid UsuarioVerifyDto usuarioVerifyDto) {
        log.info("Verificando usuário com email: {} e código: {}", usuarioVerifyDto.email(),
              usuarioVerifyDto.code());
        UsuarioTokenDto usuarioTokenDto = this.usuarioService.verify(usuarioVerifyDto);
        return ResponseEntity.status(200).body(usuarioTokenDto);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDetalhadoDto>> listar() {
        List<UsuarioDetalhadoDto> usuarios = this.usuarioService.listar();

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        log.info("Listando usuários");
        return ResponseEntity.status(200).body(usuarios);
    }

    @GetMapping("/lista-obj")
    public ResponseEntity<ListaObj<UsuarioDetalhadoDto>> listarObj() {
        ListaObj<UsuarioDetalhadoDto> usuarios = this.usuarioService.listarObj();

        if (usuarios.getTamanho() == 0) {
            return ResponseEntity.noContent().build();
        }
        log.info("Listando usuários com ListaObj");
        return ResponseEntity.status(200).body(usuarios);
    }

    @GetMapping("/teste")
    public ResponseEntity<ListaObj<UsuarioConversaDto>> listarTeste() {
        ListaObj<UsuarioConversaDto> usuarios = this.usuarioService.listarTeste();

        if (usuarios.getTamanho() == 0) {
            return ResponseEntity.noContent().build();
        }

        log.info("Listando usuários teste com ListaObj");
        return ResponseEntity.status(200).body(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioTokenDto> cadastrarUsuario(
          @RequestBody @Valid UsuarioCriacaoDto dto) {
        UsuarioTokenDto usuarioSalvo = usuarioService.salvarUsuarioCadastro(dto);
        log.info("Cadastrando usuário com email: {}", dto.email());
        return ResponseEntity.status(201).body(usuarioSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioGeralDto> buscarUsuario(@PathVariable Integer id) {
        Usuario usuario = this.usuarioService.buscarPorId(id);

        PerfilDetalhadoDto perfil = perfilService.buscarPerfilDetalhadoPorIdUsuario(
              usuario.getId());

        log.info("Buscando usuário com id: {}", id);
        return ResponseEntity.ok(usuarioMapper.usuarioGeralDtoOf(usuario, perfil));
    }

    @GetMapping("/simple/{id}")
    public ResponseEntity<UsuarioSimpleDto> buscarSimpleUsuario(@PathVariable Integer id) {
        Usuario usuario = this.usuarioService.buscarPorId(id);

        log.info("Buscando usuário simples com id: {}", id);
        return ResponseEntity.ok(new UsuarioSimpleDto(usuario));
    }


    @PutMapping
    public ResponseEntity<UsuarioTokenDto> atualizarUsuarioPorId(
          @RequestBody UsuarioAtualizacaoDto dto) {
        return ResponseEntity.ok(this.usuarioService.atualizarInformacaoUsuarioPorId(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativarUsuario(@PathVariable Integer id) {
        usuarioService.deletarUsuario(id);
        log.info("Desativando usuário com id: {}", id);
        return ResponseEntity.noContent().build();
    }

    // Para utilizar a paginação, passar dois parâmetros na URL: page e size
    // Page é o número da página, começando em 0
    // Size é o tamanho da página
    // Exemplo: http://localhost:8080/usuarios/filtro?page=3&size=1
    // Para ordernar, passar o parâmetro sort na URL
    // Exemplo: http://localhost:8080/usuarios/filtro?page=0&size=10&sort=nome,asc
    @PostMapping("/filtro")
    public ResponseEntity<Page<UsuarioBuscaDto>> listarPor(
          @RequestBody UsuarioFiltroDto usuarioFiltroDto,
          @RequestParam(required = false) String ordem,
          Pageable paginacao
    ) {
        Page<UsuarioBuscaDto> usuarios = this.usuarioService.listarPor(usuarioFiltroDto, paginacao,
              ordem);

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        log.info("Listando usuários por filtro {}", usuarioFiltroDto);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/favoritos")
    public ResponseEntity<Page<UsuarioFavoritoDto>> listarFavoritos(
          Pageable pageable,
          @RequestParam(required = false) String ordem
    ) {
        Page<UsuarioFavoritoDto> usuarios = this.usuarioService.listarFavoritos(pageable, ordem);

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }


}
