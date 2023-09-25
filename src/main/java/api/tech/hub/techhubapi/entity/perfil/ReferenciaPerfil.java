package api.tech.hub.techhubapi.entity.perfil;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(of="id")
@Data
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
