package api.tech.hub.techhubapi.entity.usuario;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.conversa.Conversa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Perfil perfil;

    private String nome;
    private String email;
    private String senha;
    private String numeroCadastroPessoa;
    private String pais;
    @Enumerated(EnumType.STRING)
    private UsuarioFuncao funcao;
    private boolean isAtivo;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Conversa> conversaList;

}
