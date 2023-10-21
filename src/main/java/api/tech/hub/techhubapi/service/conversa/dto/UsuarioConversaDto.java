package api.tech.hub.techhubapi.service.conversa.dto;

import api.tech.hub.techhubapi.entity.Arquivo;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.usuario.UsuarioService;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record UsuarioConversaDto(
        Integer id,
        String nome,
        String pathPerfilImage
) {

    public UsuarioConversaDto(Usuario usuario) {
        this(
                usuario.getId(), usuario.getNome(),
                UsuarioService.criarUrlFotoPerfil(usuario.getPerfil().getArquivos())
        );
    }

}
