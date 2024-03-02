package api.tech.hub.techhubapi.service.metricausuario.dto;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.entity.usuario.UsuarioFuncao;

public record EmpresaInteressadaDto(
        Integer top,
        String nome,
        String email,
        String numeroCadastroPessoa,
        String pais,
        UsuarioFuncao funcao


) {
    public EmpresaInteressadaDto(Integer top, Usuario usuario) {
        this(top, usuario.getNome(), usuario.getEmail(), usuario.getNumeroCadastroPessoa(),
                usuario.getPais(), usuario.getFuncao());
    }
}