package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.repository.UsuarioRepository;
import api.tech.hub.techhubapi.service.usuario.UsuarioMapper;
import api.tech.hub.techhubapi.service.usuario.dto.UsuarioAtualizacaoDto;
import api.tech.hub.techhubapi.service.usuario.dto.UsuarioCriacaoDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioMapper usuarioMapper;

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario (@RequestBody @Valid UsuarioCriacaoDto dados){
        Usuario usuario = this.repository.save(usuarioMapper.usuarioCriacaoDtoToUsuario(dados));
        return ResponseEntity.status(201).body(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return ResponseEntity.status(201).body(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Integer id){
        Optional<Usuario> usuarioOpt = this.repository.findById(id);

        return usuarioOpt.map(usuario -> ResponseEntity.status(200).body(usuario))
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @PutMapping
    public ResponseEntity<Usuario> atualizarDadosDoUsuario(@PathVariable Integer id,
                                                           @RequestBody @Valid UsuarioAtualizacaoDto usuario){
        return null;
    }

}
