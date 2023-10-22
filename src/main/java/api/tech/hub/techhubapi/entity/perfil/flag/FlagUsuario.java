package api.tech.hub.techhubapi.entity.perfil.flag;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlagUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Flag flag;
    @ManyToOne(fetch = FetchType.LAZY)
    private Perfil perfil;
}
