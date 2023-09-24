package api.tech.hub.techhubapi.entity.usuario.contrato;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@EqualsAndHashCode(of="id")
@Data
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String status;
    private String nomeProjeto;
    private Double preco;
    private String servicoEstimado;
    private LocalDateTime dtEstimada;
    private String telefone;
    private String email;
    private String modalidade;

    @OneToMany
    private List<UsuarioContrato> usuarioContratoList;
}
