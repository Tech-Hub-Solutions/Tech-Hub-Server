package api.tech.hub.techhubapi.service.metricausuario.dto;

import api.tech.hub.techhubapi.entity.usuario.Usuario;

public record EmpresaInteressadaDto(
        Integer top,
        String nome,
        String email,
        String pais
) {
    public EmpresaInteressadaDto(Integer top, Usuario usuario) {
        this(top, usuario.getNome(), usuario.getEmail(), usuario.getPais());
    }
}