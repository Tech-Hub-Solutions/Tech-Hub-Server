package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.repository.UsuarioRepository;
import api.tech.hub.techhubapi.service.usuario.UsuarioMapper;
import api.tech.hub.techhubapi.service.usuario.UsuarioService;
import api.tech.hub.techhubapi.service.usuario.dto.UsuarioAtualizacaoDto;
import api.tech.hub.techhubapi.service.usuario.dto.UsuarioLoginDto;
import api.tech.hub.techhubapi.service.usuario.dto.UsuarioTokenDto;
import api.tech.hub.techhubapi.service.usuario.dto.UsuarioCriacaoDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioService usuarioService;

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
        Usuario usuarioValidado = usuarioService.verificarUsuarioCadastro(dto);
        return ResponseEntity.created(null).body(this.repository.save(usuarioValidado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Integer id) {
        return ResponseEntity.of(this.repository.findUsuarioByIdAndIsAtivoTrue(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuarioPorId(@PathVariable Integer id,
                                                         @RequestBody UsuarioAtualizacaoDto dto) {
        Usuario usuarioAtualizacaoValidado = usuarioService.validarAtualizacaoUsuario(id,dto);
        return ResponseEntity.ok(this.repository.save(usuarioAtualizacaoValidado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativarUsuario (@PathVariable Integer id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
