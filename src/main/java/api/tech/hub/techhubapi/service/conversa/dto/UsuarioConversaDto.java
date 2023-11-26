package api.tech.hub.techhubapi.service.conversa.dto;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.arquivo.ArquivoService;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;

public record UsuarioConversaDto(
        Integer id,
        String nome,
        String urlFotoPerfil
) {

    public UsuarioConversaDto(Usuario usuario) {
        this(
                usuario.getId(), usuario.getNome(),
                ArquivoService.criarUrlArquivo(usuario.getPerfil(), TipoArquivo.PERFIL)
        );
    }

}
