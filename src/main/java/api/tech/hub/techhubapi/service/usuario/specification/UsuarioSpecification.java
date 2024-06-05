package api.tech.hub.techhubapi.service.usuario.specification;

import api.tech.hub.techhubapi.entity.perfil.Avaliacao;
import api.tech.hub.techhubapi.entity.perfil.Perfil;
import api.tech.hub.techhubapi.entity.perfil.ReferenciaPerfil;
import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.entity.perfil.flag.FlagUsuario;
import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.entity.usuario.UsuarioFuncao;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public final class UsuarioSpecification {

    public static Specification<Usuario> hasFuncao(UsuarioFuncao usuarioFuncao) {
        return ((root, query, criteriaBuilder) -> {
            if (usuarioFuncao == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("funcao"), usuarioFuncao);
        });
    }

    public static Specification<Usuario> hasNome(String nome) {
        return ((root, query, criteriaBuilder) -> {
            if (nome == null || nome.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            Expression<String> nomeExp = criteriaBuilder.lower(root.get("nome"));
            String nomePattern = "%" + nome.toLowerCase() + "%";

            return criteriaBuilder.like(nomeExp, nomePattern);
        });
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

            query.distinct(true);
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
            precoExpression = criteriaBuilder.coalesce(precoExpression, 0.0);

            query.distinct(true);
            return criteriaBuilder.between(precoExpression, precoMin, precoMax);
        }));
    }

    public static Specification<Usuario> hasFlags(List<Flag> flags) {
        return (root, query, criteriaBuilder) -> {
            if (flags == null || flags.isEmpty()) {
                return criteriaBuilder.conjunction(); // Always true
            }

            // Ensure distinct results
            query.distinct(true);

            // Array to hold all the subqueries
            Predicate[] predicates = new Predicate[flags.size()];
            int index = 0;

            for (Flag flag : flags) {
                Subquery<FlagUsuario> flagSubquery = query.subquery(FlagUsuario.class);
                Root<FlagUsuario> flagSubqueryRoot = flagSubquery.from(FlagUsuario.class);

                // Constructing each subquery
                flagSubquery.select(flagSubqueryRoot)
                      .where(criteriaBuilder.and(
                            criteriaBuilder.equal(flagSubqueryRoot.get("flag").get("id"),
                                  flag.getId()),
                            criteriaBuilder.equal(flagSubqueryRoot.get("perfil"),
                                  root.get("perfil"))
                      ));

                // Checking for the existence of each subquery
                predicates[index++] = criteriaBuilder.exists(flagSubquery);
            }

            // Combine all predicates with AND
            return criteriaBuilder.and(predicates);
        };
    }


    public static Specification<Usuario> sort(String sort) {
        return (root, query, criteriaBuilder) -> {
            if (sort == null || sort.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            String[] sortSplit = sort.split(",");

            if (sortSplit.length != 2) {
                return criteriaBuilder.conjunction();
            }

            String sortField = sortSplit[0];
            String sortDirection = sortSplit[1];

            if (sortField.isBlank() || sortDirection.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            Join<Usuario, Perfil> perfilJoin = root.join("perfil");

            Expression<?> expression = null;

            switch (sortField) {
                case "preco" -> {
                    var coalesceValue = sortDirection.equals("asc") ? Double.MAX_VALUE : 0.0;
                    expression = criteriaBuilder.coalesce(perfilJoin.get("precoMedio"),
                          coalesceValue);
                }
                case "avaliacao" -> {
                    expression = perfilJoin.get("avaliacaoMedia");
                }
                default -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                          "O campo de ordenação não existe!");
                }
            }

            switch (sortDirection) {
                case "asc" -> {
                    query.orderBy(criteriaBuilder.asc(expression));
                }
                case "desc" -> {
                    query.orderBy(criteriaBuilder.desc(expression));
                }
                default -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                          "A direção de ordenação não existe!");
                }
            }

            return criteriaBuilder.conjunction();
        };
    }


    public static Specification<Usuario> getFavoritos(Perfil perfil) {
        return ((root, query, criteriaBuilder) -> {
            Join<Usuario, Perfil> perfilJoin = root.join("perfil");
            Join<Perfil, ReferenciaPerfil> referenciaPerfilJoin = perfilJoin.join(
                  "referenciaPerfilList");

            Expression<Boolean> favorito = referenciaPerfilJoin.get("isFavorito");

            Predicate predicate = criteriaBuilder.and(
                  criteriaBuilder.equal(referenciaPerfilJoin.get("avaliador"), perfil),
                  criteriaBuilder.equal(favorito, true)
            );

            return predicate;

        });
    }

}
