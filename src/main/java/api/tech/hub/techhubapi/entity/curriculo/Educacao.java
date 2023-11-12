package api.tech.hub.techhubapi.entity.curriculo;

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
public class Educacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String instituicao;
    private String formacao;
    private LocalDate dtInicio;
    private String dtFim;

    @ManyToOne
    private Curriculo curriculo;
}
