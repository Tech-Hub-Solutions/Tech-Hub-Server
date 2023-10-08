package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.service.perfil.PerfilService;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilCadastroDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfis")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilService perfilService;




}
