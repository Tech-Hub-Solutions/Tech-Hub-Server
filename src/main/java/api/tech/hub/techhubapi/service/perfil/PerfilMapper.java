package api.tech.hub.techhubapi.service.perfil;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilCadastroDto;
import api.tech.hub.techhubapi.service.avaliacao.dto.avaliacaoDto;
import api.tech.hub.techhubapi.service.perfil.dto.PerfilDetalhadoDto;
import org.springframework.stereotype.Component;

@Component
public class PerfilMapper {
    public Perfil of(Usuario usuario, PerfilCadastroDto dto) {
        Perfil novoPerfil = new Perfil();

        novoPerfil.setSobreMim(dto.sobreMim());
        novoPerfil.setExperiencia(dto.experiencia());
        novoPerfil.setDescricao(dto.descricao());
        novoPerfil.setPathPerfilImage(dto.pathPerfilImage());
        novoPerfil.setPathWallpaperImage(dto.pathWallpaperImage());
        novoPerfil.setPrecoMedio(dto.precoMedio());
        novoPerfil.setLinkGithub(dto.linkGithub());
        novoPerfil.setLinkLinkedin(dto.linkLinkedin());
        novoPerfil.setUsuario(usuario);

        return novoPerfil;
    }


    public PerfilDetalhadoDto dtoOf(Perfil perfil) {
        return new PerfilDetalhadoDto(perfil);
    }
}
