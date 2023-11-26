package api.tech.hub.techhubapi.entity.conversa;

import api.tech.hub.techhubapi.entity.Arquivo;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Sala sala;

    @Size(max = 200000)
    private String texto;
    private LocalDateTime dtMensagem;

    @OneToMany(mappedBy = "mensagem")
    private List<Arquivo> arquivos;
}
