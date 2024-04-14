package api.tech.hub.techhubapi.service.arquivo.ftp.impl;

import static api.tech.hub.techhubapi.utils.FileConverter.convertToFile;

import api.tech.hub.techhubapi.entity.Arquivo;
import api.tech.hub.techhubapi.repository.ArquivoRepository;
import api.tech.hub.techhubapi.service.arquivo.ftp.FtpService;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import com.cloudinary.api.ApiResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.*;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "ftp.cloud.enabled", havingValue = "true")
public class FtpCloudServiceImpl implements FtpService {

    private final Cloudinary cloudinary;
    private final ArquivoRepository arquivoRepository;

    public String getArquivoUrl(Integer id, boolean download) {
        if (id == null) {
            return null;
        }

        Arquivo arquivo = arquivoRepository.findById(id)
              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Arquivo não encontrado"));

        try {
            ApiResponse apiResponse = cloudinary.api()
                  .resourceByAssetID(arquivo.getNomeArquivoSalvo(), ObjectUtils.emptyMap());
            String resourceType = apiResponse.get("resource_type").toString();
            String secureUrl = apiResponse.get("secure_url").toString();

            if (resourceType.equals("raw") || download) {
                return cloudinary.url()
                      .resourceType(resourceType)
                      .transformation(new Transformation().flags(
                            "attachment:" + arquivo.getNomeArquivoOriginal().split("\\.")[0])
                      ).generate(
                            apiResponse.get("public_id").toString());
            }

            return secureUrl;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                  "Erro ao obter arquivo");
        }
    }

    public Arquivo salvarArquivo(MultipartFile arquivo, TipoArquivo tipoArquivo) {
        try {
            Map uploadResult = cloudinary
                  .uploader()
                  .upload(convertToFile(arquivo), getParams(tipoArquivo, arquivo));

            Arquivo arquivoSalvo = Arquivo.builder()
                  .nomeArquivoOriginal(arquivo.getOriginalFilename())
                  .nomeArquivoSalvo(uploadResult.get("asset_id").toString())
                  .tipoArquivo(tipoArquivo)
                  .build();

            return arquivoRepository.save(arquivoSalvo);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                  "Erro ao salvar arquivo");
        }
    }


    public Arquivo atualizarArquivo(Integer id, MultipartFile arquivo, TipoArquivo tipoArquivo) {

        Arquivo arquivoAtual = arquivoRepository.findById(id)
              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Arquivo não encontrado"));

        try {
            Map uploadResult = cloudinary
                  .uploader()
                  .upload(convertToFile(arquivo), getParams(tipoArquivo, arquivo));

            arquivoAtual.setNomeArquivoOriginal(arquivo.getOriginalFilename());
            arquivoAtual.setNomeArquivoSalvo(uploadResult.get("asset_id").toString());
            arquivoAtual.setTipoArquivo(tipoArquivo);
            arquivoAtual.setDataUpload(LocalDate.now());

            return arquivoRepository.save(arquivoAtual);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                  "Erro ao atualizar arquivo");
        }
    }

    private Map<String, String> getParams(TipoArquivo tipoArquivo, MultipartFile arquivo) {
        return ObjectUtils.asMap(
              "folder", tipoArquivo.name(),
              "use_filename", true,
              "use_filename_as_display_name", true,
              "resource_type", "auto",
              "format", arquivo.getOriginalFilename().split("\\.")[1]
        );
    }
}
