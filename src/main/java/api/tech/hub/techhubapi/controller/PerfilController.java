package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.entity.Arquivo;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import api.tech.hub.techhubapi.service.arquivo.ftp.FtpService;
import api.tech.hub.techhubapi.service.avaliacao.AvaliacaoService;
import api.tech.hub.techhubapi.service.avaliacao.dto.AvaliacaoDetalhadoDto;
import api.tech.hub.techhubapi.service.avaliacao.dto.AvaliacaoTotal;
import api.tech.hub.techhubapi.service.avaliacao.dto.avaliacaoDto;
import api.tech.hub.techhubapi.service.perfil.PerfilService;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilCadastroDto;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilDetalhadoDto;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilGeralDetalhadoDto;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilNewArquivo;
import api.tech.hub.techhubapi.service.referencia.ReferenciaPerfilService;
import api.tech.hub.techhubapi.service.referencia.dto.ReferenciaDetalhadoDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/perfis")
@Slf4j
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilService perfilService;
    private final FtpService ftpService;
    private final ReferenciaPerfilService referenciaPerfilService;
    private final AvaliacaoService avaliacaoService;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<PerfilGeralDetalhadoDto> buscarPerfilGeral(@PathVariable Integer idUsuario){
        log.info("Buscando perfil geral do usuário: {}", idUsuario);
        return ResponseEntity.ok(this.perfilService.buscarPerfilGeralPorIdUsuario(idUsuario));
    }


    @PutMapping
    public ResponseEntity<PerfilDetalhadoDto> atualizarPerfil(
            @RequestBody @Valid PerfilCadastroDto dto){
        return ResponseEntity.ok(this.perfilService.atualizarPerfil(dto));
    }

    @PutMapping("/arquivo")
    public ResponseEntity<PerfilNewArquivo> atualizarArquivoPerfil(
            @RequestParam MultipartFile arquivo,
            @RequestParam TipoArquivo tipoArquivo
    ) {
        Arquivo newArquivo = this.perfilService.atualizarArquivoPerfil(arquivo, tipoArquivo);
        String url = this.ftpService.getArquivoUrl(newArquivo.getId(), false);

        log.info("Arquivo atualizado. Nova url: {}", url);
        return ResponseEntity.ok(new PerfilNewArquivo(url));
    }

    @PostMapping("/avaliacao/{idUsuario}")
    public ResponseEntity<AvaliacaoDetalhadoDto> avaliarPerfil(@PathVariable Integer idUsuario,
                                                               @RequestBody @Valid avaliacaoDto dto){
        log.info("Avaliando perfil do usuário: {}", idUsuario);
        return ResponseEntity.ok(this.avaliacaoService.avaliar(dto, idUsuario));
    }

    @GetMapping("/avaliacao/{idUsuario}")
    public ResponseEntity<Page<AvaliacaoDetalhadoDto>> buscarAvaliacoesDoPerfilPorIdUsuario(
            @PathVariable Integer idUsuario,
            Pageable pageable
    ){
        Page<AvaliacaoDetalhadoDto> lista = this.avaliacaoService.encontrarAvaliacoesPerfil(idUsuario, pageable);

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        log.info("Buscando avaliações do perfil do usuário: {}", idUsuario);

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/avaliacao/geral/{idUsuario}")
    public ResponseEntity<List<AvaliacaoTotal>> buscarAvaliacaoGeralUsuario(@PathVariable Integer idUsuario){
        List<AvaliacaoTotal> avaliacaoTotals = this.avaliacaoService.buscarAvaliacaoGeral(idUsuario);

        if (avaliacaoTotals.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        log.info("Buscando avaliações gerais do usuário: {}", idUsuario);

        return ResponseEntity.ok(avaliacaoTotals);
    }

    @PutMapping("/favoritar/{idAvaliado}")
    public ResponseEntity<ReferenciaDetalhadoDto> favoritarTerceiro(@PathVariable Integer idAvaliado){
        log.info("Favoritando usuario: {}", idAvaliado);
        return ResponseEntity.ok(this.referenciaPerfilService.favoritarTerceiro(idAvaliado));
    }

    @PutMapping("/recomendar/{idAvaliado}")
    public ResponseEntity<ReferenciaDetalhadoDto> recomendarTerceiro(@PathVariable Integer idAvaliado){
        log.info("Recomendando usuario: {}", idAvaliado);
        return ResponseEntity.ok(this.referenciaPerfilService.recomendarTerceiro(idAvaliado));
    }

}
