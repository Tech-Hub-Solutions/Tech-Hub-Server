package api.tech.hub.techhubapi.service.usuario.dto;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.arquivo.ArquivoService;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;

public record UsuarioSimpleDto(
        String nome,

        String email,

        String pais,

        String funcao,
        boolean isUsing2FA
) {
    public UsuarioSimpleDto(Usuario usuario) {
        this(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPais(),
                usuario.getFuncao().toString(),
                usuario.isUsing2FA()
        );
    }
}
