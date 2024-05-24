package api.tech.hub.techhubapi.service.email;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.email.EmailService;
import api.tech.hub.techhubapi.service.metricausuario.MetricaUsuarioService;
import api.tech.hub.techhubapi.service.metricausuario.dto.MetricasUsuarioResponseDto;
import api.tech.hub.techhubapi.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmailSenderSchedule {

    @Value("${base.client.url}")
    private String BASE_CLIENT_URL;

    private final UsuarioService usuarioService;
    private final EmailService emailService;
    private final MetricaUsuarioService metricaUsuarioService;

    @Scheduled(cron = "${cron.email}")
    public void enviarEmails() {
        log.info("Começando a enviar emails");
        Pageable pageable = PageRequest.of(0, 10);
        Slice<Usuario> usuarios = usuarioService.listarUsuariosFreelancers(pageable);

        do {
            usuarios.getContent().forEach(usuario -> {
                MetricasUsuarioResponseDto metricasUsuario = metricaUsuarioService.buscarMetricasUsuario(
                      usuario);
                enviarEmail(usuario, metricasUsuario);
            });
            usuarios = usuarioService.listarUsuariosFreelancers(usuarios.nextPageable());

        }
        while (usuarios.hasNext());

        log.info("Emails enviados");
    }

    private void enviarEmail(Usuario usuario, MetricasUsuarioResponseDto metricasUsuario) {

        log.info("Enviando email: {}", usuario.getEmail());
        Context context = new Context();

        context.setVariable("nome", usuario.getNome());
        context.setVariable("qtdVisualizacoes", metricasUsuario.qtdVisualizacoesPerfilSemanal());
        context.setVariable("qtdRecomendacoes", metricasUsuario.qtdRecomendacoes());
        context.setVariable("empresasInteressadas", metricasUsuario.empresasInteressadas());
        context.setVariable("baseClientUrl", BASE_CLIENT_URL);

        emailService.sendEmailWithHtmlTemplate(
              usuario.getEmail(),
              "Seu perfil TechHub",
              "EmailSemanalTemplate",
              context);
    }

}
