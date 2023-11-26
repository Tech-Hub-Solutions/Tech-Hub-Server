package api.tech.hub.techhubapi.service.conversa;

import api.tech.hub.techhubapi.entity.Arquivo;
import api.tech.hub.techhubapi.entity.conversa.Conversa;
import api.tech.hub.techhubapi.entity.conversa.Mensagem;
import api.tech.hub.techhubapi.entity.conversa.Sala;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.repository.ConversaRepository;
import api.tech.hub.techhubapi.repository.MensagemRepository;
import api.tech.hub.techhubapi.repository.SalaRepository;
import api.tech.hub.techhubapi.repository.UsuarioRepository;
import api.tech.hub.techhubapi.service.arquivo.ArquivoService;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import api.tech.hub.techhubapi.service.conversa.dto.ConversaDto;
import api.tech.hub.techhubapi.service.conversa.dto.MensagemASerEnviadaDto;
import api.tech.hub.techhubapi.service.conversa.dto.RoomCodeDto;
import api.tech.hub.techhubapi.service.usuario.autenticacao.AutenticacaoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ConversaService {
    private final AutenticacaoService autenticacaoService;
    private final SocketService socketService;

    private final ConversaRepository conversaRepository;
    private final UsuarioRepository usuarioRepository;
    private final MensagemRepository mensagemRepository;
    private final SalaRepository salaRepository;
    private final ArquivoService arquivoService;

    public RoomCodeDto iniciarConversa(Integer idUsuario) {
        Usuario usuarioAutenticado = autenticacaoService.getUsuarioFromUsuarioDetails();
        Usuario usuarioASerIniciado = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Usuário não encotrado"));

        List<Sala> salasUsuarioAutenticado = getSalas(usuarioAutenticado, true);
        List<Sala> salasUsuarioAutenticadoInativas = getSalas(usuarioAutenticado, false);
        List<Sala> salasUsuarioASerIniciado = getSalas(usuarioASerIniciado, true);

        Optional<Sala> salaEmComum = salasUsuarioAutenticado.stream()
                .filter(sala -> salasUsuarioASerIniciado.stream().anyMatch(sala::equals)).findFirst();

        Optional<Sala> salaInativa = salasUsuarioAutenticadoInativas.stream()
                .filter(sala -> salasUsuarioASerIniciado.stream().anyMatch(sala::equals)).findFirst();

        Sala sala;
        if (salaInativa.isPresent()) {
            reativarSala(salaInativa.get(), usuarioAutenticado);
            sala = salaInativa.get();
        } else if (salaEmComum.isEmpty()) {
            sala = criarNovaSala(usuarioAutenticado, usuarioASerIniciado);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Conversa já iniciada!");
        }

        socketService.recarregarConversas(usuarioAutenticado.getId());
        socketService.recarregarConversas(usuarioASerIniciado.getId());

        return new RoomCodeDto(sala.getRoomCode());
    }

    private List<Sala> getSalas(Usuario usuario, boolean isAtivo) {
        return usuario.getConversaList().stream()
                .filter(c -> c.isAtivo() == isAtivo)
                .map(Conversa::getSala)
                .toList();
    }

    private void reativarSala(Sala sala, Usuario usuario) {
        Conversa conversa = this.conversaRepository.findByUsuarioAndSala(usuario, sala);
        conversa.setAtivo(true);
        this.conversaRepository.save(conversa);
    }

    private Sala criarNovaSala(Usuario usuarioAutenticado, Usuario usuarioASerIniciado) {
        Sala newSala = new Sala();
        newSala.setRoomCode(UUID.randomUUID().toString());
        this.salaRepository.save(newSala);

        createConversa(usuarioAutenticado, newSala);
        createConversa(usuarioASerIniciado, newSala);

        return newSala;
    }

    private void createConversa(Usuario usuario, Sala sala) {
        Conversa conversa = new Conversa(null, usuario, sala, true);
        this.conversaRepository.save(conversa);
    }

    public void enviarMensagem(String roomCode, String mensagem, MultipartFile arquivo, TipoArquivo tipoArquivo) {
        Usuario usuario = autenticacaoService.getUsuarioFromUsuarioDetails();
        Sala sala = this.salaRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Sala não encontrada"));
        Conversa conversaUsuarioAEnviar = this.conversaRepository.findBySala(sala)
                .stream()
                .filter(c -> c.getUsuario() != usuario)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "usuário não encontrada"));


        verificarUsuarioNaSala(usuario, sala);

        Mensagem newMensagem = salvarMensagem(usuario, sala, mensagem, arquivo, tipoArquivo);

        MensagemASerEnviadaDto mensagemASerEnviadaDto = new MensagemASerEnviadaDto(newMensagem);

        socketService.enviarMensagem(roomCode, mensagemASerEnviadaDto);

        socketService.recarregarConversas(usuario.getId());
        socketService.recarregarConversas(conversaUsuarioAEnviar.getUsuario().getId());
    }

    private Mensagem salvarMensagem(Usuario usuario, Sala sala, String mensagem, MultipartFile arquivo,
                                    TipoArquivo tipoArquivo) {
        Mensagem newMensagem = new Mensagem();

        newMensagem.setDtMensagem(LocalDateTime.now());
        newMensagem.setUsuario(usuario);
        newMensagem.setSala(sala);
        newMensagem.setTexto(mensagem);

        newMensagem = this.mensagemRepository.save(newMensagem);

        if (arquivo != null) {
            if (tipoArquivo == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            Arquivo arquivoSalvado = this.arquivoService.salvarArquivoLocal(arquivo, tipoArquivo);
            arquivoSalvado.setMensagem(newMensagem);
            newMensagem.setArquivos(List.of(this.arquivoService.salvarArquivo(arquivoSalvado)));
        }

        return newMensagem;
    }

    public List<MensagemASerEnviadaDto> listarMensagens(String roomCode) {
        Usuario usuarioAutenticado = autenticacaoService.getUsuarioFromUsuarioDetails();
        Sala sala = this.salaRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Sala não encontrada"));


        verificarUsuarioNaSala(usuarioAutenticado, sala);

        List<Mensagem> mensagens = sala.getMensagemList();

        return mensagens.stream().map(MensagemASerEnviadaDto::new).toList();
    }

    @Transactional
    public void apagarConversa(String roomCode) {
        Sala sala = this.salaRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Sala não encontrada"));


        Usuario usuarioAutenticado = autenticacaoService.getUsuarioFromUsuarioDetails();
        Conversa conversa = this.conversaRepository.findByUsuarioAndSala(usuarioAutenticado, sala);
        conversa.setAtivo(false);
        this.conversaRepository.save(conversa);

    }

    private void verificarUsuarioNaSala(Usuario usuario, Sala sala) {
        if (!this.conversaRepository.existsByUsuarioAndSalaAndIsAtivoTrue(usuario, sala)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Usuário não está na conversa!");
        }
    }

    public List<ConversaDto> listarConversas() {
        Usuario usuarioAutenticado = autenticacaoService.getUsuarioFromUsuarioDetails();


        List<Conversa> conversasUsuario = conversaRepository.findByUsuario(usuarioAutenticado);
        List<Sala> salasDoUsuario = conversasUsuario.stream().map(Conversa::getSala).distinct().toList();

        List<Conversa> conversasDaSala = conversaRepository.findByAndSalaIn(salasDoUsuario);

        List<ConversaDto> conversas = conversasDaSala
                .stream()
                .filter(conversa -> !conversa.getUsuario().equals(usuarioAutenticado))
                .map(conversa -> {
                    Optional<Mensagem> mensagem = mensagemRepository.findFirstBySalaOrderByDtMensagemDesc(conversa.getSala());

                    return mensagem.map(value -> new ConversaDto(conversa, value))
                            .orElseGet(() -> new ConversaDto(conversa));

                })
                .sorted(Comparator.comparing(conversaDto -> {
                    LocalDateTime dtHora = conversaDto.getMensagem() != null ?
                            conversaDto.getMensagem().dtHora() :
                            LocalDateTime.MIN;
                    return dtHora;
                }, Comparator.reverseOrder()))
                .toList();

        return conversas;
    }

    public List<Mensagem> listarMensagensBanco(String room) {
        Sala sala = this.salaRepository.findByRoomCode(room)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST)
                );
        return sala.getMensagemList();
    }

    public List<Usuario> listarUsuarios(String room) {
        List<Conversa> conversa = this.conversaRepository.findBySalaRoomCode(room);

        if(conversa.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return conversa.stream()
                .map(Conversa::getUsuario)
                .toList();

    }
}
