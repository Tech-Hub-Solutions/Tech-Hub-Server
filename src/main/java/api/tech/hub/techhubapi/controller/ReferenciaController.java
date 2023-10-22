package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.service.referencia.ReferenciaPerfilService;
import api.tech.hub.techhubapi.service.referencia.dto.ReferenciaDetalhadoDto;
import api.tech.hub.techhubapi.service.referencia.dto.ReferenciaPerfilCriacaoDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/referencias")
@RequiredArgsConstructor
public class ReferenciaController {

    private final ReferenciaPerfilService referenciaPerfilService;

    @PostMapping("/referenciar/{idAvaliador}")
    public ResponseEntity<ReferenciaDetalhadoDto> referenciarPerfil(@PathVariable Integer idAvaliador,
                                                                    @RequestBody @Valid ReferenciaPerfilCriacaoDto dto) {
        return ResponseEntity.ok(this.referenciaPerfilService.criarReferenciaPerfil(idAvaliador, dto));
    }

}
