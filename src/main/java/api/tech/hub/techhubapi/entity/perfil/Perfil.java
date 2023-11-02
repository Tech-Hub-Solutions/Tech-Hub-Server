package api.tech.hub.techhubapi.entity.perfil;

import api.tech.hub.techhubapi.entity.Arquivo;
import api.tech.hub.techhubapi.entity.perfil.flag.FlagUsuario;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String sobreMim;
    private String experiencia;
    private String descricao;
    private Double precoMedio;
    private String nomeGithub;
    private String linkGithub;
    private String linkLinkedin;

    @OneToOne(mappedBy = "perfil")
    private Usuario usuario;

    @OneToMany(mappedBy = "perfil")
    private List<FlagUsuario> flagUsuarioList;

    @OneToMany(mappedBy = "perfil")
    private List<Avaliacao> avaliacaoList;

    @OneToMany(mappedBy = "avaliador")
    private List<ReferenciaPerfil> referenciaPerfilList;

    @OneToMany(mappedBy = "perfil")
    private List<Arquivo> arquivos;
}
