package api.tech.hub.techhubapi.controller;

import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.service.arquivotxt.ArquivoTxtService;
import api.tech.hub.techhubapi.service.flag.FlagMapper;
import api.tech.hub.techhubapi.service.flag.FlagService;
import api.tech.hub.techhubapi.service.flag.dto.FlagDto;
import api.tech.hub.techhubapi.service.flag.dto.FlagRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/flags")
@RequiredArgsConstructor
public class FlagController {

    private final FlagService flagService;
    private final ArquivoTxtService arquivoTxtService;

    @PostMapping
    public ResponseEntity<FlagDto> cadastrarFlag(@RequestBody FlagRequestDto dto){
        Flag flag = FlagMapper.of(dto);

        return ResponseEntity.ok(FlagMapper.dtoOf(this.arquivoTxtService.cadastrarFlag(flag)));
    }

    @GetMapping
    public ResponseEntity<List<FlagDto>> listarTodasAsFlags(){
        List<Flag> flags = this.flagService.listarTodasAsFlags();

        if (flags.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(flags.stream().map(FlagDto::new).toList());
    }

    @PostMapping("/txt/importar")
    public ResponseEntity<Void> importarTxtFlags(@RequestPart("file") MultipartFile arquivo){
        this.arquivoTxtService.importarArquivoTxt(arquivo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/txt/exportar")
    public ResponseEntity<Resource> baixarTxtFlags(){
        Resource resource = this.arquivoTxtService.exportarTxt();
        String contentType = this.arquivoTxtService.getContentType(resource);

        long fileSize;

        try {
            fileSize = resource.contentLength(); // It's important that resource should be able to provide this info
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        + "Flags.txt" + "\"")
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(fileSize)) // Adding size to the headers
                .body(resource);
    }


    @PostMapping("/agenda-adicionar")
    public ResponseEntity<FlagDto> agendarCadastroDeFlag(@RequestBody FlagRequestDto dto){
        Flag flag = FlagMapper.of(dto);

        this.arquivoTxtService.adicionarFlagNaAgenda(flag);

        return ResponseEntity.ok(FlagMapper.dtoOf(flag));
    }

    @PostMapping("/agenda-executar")
    public ResponseEntity<List<FlagDto>> executarAgendaDeFlags(){
        List<Flag> flags = this.arquivoTxtService.executarAgendaDeFlags();

        if (flags.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(flags.stream().map(FlagDto::new).toList());
    }

    @DeleteMapping("/agenda-limpar")
    public ResponseEntity<Void> limparAgendaDeFlags(){
        this.arquivoTxtService.limparAgendaDeFlags();

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/limpar-refazer")
    public ResponseEntity<Void> limparAgendaDeFlagsERefazer(){
        this.arquivoTxtService.limparRefazer();

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/agenda-desfazer-ultimo")
    public ResponseEntity<Void> desfazerUltimoCadastro(){
        this.arquivoTxtService.desfazerUltimoCadastro();

        return ResponseEntity.noContent().build();
    }
}
