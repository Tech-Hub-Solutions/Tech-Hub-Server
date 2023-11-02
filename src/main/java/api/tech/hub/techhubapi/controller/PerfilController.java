package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import api.tech.hub.techhubapi.service.avaliacao.AvaliacaoService;
import api.tech.hub.techhubapi.service.perfil.PerfilService;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilCadastroDto;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilDetalhadoDto;
import api.tech.hub.techhubapi.service.referencia.ReferenciaPerfilService;
import api.tech.hub.techhubapi.service.referencia.dto.ReferenciaDetalhadoDto;
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

    @PutMapping("")
    public ResponseEntity<PerfilDetalhadoDto> atualizarPerfil(
                                                          @RequestBody @Valid PerfilCadastroDto dto){
        return ResponseEntity.ok(this.perfilService.atualizarPerfil(dto));
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<PerfilDetalhadoDto> buscarPerfil(@PathVariable Integer idUsuario){
        return ResponseEntity.ok(this.perfilService.buscarPerfilDetalhadoPorIdUsuario(idUsuario));
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
