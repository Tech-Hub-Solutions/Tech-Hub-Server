package api.tech.hub.techhubapi.entity;

import api.tech.hub.techhubapi.entity.Perfil;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(of="id")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Perfil perfil;

    private String nome;
    private String email;
    private String numeroCadastroPessoa;
    private String pais;
    private String funcao;
    private Boolean isAtivo;
}
