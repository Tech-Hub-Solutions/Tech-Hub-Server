package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.service.avaliacao.AvaliacaoService;
import api.tech.hub.techhubapi.service.avaliacao.dto.AvaliacaoDetalhadoDto;
import api.tech.hub.techhubapi.service.avaliacao.dto.AvaliacaoTotal;
import api.tech.hub.techhubapi.service.avaliacao.dto.avaliacaoDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @PostMapping("/{idUsuario}")
    public ResponseEntity<AvaliacaoDetalhadoDto> avaliarPerfil(@PathVariable Integer idUsuario,
                                                               @RequestBody @Valid avaliacaoDto dto){
        return ResponseEntity.ok(this.avaliacaoService.avaliar(dto, idUsuario));
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<AvaliacaoDetalhadoDto>> buscarAvaliacoesDoPerfilPorIdUsuario(@PathVariable Integer idUsuario){
        return ResponseEntity.ok(this.avaliacaoService.encontrarAvaliacoesPerfil(idUsuario));
    }

    @GetMapping("contar/{idUsuario}")
    public ResponseEntity<List<AvaliacaoTotal>> buscarAvaliacaoGeralUsuario(@PathVariable Integer idUsuario){
        List<AvaliacaoTotal> avaliacaoTotals = this.avaliacaoService.buscarAvaliacaoGeral(idUsuario);

        if (avaliacaoTotals.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(avaliacaoTotals);
    }
}
