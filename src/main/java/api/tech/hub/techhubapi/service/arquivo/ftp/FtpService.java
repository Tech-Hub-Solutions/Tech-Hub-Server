package api.tech.hub.techhubapi.service.arquivo.ftp;

import api.tech.hub.techhubapi.entity.Arquivo;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import org.springframework.cglib.core.Local;
import org.springframework.web.multipart.MultipartFile;

public interface FtpService {

    String getArquivoUrl(Integer id, boolean download);

    Arquivo salvarArquivo(MultipartFile arquivo, TipoArquivo tipoArquivo);

    Arquivo atualizarArquivo(Integer id, MultipartFile arquivo, TipoArquivo tipoArquivo);
}
