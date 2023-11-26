package api.tech.hub.techhubapi.entity.perfil;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReferenciaPerfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Perfil avaliador;
    @ManyToOne
    private Perfil avaliado;

    private boolean isFavorito;
    private boolean isRecomendado;
}
