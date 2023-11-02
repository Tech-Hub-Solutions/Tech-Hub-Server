package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import api.tech.hub.techhubapi.service.avaliacao.AvaliacaoService;
import api.tech.hub.techhubapi.service.avaliacao.dto.AvaliacaoDetalhadoDto;
import api.tech.hub.techhubapi.service.perfil.PerfilService;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilCadastroDto;
import api.tech.hub.techhubapi.service.avaliacao.dto.avaliacaoDto;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilDetalhadoDto;
import api.tech.hub.techhubapi.service.projeto.ProjetoService;
import api.tech.hub.techhubapi.service.referencia.ReferenciaPerfilService;
import api.tech.hub.techhubapi.service.referencia.dto.ReferenciaDetalhadoDto;
import api.tech.hub.techhubapi.service.referencia.dto.ReferenciaPerfilCriacaoDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/perfis")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilService perfilService;
    private final ReferenciaPerfilService referenciaPerfilService;
    private final AvaliacaoService avaliacaoService;

    @PutMapping("/{idUsuario}")
    public ResponseEntity<PerfilDetalhadoDto> atualizarPerfil(@PathVariable Integer idUsuario,
                                                          @RequestBody @Valid PerfilCadastroDto dto){
        return ResponseEntity.ok(this.perfilService.atualizarPerfil(idUsuario,dto));
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<PerfilDetalhadoDto> buscarPerfil(@PathVariable Integer idUsuario){
        return ResponseEntity.ok(this.perfilService.buscarPerfilDetalhadoPorIdUsuario(idUsuario));
    }

    @GetMapping("/referenciar/{idUsuario}")
    public ResponseEntity<List<ReferenciaDetalhadoDto>> buscarReferenciasDoPerfilPorIdUsuario(@PathVariable Integer idUsuario){
        return ResponseEntity.ok(this.referenciaPerfilService.encontrarReferenciasPerfil(idUsuario));
    }
  
    @PutMapping("/arquivo")
    public ResponseEntity<Void> atualizarArquivoPerfil(
            @RequestParam MultipartFile arquivo,
            @RequestParam TipoArquivo tipoArquivo
            ) {
        this.perfilService.atualizarArquivoPerfil(arquivo, tipoArquivo);

        return ResponseEntity.ok().build();
    }
}
