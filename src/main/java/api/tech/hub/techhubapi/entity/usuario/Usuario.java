package api.tech.hub.techhubapi.entity.usuario;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.conversa.Conversa;
import api.tech.hub.techhubapi.entity.conversa.Mensagem;
import api.tech.hub.techhubapi.entity.contrato.UsuarioContrato;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
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
    private UsuarioFuncao funcao;
    private boolean isAtivo;

    @OneToMany
    private List<UsuarioContrato> usuarioContratoList;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Conversa> conversaList;

}
