package api.tech.hub.techhubapi.service.perfil.dto;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.Projeto;
import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;
import api.tech.hub.techhubapi.entity.perfil.flag.FlagUsuario;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import jakarta.persistence.*;

import java.util.List;

public record PerfilDetalhadoDto(Integer id,String experiencia,String descricao,String pathPerfilImage,
                                 String pathWallpaperImage,Double precoMedio,String linkGithub,String linkLinkedin,
                                 List<FlagUsuario> flagUsuarioList) {

    public PerfilDetalhadoDto(Perfil perfil){
        this(perfil.getId(), perfil.getExperiencia(), perfil.getDescricao(), perfil.getPathPerfilImage(),
                perfil.getPathWallpaperImage(), perfil.getPrecoMedio(), perfil.getLinkGithub(),
                perfil.getLinkLinkedin(), perfil.getFlagUsuarioList());
    }
}
