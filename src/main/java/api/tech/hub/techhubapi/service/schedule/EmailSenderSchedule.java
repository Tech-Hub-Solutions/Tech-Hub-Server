package api.tech.hub.techhubapi.service.schedule;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.email.EmailService;
import api.tech.hub.techhubapi.service.metricausuario.MetricaUsuarioService;
import api.tech.hub.techhubapi.service.metricausuario.dto.MetricasUsuarioResponseDto;
import api.tech.hub.techhubapi.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;


@Service
@RequiredArgsConstructor
public class EmailSenderSchedule {
    private final UsuarioService usuarioService;
    private final EmailService emailService;
    private final MetricaUsuarioService metricaUsuarioService;

    @Scheduled(cron = "${cron.email}")
    public void enviarEmails() {
        System.out.println("\nComeçando a enviar emails\n");
        Pageable pageable = PageRequest.of(0, 10);
        Slice<Usuario> usuarios = usuarioService.listarUsuariosFreelancers(pageable);

        do {
            usuarios.getContent().forEach(usuario -> {
                MetricasUsuarioResponseDto metricasUsuario = metricaUsuarioService.buscarMetricasUsuario(usuario);
                enviarEmail(usuario, metricasUsuario);
            });
            usuarios = usuarioService.listarUsuarios(usuarios.nextPageable());
        }
        while (usuarios.hasNext());
    }

    private void enviarEmail(Usuario usuario, MetricasUsuarioResponseDto metricasUsuario) {

        System.out.println("Enviando email para " + usuario.getEmail());
        Context context = new Context();

        context.setVariable("nome", usuario.getNome());
        context.setVariable("qtdVisualizacoes", metricasUsuario.qtdVisualizacoesPerfilSemanal());
        context.setVariable("qtdRecomendacoes", metricasUsuario.qtdRecomendacoes());
        context.setVariable("empresasInteressadas", metricasUsuario.empresasInteressadas());

        emailService.sendEmailWithHtmlTemplate(
                usuario.getEmail(),
                "Seu perfil TechHub",
                "EmailSemanalTemplate",
                context);
    }

}
