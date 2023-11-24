package api.tech.hub.techhubapi.service.usuario;

import api.tech.hub.techhubapi.configuration.security.jwt.GerenciadorTokenJwt;
import api.tech.hub.techhubapi.entity.ListaObj;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.entity.usuario.UsuarioFuncao;
import api.tech.hub.techhubapi.repository.FlagRepository;
import api.tech.hub.techhubapi.repository.PerfilRepository;
import api.tech.hub.techhubapi.repository.UsuarioRepository;
import api.tech.hub.techhubapi.service.usuario.autenticacao.AutenticacaoService;
import api.tech.hub.techhubapi.service.usuario.dto.UsuarioDetalhadoDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private GerenciadorTokenJwt gerenciadorTokenJwt;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private AutenticacaoService autenticacaoService;
    @Mock
    private UsuarioMapper usuarioMapper;
    @Mock
    private PerfilRepository perfilRepository;
    @Mock
    private FlagRepository flagRepository;

    @Test
    void listar() {
        List<Usuario> users = List.of(
                new Usuario(21, null, "teste","teste@teste","123",
                        "12345678910","br", UsuarioFuncao.FREELANCER, true,
                        null,null),
                new Usuario(22, null, "teste2","teste2@teste","123",
                        "12345678910","br", UsuarioFuncao.FREELANCER, true,
                        null,null),
                new Usuario(23, null, "teste3","teste3@teste","123",
                        "12345678910","br", UsuarioFuncao.FREELANCER, true,
                        null,null)
        );

        Mockito.when(usuarioRepository.findAll()).thenReturn(users);

        ListaObj<UsuarioDetalhadoDto> usersReturn = usuarioService.listar();

        assertFalse(usersReturn.isEmpty());
        assertEquals(users.size(),usersReturn.getTamanho());
    }

    @Test
    void buscarUsuarioPorId() {
        Usuario usuario = new Usuario(21, null, "teste","teste@teste","123",
                "12345678910","br", UsuarioFuncao.FREELANCER, true,
                null,null);

        Integer id = 21;

        Mockito.when(usuarioRepository.findUsuarioByIdAndIsAtivoTrue(id)).thenReturn(Optional.of(usuario));

        Usuario usuarioReturn = usuarioService.buscarPorId(id);

        assertEquals(usuario.getId(), usuarioReturn.getId());
        assertEquals(usuario.getNome(), usuarioReturn.getNome());
        assertEquals(usuario.getEmail(), usuarioReturn.getEmail());
        assertEquals(usuario.getSenha(), usuarioReturn.getSenha());
        assertEquals(usuario.getNumeroCadastroPessoa(), usuarioReturn.getNumeroCadastroPessoa());
        assertEquals(usuario.getPais(), usuarioReturn.getPais());
        assertEquals(usuario.getFuncao(), usuarioReturn.getFuncao());
        assertEquals(usuario.isAtivo(), usuarioReturn.isAtivo());
    }
}