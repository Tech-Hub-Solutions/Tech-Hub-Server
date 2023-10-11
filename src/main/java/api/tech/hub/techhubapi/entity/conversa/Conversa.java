package api.tech.hub.techhubapi.entity.conversa;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Conversa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sala sala;

    boolean isAtivo;
}
