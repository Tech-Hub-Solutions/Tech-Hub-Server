package api.tech.hub.techhubapi.entity.perfil;

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
    private String pathPerfilImage;
    private String pathWallpaperImage;
    private Double precoMedio;
    private String linkGithub;
    private String linkLinkedin;

    @OneToOne
    private Usuario usuario;
    @OneToMany
    private List<FlagUsuario> flagUsuarioList;
    @OneToMany
    private List<Avaliacao> avaliacaoList;
    @OneToMany
    private List<Projeto> projetoList;
    @OneToMany
    private List<ReferenciaPerfil> referenciaPerfilList;
}
