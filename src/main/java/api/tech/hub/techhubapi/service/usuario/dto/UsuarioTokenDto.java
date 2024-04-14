package api.tech.hub.techhubapi.service.usuario.dto;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.entity.usuario.UsuarioFuncao;
import api.tech.hub.techhubapi.service.arquivo.ArquivoService;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import api.tech.hub.techhubapi.service.arquivo.ftp.FtpService;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record UsuarioTokenDto(
      Integer id,
      String nome,
      @Enumerated(EnumType.STRING)
      UsuarioFuncao funcao,
      String pais,
      String urlFotoPerfil,
      Boolean isUsing2FA,
      String secretQrCodeUrl,
      String secret,
      String token
) {

    public UsuarioTokenDto(Usuario usuario, String token, String secretQrCodeUrl, String secret,
          FtpService ftpService) {
        this(
              usuario.getId(),
              usuario.getNome(),
              usuario.getFuncao(),
              usuario.getPais(),
              ftpService.getArquivoUrl(
                    ArquivoService.getArquivoOfPerfil(usuario.getPerfil(), TipoArquivo.PERFIL),
                    false),
              usuario.isUsing2FA(),
              secretQrCodeUrl,
              secret,
              token
        );
    }
}
