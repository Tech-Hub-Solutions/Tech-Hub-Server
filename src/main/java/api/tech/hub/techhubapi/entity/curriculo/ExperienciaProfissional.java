package api.tech.hub.techhubapi.entity.curriculo;

import api.tech.hub.techhubapi.entity.curriculo.Curriculo;
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
public class ExperienciaProfissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeEmpresa;
    private LocalDate dtInicio;
    private LocalDate dtFim;
    private String cargo;

    @ManyToOne
    private Curriculo curriculo;
}
