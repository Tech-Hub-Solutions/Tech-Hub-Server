package api.tech.hub.techhubapi.service.perfil;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.entity.usuario.UsuarioFuncao;
import api.tech.hub.techhubapi.repository.PerfilRepository;
import api.tech.hub.techhubapi.repository.UsuarioRepository;
import api.tech.hub.techhubapi.service.arquivo.ArquivoService;
import api.tech.hub.techhubapi.service.flag.FlagUsuarioService;
import api.tech.hub.techhubapi.service.usuario.autenticacao.AutenticacaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PerfilServiceTest {

    @InjectMocks
    private PerfilService perfilService;
    @Mock
    private PerfilRepository perfilRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private PerfilMapper perfilMapper;
    @Mock
    private FlagUsuarioService flagUsuarioService;
    @Mock
    private ArquivoService arquivoService;
    @Mock
    private AutenticacaoService autenticacaoService;

    @Test
    void atualizarPerfil() {
        Usuario usuarioLogado = new Usuario(21, null, "teste", "teste@teste", "123",
                "12345678910", "br", UsuarioFuncao.FREELANCER, true,
                null, null);

        Perfil perfilLogado = new Perfil(21, "tem nada", "nenhuma", "sou um dev", 500.0,
                "teste testinha", "teste@hotmail.com", "linkedin.com/testinho",
                usuarioLogado, null, null,
                null, null);

        usuarioLogado.setPerfil(perfilLogado);

        Perfil perfilAtualizado = new Perfil(22, "Sou timido", "10 anos como dev back end",
                "sou senior", 2000.0, "Senior eu sou", "senior@hotmail.com",
                "linkedin.com/senior", usuarioLogado, null, null,
                null, null);

        Mockito.when(perfilRepository.save(perfilAtualizado)).thenReturn(perfilAtualizado);

        Perfil perfilAtualizadoReturn = perfilRepository.save(perfilAtualizado);

        assertNotEquals(perfilAtualizadoReturn, perfilLogado);
    }
}