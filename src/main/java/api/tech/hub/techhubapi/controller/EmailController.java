package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.service.email.dto.EmailDto;
import api.tech.hub.techhubapi.service.email.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private EmailService EmailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMail(@RequestBody EmailDto emailDto) throws MessagingException {
        EmailService.sendHtml(emailDto);

        return ResponseEntity.ok().body(String.format("Email enviado para %s", emailDto.destinatario()));
    }

}
