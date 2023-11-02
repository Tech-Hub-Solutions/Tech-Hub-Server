package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;
import api.tech.hub.techhubapi.service.referencia.ReferenciaPerfilMapper;
import api.tech.hub.techhubapi.service.referencia.ReferenciaPerfilService;
import api.tech.hub.techhubapi.service.referencia.dto.ReferenciaDetalhadoDto;
import api.tech.hub.techhubapi.service.referencia.dto.ReferenciaPerfilCriacaoDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/referencias")
@RequiredArgsConstructor
public class ReferenciaController {

    private final ReferenciaPerfilService referenciaPerfilService;
    private final ReferenciaPerfilMapper referenciaPerfilMapper;

    @PutMapping("/favoritar/{idAvaliado}")
    public ResponseEntity<ReferenciaDetalhadoDto> favoritarTerceiro(@PathVariable Integer idAvaliado){
        return ResponseEntity.ok(this.referenciaPerfilService.favoritarTerceiro(idAvaliado));
    }

    @PutMapping("/recomendar/{idAvaliado}")
    public ResponseEntity<ReferenciaDetalhadoDto> recomendarTerceiro(@PathVariable Integer idAvaliado){
        return ResponseEntity.ok(this.referenciaPerfilService.recomendarTerceiro(idAvaliado));
    }

    @GetMapping("/buscar-referencia/usuario-logado")
    public ResponseEntity<List<ReferenciaDetalhadoDto>> buscarReferencias(){
        List<ReferenciaPerfil> referencias = this.referenciaPerfilService.encontrarReferenciasPerfil();

        if (referencias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ReferenciaDetalhadoDto> referenciaDetalhadoDtos = this.referenciaPerfilMapper
                .retornarListaReferenciasDto(referencias);

        return ResponseEntity.ok(referenciaDetalhadoDtos);
    }

}
