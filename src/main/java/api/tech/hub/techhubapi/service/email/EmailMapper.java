package api.tech.hub.techhubapi.service.email;

import api.tech.hub.techhubapi.entity.Mail;
import api.tech.hub.techhubapi.service.email.dto.EmailDto;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {

        public Mail dtoToEmail(EmailDto dto) {
            Mail mail = new Mail();

            mail.setDestinatario(dto.destinatario());
            mail.setAssunto(dto.assunto());
            mail.setConteudo(dto.conteudo());

            return mail;
        }


}
