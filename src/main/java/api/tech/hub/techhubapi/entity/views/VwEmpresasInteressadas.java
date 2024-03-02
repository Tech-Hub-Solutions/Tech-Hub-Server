package api.tech.hub.techhubapi.entity.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VwEmpresasInteressadas {

    @Id
    private Integer id;
    private Integer empresaId;
    private String empresaNome;
    private Integer avaliadorId;
    private Integer avaliadoId;
    private Double media_avaliacao;
}
