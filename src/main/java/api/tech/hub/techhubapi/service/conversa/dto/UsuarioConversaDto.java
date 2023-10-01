package api.tech.hub.techhubapi.service.conversa.dto;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioConversaDto {

    private String nome;
    private String pathPerfilImage;

    public UsuarioConversaDto(Usuario usuario) {
        this.nome = usuario.getNome();
        if (usuario.getPerfil() == null) {
            this.pathPerfilImage = null;
        } else {
            this.pathPerfilImage = usuario.getPerfil().getPathPerfilImage();
        }
    }
}
