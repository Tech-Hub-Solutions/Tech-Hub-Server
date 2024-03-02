package api.tech.hub.techhubapi.service.email;

import api.tech.hub.techhubapi.entity.Mail;
import api.tech.hub.techhubapi.repository.UsuarioRepository;
import api.tech.hub.techhubapi.service.email.dto.EmailDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailMapper mapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Value("${spring.mail.username}")
    private String remetente;

    /*public void sendMail(MailStructure mailStructure) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(remetente);

        simpleMailMessage.setSubject(mailStructure.getAssunto());
        simpleMailMessage.setText(mailStructure.getEmail());

        simpleMailMessage.setTo(mailStructure.getDestinatario());


        javaMailSender.send(simpleMailMessage);
    }*/

    public void sendHtml(EmailDto dto) {
        Mail email = mapper.dtoToEmail(dto);

            try {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                mimeMessageHelper.setFrom(remetente);
                mimeMessageHelper.setTo(email.getDestinatario());

                mimeMessageHelper.setSubject(email.getAssunto());
                mimeMessageHelper.setText(email.getConteudo(), true);

                javaMailSender.send(mimeMessage);

            } catch (MessagingException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro no corpo do email");
            } catch (MailAuthenticationException ex) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Erro na autenticação das credenciais do remetente");
            } catch (MailSendException ex) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Erro ao enviar o email");
            } catch (MailPreparationException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro na preparação do email");
            }

    }
}
