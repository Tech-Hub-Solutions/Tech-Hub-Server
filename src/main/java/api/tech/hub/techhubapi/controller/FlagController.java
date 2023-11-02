package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.service.flag.FlagService;
import api.tech.hub.techhubapi.service.flag.dto.FlagDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flags")
@RequiredArgsConstructor
public class FlagController {

    private final FlagService flagService;

    @GetMapping
    public ResponseEntity<List<FlagDto>> listarTodasAsFlags(){
        List<Flag> flags = this.flagService.listarTodasAsFlags();

        if (flags.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(flags.stream().map(FlagDto::new).toList());
    }
}
