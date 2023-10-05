package api.tech.hub.techhubapi.entity.contrato;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioContrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private UsuarioContrato usuarioContrato;

    @ManyToOne
    private Contrato contrato;
}
