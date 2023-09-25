package api.tech.hub.techhubapi.entity.usuario.chat;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@EqualsAndHashCode(of="id")
@Data
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roomCode;
    @OneToMany
    private List<Conversa> conversaList;
    @OneToMany
    private List<Mensagem> mensagemList;
}
