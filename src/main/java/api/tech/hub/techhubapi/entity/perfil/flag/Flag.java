package api.tech.hub.techhubapi.entity.perfil.flag;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String area;
    private String categoria;

    @OneToMany
    private List<FlagUsuario> flagUsuarioList;
}
