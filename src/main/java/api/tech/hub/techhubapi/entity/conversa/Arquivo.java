package api.tech.hub.techhubapi.entity.conversa;

import api.tech.hub.techhubapi.service.arquivo.TipoArquivo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
  private LocalDate dataUpload;
  private TipoArquivo tipoArquivo;
  @ManyToOne
  private Mensagem mensagem;
}
