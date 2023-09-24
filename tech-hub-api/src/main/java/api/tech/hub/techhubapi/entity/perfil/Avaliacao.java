package api.tech.hub.techhubapi.entity.perfil;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(of="id")
@Data
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Perfil perfil;

    private String comentario;
    private Integer qtdEstrela;
}
