package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.service.email.Email;
import api.tech.hub.techhubapi.service.email.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/mail")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;


    @Value("${email.sender.api.key}")
    private String apiKey;

    @PostMapping
    public ResponseEntity<String> sendMail(
            @RequestBody Email emailDto,
            @RequestParam String apiKey
    ) throws MessagingException {

        if (!apiKey.equals(this.apiKey)) {
            return ResponseEntity.status(401).body("API Key inválida");
        }

        emailService.sendMail(emailDto);
        log.info("Email enviado para {}", emailDto.destinatario());
        return ResponseEntity.ok().body(String.format("Email enviado para %s", emailDto.destinatario()));
    }

}
