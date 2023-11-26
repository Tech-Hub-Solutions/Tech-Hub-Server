package api.tech.hub.techhubapi.service.perfil.dto;

import api.tech.hub.techhubapi.service.flag.dto.FlagDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerfilDetalhadoDto {

    private Integer id;
    private String sobreMim;
    private String experiencia;
    private String descricao;
    private Double precoMedio;
    private String nomeGithub;
    private String linkGithub;
    private String linkLinkedin;
    private List<FlagDto> flags;
}
