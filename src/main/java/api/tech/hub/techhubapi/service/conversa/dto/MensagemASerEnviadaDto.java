package api.tech.hub.techhubapi.service.conversa.dto;

import api.tech.hub.techhubapi.entity.conversa.Mensagem;
import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public record MensagemASerEnviadaDto(
        Integer usuarioId,
        String texto,
        LocalDateTime dtHora,
        String urlArquivo,
        TipoArquivo tipoArquivo
) {
    public MensagemASerEnviadaDto(Mensagem mensagem) {
        this(
                mensagem.getUsuario().getId(),
                mensagem.getTexto(),
                mensagem.getDtMensagem(),
                gerarUrlArquivo(mensagem),
                obterTipoArquivo(mensagem)
        );
    }

    private static String gerarUrlArquivo(Mensagem mensagem) {
        if (mensagem.getArquivos() != null) {

            if(mensagem.getArquivos().isEmpty()) {
                return null;
            }

            String urlArquivo = "";
            TipoArquivo tipoArquivoImagem = mensagem.getArquivos().get(0).getTipoArquivo();

            if (tipoArquivoImagem.equals(TipoArquivo.DOCUMENTO)) {
                urlArquivo = "/file/" + mensagem.getArquivos().get(0).getId();
            } else if (tipoArquivoImagem.equals(TipoArquivo.IMAGEM)) {
                urlArquivo = "/image/" + mensagem.getArquivos().get(0).getId();
            }

            return urlArquivo;
        }

        return null;
    }

    private static TipoArquivo obterTipoArquivo(Mensagem mensagem) {
        // Verificando se a lista não é null e não está vazia
        if (mensagem.getArquivos() != null && !mensagem.getArquivos().isEmpty()) {
            return mensagem.getArquivos().get(0).getTipoArquivo();
        }
        return null;
    }
}
