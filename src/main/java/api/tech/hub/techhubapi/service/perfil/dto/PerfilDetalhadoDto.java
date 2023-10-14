package api.tech.hub.techhubapi.service.perfil.dto;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.Projeto;
import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;
import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.entity.perfil.flag.FlagUsuario;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import jakarta.persistence.*;

import java.util.List;

public record PerfilDetalhadoDto(Integer id, String sobreMim, String experiencia, String descricao, Double precoMedio,
                                 String nomeGitHub, String linkGithub, String linkLinkedin) {

    public PerfilDetalhadoDto(Perfil perfil){
        this(perfil.getId(), perfil.getSobreMim(), perfil.getExperiencia(), perfil.getDescricao(),
                perfil.getPrecoMedio(), perfil.getNomeGithub(), perfil.getLinkGithub(), perfil.getLinkGithub());
    }
}
