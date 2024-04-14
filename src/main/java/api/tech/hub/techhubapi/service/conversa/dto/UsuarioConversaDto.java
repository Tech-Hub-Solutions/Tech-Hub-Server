package api.tech.hub.techhubapi.service.conversa.dto;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.service.arquivo.ArquivoService;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import api.tech.hub.techhubapi.service.arquivo.ftp.FtpService;

public record UsuarioConversaDto(
      Integer id,
      String nome,
      String urlFotoPerfil
) {

    public UsuarioConversaDto(Usuario usuario, FtpService ftpService) {
        this(
              usuario.getId(), usuario.getNome(),
              ftpService.getArquivoUrl(
                    ArquivoService.getArquivoOfPerfil(usuario.getPerfil(), TipoArquivo.PERFIL),
                    false)
        );
    }

}
