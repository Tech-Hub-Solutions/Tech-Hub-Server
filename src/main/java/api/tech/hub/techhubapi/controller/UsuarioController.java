package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.entity.ListaObj;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.conversa.dto.UsuarioConversaDto;
import api.tech.hub.techhubapi.service.usuario.dto.UsuarioBuscaDto;
import api.tech.hub.techhubapi.service.usuario.UsuarioService;
import api.tech.hub.techhubapi.service.usuario.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        UsuarioTokenDto usuarioTokenDto = this.usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioTokenDto);
    }

    @GetMapping
    public ResponseEntity<ListaObj<UsuarioDetalhadoDto>> listar() {
        ListaObj<UsuarioDetalhadoDto> usuarios = this.usuarioService.listar();

        if (usuarios.getTamanho() == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(200).body(usuarios);
    }

    @GetMapping("/teste")
    public ResponseEntity<ListaObj<UsuarioConversaDto>> listarTeste() {
        ListaObj<UsuarioConversaDto> usuarios = this.usuarioService.listarTeste();

        if (usuarios.getTamanho() == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(200).body(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody @Valid UsuarioCriacaoDto dto) {
        Usuario usuarioSalvo = usuarioService.salvarUsuarioCadastro(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuarioSalvo.getId())
                .toUri();

        return ResponseEntity.created(uri).body(usuarioSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDetalhadoDto> buscarUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(this.usuarioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDetalhadoDto> atualizarUsuarioPorId(@PathVariable Integer id,
                                                                     @RequestBody UsuarioAtualizacaoDto dto) {
        return ResponseEntity.ok(this.usuarioService.atualizarInformacaoUsuarioPorId(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativarUsuario(@PathVariable Integer id) {
        usuarioService.deletarUsuario(id);
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
            Pageable paginacao
    ) {
        Page<UsuarioBuscaDto> usuarios = this.usuarioService.listarPor(usuarioFiltroDto, paginacao);

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/favoritos")
    public ResponseEntity<Page<UsuarioFavoritoDto>> listarFavoritos(
            Pageable pageable
    ) {
        Page<UsuarioFavoritoDto> usuarios = this.usuarioService.listarFavoritos(pageable);

        if(usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }
}
