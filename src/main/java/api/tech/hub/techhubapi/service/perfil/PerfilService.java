package api.tech.hub.techhubapi.service.perfil;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.Arquivo;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;
import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.entity.perfil.flag.FlagUsuario;
import api.tech.hub.techhubapi.repository.*;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.repository.PerfilRepository;
import api.tech.hub.techhubapi.repository.UsuarioRepository;
import api.tech.hub.techhubapi.service.arquivo.ArquivoService;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import api.tech.hub.techhubapi.service.flag.FlagService;
import api.tech.hub.techhubapi.service.flag.FlagUsuarioService;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilCadastroDto;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilDetalhadoDto;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilGeralDetalhadoDto;
import api.tech.hub.techhubapi.service.usuario.autenticacao.AutenticacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;
    private final PerfilMapper perfilMapper;
    private final FlagUsuarioService flagUsuarioService;
    private final ArquivoService arquivoService;
    private final AutenticacaoService autenticacaoService;

    public PerfilGeralDetalhadoDto buscarPerfilGeralPorIdUsuario(Integer idUsuario) {
        if (!this.usuarioRepository.existsById(idUsuario)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }


        Usuario usuario = this.usuarioRepository.findById(idUsuario)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
                );

        Perfil perfil = this.perfilRepository.encontrarPerfilPorIdUsuario(idUsuario)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado")
                );

        Usuario usuarioLogado = this.autenticacaoService.getUsuarioFromUsuarioDetails();

        return this.perfilMapper.perfilGeralDtoOf(usuarioLogado,perfil);
    }

    public PerfilDetalhadoDto atualizarPerfil(PerfilCadastroDto dto) {
        Usuario usuarioLogado = this.autenticacaoService.getUsuarioFromUsuarioDetails();

        Perfil perfil = this.perfilRepository.encontrarPerfilPorIdUsuario(usuarioLogado.getId()).get();

        perfil = this.perfilMapper.of(perfil, dto);

        this.perfilRepository.save(perfil);

        flagUsuarioService.salvarFlagUsuario(perfil, dto.flagsId());

        return criarPerfilDetalhadoDto(usuarioLogado.getId());
    }

    private PerfilDetalhadoDto criarPerfilDetalhadoDto(int idUsuario) {
        Perfil perfil = this.perfilRepository.encontrarPerfilPorIdUsuario(idUsuario).get();
        return this.perfilMapper.dtoOf(perfil);
    }

    public PerfilDetalhadoDto buscarPerfilDetalhadoPorIdUsuario(Integer idUsuario) {
        if (!this.usuarioRepository.existsById(idUsuario)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        return criarPerfilDetalhadoDto(idUsuario);
    }

    public void atualizarArquivoPerfil(MultipartFile arquivo, TipoArquivo tipoArquivo) {
        Usuario usuarioLogado = this.autenticacaoService.getUsuarioFromUsuarioDetails();

        Perfil perfil = this.perfilRepository.findById(usuarioLogado.getPerfil().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado"));

        if (!tipoArquivo.equals(TipoArquivo.PERFIL) && !tipoArquivo.equals(TipoArquivo.WALLPAPER)
                && !tipoArquivo.equals(TipoArquivo.CURRICULO)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de arquivo inválido");
        }

        Arquivo arquivoSalvo = this.arquivoService.salvarArquivoLocal(arquivo, tipoArquivo);

        arquivoSalvo.setId(
                perfil.getArquivos().stream()
                        .filter(arquivo1 -> arquivo1.getTipoArquivo().equals(tipoArquivo))
                        .findFirst()
                        .map(Arquivo::getId)
                        .orElse(null)
        );

        if (arquivoSalvo.getId() != null) {
            this.arquivoService.deletarArquivo(arquivoSalvo.getId(), tipoArquivo);
        }

        arquivoSalvo.setPerfil(perfil);
        this.arquivoService.salvarArquivo(arquivoSalvo);
    }

}
