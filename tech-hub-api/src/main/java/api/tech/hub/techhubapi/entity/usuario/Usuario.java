package api.tech.hub.techhubapi.entity.usuario;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.usuario.chat.Conversa;
import api.tech.hub.techhubapi.entity.usuario.chat.Mensagem;
import api.tech.hub.techhubapi.entity.usuario.contrato.UsuarioContrato;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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
    private String senha;
    private String numeroCadastroPessoa;
    private String pais;
    private String funcao;
    private boolean isAtivo;

    @OneToMany
    private List<UsuarioContrato> usuarioContratoList;
    @OneToMany
    private List<Mensagem> mensagemList;
    @OneToMany
    private List<Conversa> conversaList;

}
