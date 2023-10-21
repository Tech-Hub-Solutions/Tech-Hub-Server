package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.entity.ListaObj;
import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.usuario.UsuarioMapper;
import api.tech.hub.techhubapi.service.usuario.UsuarioService;
import api.tech.hub.techhubapi.service.usuario.dto.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
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
    public ResponseEntity<ListaObj<Usuario>> listar() {
        ListaObj<Usuario> usuarios = this.usuarioService.listar();

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


    // Para passar a paginação, adicionar size e page
    // localhost:8080/recursos?size=3&page=1

    // 10 registros/
    // size 1, 10 paginas

    // Para ordenar, sort e nome do atributo

    // localhost:8080/recursos?sort=nome,desc

    //  Se não passarmos o parâmetro size, o Spring devolverá 20 registros por padrão.


    // Pode juntar os dois
    // localhost:8080/recursos?sort=nome,desc&size=1&page=1
    @PostMapping("/filtro")
    public ResponseEntity<Page<UsuarioDetalhadoDto>> listarPor(
            @RequestBody UsuarioFiltroDto usuarioFiltroDto,
            Pageable paginacao
    ) {
        Page<UsuarioDetalhadoDto> usuarios = this.usuarioService.listarPor(usuarioFiltroDto, paginacao);

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }
}
