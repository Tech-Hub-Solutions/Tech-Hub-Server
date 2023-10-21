package api.tech.hub.techhubapi.service.usuario.specification;

import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.entity.perfil.flag.FlagUsuario;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public final class UsuarioSpecification {
    public static Specification<Usuario> hasFlags(List<Flag> flags) {
        return (root, query, criteriaBuilder) -> {
            if (flags == null || flags.isEmpty()) {
                return criteriaBuilder.conjunction(); // Always true
            }

            Join<Usuario, Perfil> perfilJoin = root.join("perfil");
            Join<Perfil, FlagUsuario> flagUsuarioJoin = perfilJoin.join("flagUsuarioList");
            Join<FlagUsuario, Flag> flagJoin = flagUsuarioJoin.join("flag");

            Predicate predicate = flagJoin.in(flags);
            return predicate;
        };
    }

    public static Specification<Usuario> hasNome(final String nome) {
        return (root, query, criteriaBuilder) -> {
            if (nome == null || nome.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            Expression<String> nomeExp = criteriaBuilder.lower(root.get("nome"));

            return criteriaBuilder.like(nomeExp, "%" + nome.toLowerCase() + "%");
        };
    }

    public static Specification<Usuario> hasArea(String area) {
        return ((root, query, criteriaBuilder) -> {
            if (area == null || area.isBlank()) {
                return criteriaBuilder.conjunction();
            }


            Join<Usuario, Perfil> perfilJoin = root.join("perfil");
            Join<Perfil, FlagUsuario> flagUsuarioJoin = perfilJoin.join("flagUsuarioList");
            Join<FlagUsuario, Flag> flagJoin = flagUsuarioJoin.join("flag");

            String areaPattern = "%" + area + "%";
            Expression<String> areaExp = criteriaBuilder.lower(flagJoin.get("area"));
            Predicate predicate = criteriaBuilder.like(areaExp, areaPattern);

            return predicate;
        });
    }

    public static Specification<Usuario> hasPrecoBetween(Double precoMin, Double precoMax) {
        return (((root, query, criteriaBuilder) -> {
            if (precoMin == null || precoMax == null) {
                return criteriaBuilder.conjunction();
            }


            Join<Usuario, Perfil> perfilJoin = root.join("perfil");

            Expression<Double> precoExpression = perfilJoin.get("precoMedio");

            return criteriaBuilder.between(precoExpression, precoMin, precoMax);
        }));
    }


}
