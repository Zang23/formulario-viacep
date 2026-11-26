
package br.edu.formulario.contrato;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

public class ContratoSpecification {

    public static Specification<Contrato> comFiltros(
            DadosFiltroContrato filtros) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filtros.empresa() != null && !filtros.empresa().isBlank()) {
                var empresa = root.join("empresa", JoinType.LEFT);

                predicates.add(
                    criteriaBuilder.like(
                        criteriaBuilder.lower(empresa.get("nome")),
                        "%" + filtros.empresa().toLowerCase().trim() + "%"
                    )
                );
            }

            if (filtros.estagiario() != null && !filtros.estagiario().isBlank()) {
                var estagiario = root.join("estagiario", JoinType.LEFT);

                predicates.add(
                    criteriaBuilder.like(
                        criteriaBuilder.lower(estagiario.get("nome")),
                        "%" + filtros.estagiario().toLowerCase().trim() + "%"
                    )
                );
            }

            if (filtros.area() != null && !filtros.area().isBlank()) {
                predicates.add(
                    criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("area")),
                        "%" + filtros.area().toLowerCase().trim() + "%"
                    )
                );
            }

            if (filtros.funcao() != null && !filtros.funcao().isBlank()) {
                predicates.add(
                    criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("funcao")),
                        "%" + filtros.funcao().toLowerCase().trim() + "%"
                    )
                );
            }

            if (filtros.cargaHoraria() != null) {
                predicates.add(
                    criteriaBuilder.equal(
                        root.get("cargaHoraria"),
                        filtros.cargaHoraria()
                    )
                );
            }

            if (filtros.bancoHoras() != null) {
                predicates.add(
                    criteriaBuilder.equal(
                        root.get("bancoHoras"),
                        filtros.bancoHoras()
                    )
                );
            }

            if (filtros.estagioArea() != null) {
                predicates.add(
                    criteriaBuilder.equal(
                        root.get("estagioArea"),
                        filtros.estagioArea()
                    )
                );
            }

            if (filtros.cpfSupervisor() != null &&
                    !filtros.cpfSupervisor().isBlank()) {

                String cpf = filtros.cpfSupervisor()
                    .replaceAll("\\D", "");

                var cpfBanco = root.get("cpfSupervisor");

                predicates.add(
                    criteriaBuilder.like(
                        criteriaBuilder.function(
                            "replace",
                            String.class,
                            criteriaBuilder.function(
                                "replace",
                                String.class,
                                criteriaBuilder.function(
                                    "replace",
                                    String.class,
                                    cpfBanco,
                                    criteriaBuilder.literal("."),
                                    criteriaBuilder.literal("")
                                ),
                                criteriaBuilder.literal("-"),
                                criteriaBuilder.literal("")
                            ),
                            criteriaBuilder.literal(" "),
                            criteriaBuilder.literal("")
                        ),
                        "%" + cpf + "%"
                    )
                );
            }

            return criteriaBuilder.and(
                predicates.toArray(new Predicate[0])
            );
        };
    }
}
