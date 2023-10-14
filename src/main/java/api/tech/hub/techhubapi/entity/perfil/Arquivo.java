package api.tech.hub.techhubapi.entity.perfil;

import api.tech.hub.techhubapi.entity.conversa.Mensagem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Arquivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeArquivoOriginal;
    private String nomeArquivoSalvo;
    private LocalDateTime dataUpload;
    private Enum tipoArquivo;

    @ManyToOne
    private Mensagem mensagem;
    @ManyToOne
    private Perfil perfil;
}
