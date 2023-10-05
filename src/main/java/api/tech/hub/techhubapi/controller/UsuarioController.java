package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.usuario.UsuarioService;
import api.tech.hub.techhubapi.service.usuario.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        UsuarioTokenDto usuarioTokenDto = this.usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioTokenDto);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = this.usuarioService.listar();

        if(usuarios.isEmpty()) {
            return  ResponseEntity.noContent().build();
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
        return ResponseEntity.ok(this.usuarioService.atualizarInformacaoUsuarioPorId(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativarUsuario (@PathVariable Integer id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
