package api.tech.hub.techhubapi.entity.usuario.contrato;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(of="id")
@Data
public class UsuarioContrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private UsuarioContrato usuarioContrato;

    @ManyToOne
    private Contrato contrato;
}
