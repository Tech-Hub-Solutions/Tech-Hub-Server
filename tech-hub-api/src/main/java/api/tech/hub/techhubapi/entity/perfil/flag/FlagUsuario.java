package api.tech.hub.techhubapi.entity.perfil.flag;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(of="id")
@Data
public class FlagUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Flag flag;
    @ManyToOne
    private Perfil perfil;
}
