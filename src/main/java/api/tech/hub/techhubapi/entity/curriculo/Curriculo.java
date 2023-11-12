package api.tech.hub.techhubapi.entity.curriculo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String telefone;
    private String email;
    private String resumo;

    @OneToMany(mappedBy = "curriculo")
    private List<ExperienciaProfissional> experienciaProfissional;

    @OneToMany(mappedBy = "curriculo")
    private List<Educacao> educacao;

    @OneToMany(mappedBy = "curriculo")
    private List<Habilidades> habilidades;

    @ManyToMany(mappedBy = "curriculo")
    private List<Idioma> idiomas;

    private String referencia;
}
