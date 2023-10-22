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


    @GetMapping("/avaliar/{idUsuario}")
    public ResponseEntity<List<AvaliacaoDetalhadoDto>> buscarAvaliacoesDoPerfilPorIdUsuario(@PathVariable Integer idUsuario){
        return ResponseEntity.ok(this.avaliacaoService.encontrarAvaliacoesPerfil(idUsuario));
    }

    @PutMapping("/{id}/arquivo")
    public ResponseEntity<Void> atualizarArquivoPerfil(
            @PathVariable Integer id,
            @RequestParam MultipartFile arquivo,
            @RequestParam TipoArquivo tipoArquivo
            ) {
        this.perfilService.atualizarArquivoPerfil(id, arquivo, tipoArquivo);
        return ResponseEntity.ok().build();
    }

//    @PatchMapping("/atualizar/sobre-mim/{idUsuario}")
//    public ResponseEntity<Perfil> atualizarSobreMim(@PathVariable int idUsuario,
//                                                    @RequestParam String sobreMim) {
//        Perfil perfilAtualizado = this.perfilService.atualizarSobreMim(idUsuario, sobreMim);
//        return ResponseEntity.ok(perfilAtualizado);
//    }
//
//    @PatchMapping("/atualizar/experiencia/{idUsuario}")
//    public ResponseEntity<Perfil> atualizarExperiencia(@PathVariable int idUsuario,
//                                                    @RequestParam String experiencia) {
//        Perfil perfilAtualizado = this.perfilService.atualizarExperiencia(idUsuario, experiencia);
//        return ResponseEntity.ok(perfilAtualizado);
//    }
//
//    @PatchMapping("/atualizar/descricao/{idUsuario}")
//    public ResponseEntity<Perfil> atualizarDescricao(@PathVariable int idUsuario,
//                                                       @RequestParam String descricao) {
//        Perfil perfilAtualizado = this.perfilService.atualizarDescricao(idUsuario, descricao);
//        return ResponseEntity.ok(perfilAtualizado);
//    }
//
//    @PatchMapping("/atualizar/pathPerfilImage/{idUsuario}")
//    public ResponseEntity<Perfil> atualizarPathPerfilImage(@PathVariable int idUsuario,
//                                                     @RequestParam String pathPerfilImage) {
//        Perfil perfilAtualizado = this.perfilService.atualizarPathPerfilImage(idUsuario, pathPerfilImage);
//        return ResponseEntity.ok(perfilAtualizado);
//    }
//
//    @PatchMapping("/atualizar/pathWallpaperImage/{idUsuario}")
//    public ResponseEntity<Perfil> atualizarPathWallpaperImage(@PathVariable int idUsuario,
//                                                           @RequestParam String pathWallpaperImage) {
//        Perfil perfilAtualizado = this.perfilService.atualizarPathWallpaperImage(idUsuario, pathWallpaperImage);
//        return ResponseEntity.ok(perfilAtualizado);
//    }
//
//    @PatchMapping("/atualizar/precoMedio/{idUsuario}")
//    public ResponseEntity<Perfil> atualizarPrecoMedio(@PathVariable int idUsuario,
//                                                              @RequestParam String precoMedio) {
//        Perfil perfilAtualizado = this.perfilService.atualizarPrecoMedio(idUsuario, precoMedio);
//        return ResponseEntity.ok(perfilAtualizado);
//    }
//
//    @PatchMapping("/atualizar/linkGithub/{idUsuario}")
//    public ResponseEntity<Perfil> atualizarLinkGithub(@PathVariable int idUsuario,
//                                                      @RequestParam String linkGithub) {
//        Perfil perfilAtualizado = this.perfilService.atualizarLinkGithub(idUsuario, linkGithub);
//        return ResponseEntity.ok(perfilAtualizado);
//    }
//
//    @PatchMapping("/atualizar/linkLinkedin/{idUsuario}")
//    public ResponseEntity<Perfil> atualizarLinkLinkedin(@PathVariable int idUsuario,
//                                                      @RequestParam String linkLinkedin) {
//        Perfil perfilAtualizado = this.perfilService.atualizarLinkLinkedin(idUsuario, linkLinkedin);
//        return ResponseEntity.ok(perfilAtualizado);
//    }
}
