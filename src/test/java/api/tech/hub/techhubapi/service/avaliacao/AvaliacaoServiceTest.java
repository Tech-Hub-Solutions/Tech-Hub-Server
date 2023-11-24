package api.tech.hub.techhubapi.service.avaliacao;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.entity.usuario.UsuarioFuncao;
import api.tech.hub.techhubapi.repository.AvaliacaoRepository;
import api.tech.hub.techhubapi.repository.PerfilRepository;
import api.tech.hub.techhubapi.service.avaliacao.dto.AvaliacaoDetalhadoDto;
import api.tech.hub.techhubapi.service.usuario.autenticacao.AutenticacaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class AvaliacaoServiceTest {

    @InjectMocks
    private AvaliacaoService avaliacaoService;
    @Mock
    private AvaliacaoMapper mapper;
    @Mock
    private AvaliacaoRepository avaliacaoRepository;
    @Mock
    private PerfilRepository perfilRepository;
    @Mock
    private AutenticacaoService autenticacaoService;

    @Test
    void avaliar() {
        Usuario usuarioLogado = new Usuario(21, null, "teste", "teste@teste", "123",
                "12345678910", "br", UsuarioFuncao.FREELANCER, true,
                null, null);

        Perfil perfilLogado = new Perfil(21, "tem nada", "nenhuma", "sou um dev", 500.0,
                "teste testinha", "teste@hotmail.com", "linkedin.com/testinho",
                usuarioLogado, null, null,
                null, null);

        usuarioLogado.setPerfil(perfilLogado);

        Mockito.when(autenticacaoService.getUsuarioFromUsuarioDetails()).thenReturn(usuarioLogado);

        Usuario usuarioLogadoReturn = autenticacaoService.getUsuarioFromUsuarioDetails();

        Perfil perfil = new Perfil(22, "num sei", "nenhuma", "sou um dev", 500.0,
                "teste testinha", "teste@hotmail.com", "linkedin.com/testinho",
                null, null, null,
                null, null);

        Mockito.when(perfilRepository.encontrarPerfilPorIdUsuario(21)).thenReturn(Optional.of(perfil));

        Perfil perfilReturn = perfilRepository.encontrarPerfilPorIdUsuario(21).get();

        Avaliacao avaliacao = new Avaliacao(50, perfilReturn, usuarioLogadoReturn.getPerfil(), "muito bom", 5);

        Mockito.when(avaliacaoRepository.save(avaliacao)).thenReturn(avaliacao);

        Avaliacao avaliacaoReturn = avaliacaoRepository.save(avaliacao);

        assertEquals(avaliacaoReturn, avaliacao);
    }

    @Test
    void encontrarAvaliacoesPerfil() {
        Usuario usuarioLogado = new Usuario(21, null, "teste", "teste@teste", "123",
                "12345678910", "br", UsuarioFuncao.FREELANCER, true,
                null, null);

        Perfil perfilLogado = new Perfil(21, "tem nada", "nenhuma", "sou um dev", 500.0,
                "teste testinha", "teste@hotmail.com", "linkedin.com/testinho",
                usuarioLogado, null, List.of(
                new Avaliacao(50, usuarioLogado.getPerfil(),null, "muito bom", 5),
                new Avaliacao(51, usuarioLogado.getPerfil(),null, "médio", 3),
                new Avaliacao(52, usuarioLogado.getPerfil(),null, "ruim", 1)
        ),
                null, null);

        usuarioLogado.setPerfil(perfilLogado);

        Mockito.when(perfilRepository.encontrarPerfilPorIdUsuario(usuarioLogado.getId())).thenReturn(Optional.of(perfilLogado));

        Perfil perfilReturn = perfilRepository.encontrarPerfilPorIdUsuario(usuarioLogado.getId()).get();

        Page<Avaliacao> avaliacoes = new PageImpl<>(perfilReturn.getAvaliacaoList());

        Mockito.when(avaliacaoRepository.findAvaliacaoByPerfil(perfilReturn,
                Pageable.ofSize(3))).thenReturn(avaliacoes);

        Page<Avaliacao> avaliacaosReturn = avaliacaoRepository.findAvaliacaoByPerfil(perfilReturn,
                Pageable.ofSize(3));


        assertFalse(avaliacaosReturn.isEmpty());
        assertEquals(avaliacoes, avaliacaosReturn);
    }


}