package api.tech.hub.techhubapi.entity.usuario.chat;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode(of = "id")
@Data
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Sala sala;

    private String texto;
    private LocalDateTime dtMensagem;
}
