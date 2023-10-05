package api.tech.hub.techhubapi.entity.conversa;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
