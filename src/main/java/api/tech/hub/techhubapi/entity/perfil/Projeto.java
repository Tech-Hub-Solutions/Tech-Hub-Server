package api.tech.hub.techhubapi.entity.perfil;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Perfil perfil;

    private String titulo;
    private String descricao;
    private String pathImage;
    private String linkAcesso;
}
