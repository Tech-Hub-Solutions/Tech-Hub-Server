package api.tech.hub.techhubapi.service.metricausuario;

import api.tech.hub.techhubapi.entity.VisualizacaoPerfil;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.entity.usuario.UsuarioFuncao;
import api.tech.hub.techhubapi.entity.views.VwEmpresasInteressadas;
import api.tech.hub.techhubapi.repository.PerfilRepository;
import api.tech.hub.techhubapi.repository.VisualizacaoPerfilRepository;
import api.tech.hub.techhubapi.repository.VwEmpresasInteressadasRepository;
import api.tech.hub.techhubapi.service.metricausuario.dto.EmpresaInteressadaDto;
import api.tech.hub.techhubapi.service.metricausuario.dto.MetricasUsuarioResponseDto;
import api.tech.hub.techhubapi.service.referencia.ReferenciaPerfilService;
import api.tech.hub.techhubapi.service.usuario.UsuarioService;
import api.tech.hub.techhubapi.service.usuario.autenticacao.AutenticacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MetricaUsuarioService {

    @Value("${base.perfil.client.url}")
    private String BASE_PERFIL_CLIENT_URL;

    private final AutenticacaoService autenticacaoService;
    private final VisualizacaoPerfilRepository visualizacaoPerfilRepository;
    private final PerfilRepository perfilRepository;
    private final UsuarioService usuarioService;
    private final VwEmpresasInteressadasRepository vwEmpresasInteressadasRepository;
    private final ReferenciaPerfilService referenciaPerfilService;

    public VisualizacaoPerfil adicionarVisualizacao(Integer idPerfil) {
        Usuario usuarioLogado = this.autenticacaoService.getUsuarioFromUsuarioDetails();

        Perfil perfil = this.perfilRepository.findById(idPerfil)
              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Perfil não encontrado"));

        LocalDateTime inicioSemana = LocalDateTime.now().minusDays(7);
        LocalDateTime fimSemana = LocalDateTime.now();

        Optional<VisualizacaoPerfil> visualizacaoPerfilOpt = this.visualizacaoPerfilRepository.findByDtHoraBetweenAndUsuarioAndPerfil(
              inicioSemana, fimSemana, usuarioLogado, perfil
        );

        if (visualizacaoPerfilOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Visualização já registrada");
        }

        VisualizacaoPerfil visualizacaoPerfil = new VisualizacaoPerfil(
              null,
              LocalDateTime.now(),
              usuarioLogado,
              perfil
        );

        return this.visualizacaoPerfilRepository.save(visualizacaoPerfil);

    }

    public Integer buscarQtdVisualizacoesPerfilSemanal(Usuario usuario) {
        LocalDateTime inicioSemana = LocalDateTime.now().minusDays(7);
        LocalDateTime fimSemana = LocalDateTime.now();

        return this.visualizacaoPerfilRepository.countByDtHoraBetweenAndPerfil(inicioSemana,
              fimSemana, usuario.getPerfil());

    }

    public List<VwEmpresasInteressadas> buscarTop4EmpresasInteressadas(Usuario usuario) {
        return this.vwEmpresasInteressadasRepository.findTop4ByAvaliadoId(usuario.getId());
    }

    public MetricasUsuarioResponseDto buscarMetricasUsuario(Usuario usuario) {

        usuarioService.validarFuncaoUsuario(usuario, UsuarioFuncao.FREELANCER);

        List<VwEmpresasInteressadas> vwEmpresasInteressadas = this.buscarTop4EmpresasInteressadas(
              usuario);

        List<Integer> vwEmpresasInteressadasIds = vwEmpresasInteressadas.stream()
              .map(VwEmpresasInteressadas::getEmpresaId).toList();

        List<Usuario> empresasInteressadas = this.usuarioService.buscarPorIds(
              vwEmpresasInteressadasIds);

        Map<Integer, Integer> empresaIdParaPosicao = new HashMap<>();
        for (int i = 0; i < vwEmpresasInteressadas.size(); i++) {
            empresaIdParaPosicao.put(vwEmpresasInteressadas.get(i).getEmpresaId(), i + 1);
        }

        List<EmpresaInteressadaDto> empresasInteressadasDto = empresasInteressadas
              .stream()
              .map(empresa -> new EmpresaInteressadaDto(empresaIdParaPosicao.get(empresa.getId()),
                    empresa, BASE_PERFIL_CLIENT_URL))
              .sorted(Comparator.comparing(EmpresaInteressadaDto::top))
              .toList();

        Integer qtdVisualizacoesPerfilSemanal = this.buscarQtdVisualizacoesPerfilSemanal(usuario);

        Integer qtdRecomendacoes = referenciaPerfilService.buscarQtdRecomendacoes(usuario);

        return new MetricasUsuarioResponseDto(
              usuario.getId(),
              empresasInteressadasDto,
              qtdVisualizacoesPerfilSemanal,
              qtdRecomendacoes);
    }


}
