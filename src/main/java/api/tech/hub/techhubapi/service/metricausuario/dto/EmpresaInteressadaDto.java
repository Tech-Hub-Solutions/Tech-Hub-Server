package api.tech.hub.techhubapi.service.metricausuario.dto;

import api.tech.hub.techhubapi.entity.usuario.Usuario;

public record EmpresaInteressadaDto(
      Integer top,
      Integer id,
      String nome,
      String email,
      String pais,
      String url
) {

    public EmpresaInteressadaDto(Integer top, Usuario usuario, String basePerfilClient) {
        this(top, usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getPais(),
              String.format("%s/%d", basePerfilClient, usuario.getId()));
    }
}