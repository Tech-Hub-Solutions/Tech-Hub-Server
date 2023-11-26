package api.tech.hub.techhubapi.service.usuario.dto;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.entity.usuario.UsuarioFuncao;
import api.tech.hub.techhubapi.service.arquivo.ArquivoService;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record UsuarioTokenDto (
        Integer id,
        String nome,
        @Enumerated(EnumType.STRING)
        UsuarioFuncao funcao,
        String pais,
        String urlFotoPerfil,
        String token
){
    public UsuarioTokenDto(Usuario usuario, String token){
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getFuncao(),
                usuario.getPais(),
                ArquivoService.criarUrlArquivo(usuario.getPerfil(), TipoArquivo.PERFIL),
                token
        );
    }
}
