package api.tech.hub.techhubapi.service.metricausuario;

import api.tech.hub.techhubapi.entity.VisualizacaoPerfil;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.entity.usuario.UsuarioFuncao;
import api.tech.hub.techhubapi.entity.views.VwEmpresasInteressadas;
import api.tech.hub.techhubapi.repository.PerfilRepository;
import api.tech.hub.techhubapi.repository.VisualizacaoPerfilRepository;
import api.tech.hub.techhubapi.repository.VwEmpresasInteressadasRepository;
import api.tech.hub.techhubapi.service.metricausuario.dto.EmpresaInteressadaDto;
import api.tech.hub.techhubapi.service.metricausuario.dto.MetricaUsuarioResponseDto;
import api.tech.hub.techhubapi.service.usuario.UsuarioService;
import api.tech.hub.techhubapi.service.usuario.autenticacao.AutenticacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MetricaUsuarioService {

    private final AutenticacaoService autenticacaoService;
    private final VisualizacaoPerfilRepository visualizacaoPerfilRepository;
    private final PerfilRepository perfilRepository;
    private final UsuarioService usuarioService;
    private final VwEmpresasInteressadasRepository vwEmpresasInteressadasRepository;

    public VisualizacaoPerfil adicionarVisualizacao(Integer idPerfil) {
        Usuario usuarioLogado = this.autenticacaoService.getUsuarioFromUsuarioDetails();

        Perfil perfil = this.perfilRepository.findById(idPerfil)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado"));

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

        return this.visualizacaoPerfilRepository.countByDtHoraBetweenAndPerfil(inicioSemana, fimSemana, usuario.getPerfil());

    }

    public List<VwEmpresasInteressadas> buscarTop5EmpresasInteressadas(Usuario usuario) {
        return this.vwEmpresasInteressadasRepository.findTop5ByAvaliadoId(usuario.getId());
    }

    public MetricaUsuarioResponseDto buscarMetricasUsuario(Usuario usuario) {

        usuarioService.validarFuncaoUsuario(usuario, UsuarioFuncao.FREELANCER);

        List<VwEmpresasInteressadas> vwEmpresasInteressadas = this.buscarTop5EmpresasInteressadas(usuario);
        vwEmpresasInteressadas.forEach(System.out::println);

        List<Integer> vwEmpresasInteressadasIds = vwEmpresasInteressadas.stream().map(VwEmpresasInteressadas::getEmpresaId).toList();

        List<Usuario> empresasInteressadas = this.usuarioService.buscarPorIds(vwEmpresasInteressadasIds);

        Map<Integer, Integer> empresaIdParaPosicao = new HashMap<>();
        for (int i = 0; i < vwEmpresasInteressadas.size(); i++) {
            empresaIdParaPosicao.put(vwEmpresasInteressadas.get(i).getEmpresaId(), i + 1);
        }

        List<EmpresaInteressadaDto> empresasInteressadasDto = empresasInteressadas
                .stream()
                .map(empresa -> new EmpresaInteressadaDto(empresaIdParaPosicao.get(empresa.getId()), empresa))
                .sorted(Comparator.comparing(EmpresaInteressadaDto::top))
                .toList();

        Integer qtdVisualizacoesPerfilSemanal = this.buscarQtdVisualizacoesPerfilSemanal(usuario);

        Integer qtdRecomendacoes = usuario.getPerfil()
                .getReferenciaPerfilList()
                .stream()
                .filter(r -> r.getAvaliado().equals(usuario.getPerfil()))
                .filter(ReferenciaPerfil::isRecomendado)
                .toList()
                .size();

        MetricaUsuarioResponseDto metrica = new MetricaUsuarioResponseDto(
                usuario.getId(),
                empresasInteressadasDto,
                qtdVisualizacoesPerfilSemanal,
                qtdRecomendacoes);

        return metrica;
    }


}
