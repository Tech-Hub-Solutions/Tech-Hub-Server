package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.entity.VisualizacaoPerfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.metricausuario.dto.MetricasUsuarioResponseDto;
import api.tech.hub.techhubapi.service.metricausuario.dto.VisualizacaoPerfilResponseDto;
import api.tech.hub.techhubapi.service.metricausuario.MetricaUsuarioService;
import api.tech.hub.techhubapi.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/metricas-usuario")
@RequiredArgsConstructor
public class MetricaUsuarioController {

    private final MetricaUsuarioService metricaUsuarioService;
    private final UsuarioService usuarioService;

    @PostMapping("{idPerfil}")
    public ResponseEntity<VisualizacaoPerfilResponseDto> adicionarVisualizacao(@PathVariable Integer idPerfil) {
        VisualizacaoPerfil visualizacaoPerfil = this.metricaUsuarioService.adicionarVisualizacao(idPerfil);
        log.info("Adicionando visualização para o perfil de id: {}", idPerfil);

        return ResponseEntity.created(null).body(new VisualizacaoPerfilResponseDto(visualizacaoPerfil));
    }


    @GetMapping("{idUsuario}")
    public ResponseEntity<MetricasUsuarioResponseDto> buscarVisualizacaoPerfil(
            @PathVariable Integer idUsuario
    ) {
        Usuario usuario = this.usuarioService.buscarPorId(idUsuario);
        log.info("Buscando métricas do usuário de id: {}", idUsuario);
        return ResponseEntity.ok(this.metricaUsuarioService.buscarMetricasUsuario(usuario));
    }
}
