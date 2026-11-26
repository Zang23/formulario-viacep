package br.edu.formulario.estagiario;

import br.edu.formulario.contrato.Contrato;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Join;

public class EstagiarioSpecification {

    public static Specification<Estagiario> comFiltros(DadosFiltroEstagiario filtros) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filtros.nome() != null && !filtros.nome().isBlank()) {
                predicates.add(
                    criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nome")),
                        "%" + filtros.nome().toLowerCase().trim() + "%"
                    )
                );
            }

            if (filtros.genero() != null && !filtros.genero().isBlank()) {
                predicates.add(
                    criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get("genero")),
                        filtros.genero().toLowerCase().trim()
                    )
                );
            }

            if (filtros.cidade() != null && !filtros.cidade().isBlank()) {
                predicates.add(
                    criteriaBuilder.like(
                        criteriaBuilder.lower(
                            root.join("endereco", JoinType.LEFT).get("localidade")
                        ),
                        "%" + filtros.cidade().toLowerCase().trim() + "%"
                    )
                );
            }

            if (filtros.empresa() != null && !filtros.empresa().isBlank()) {

                Join<Estagiario, Contrato> contrato =
                    root.join("contratos");

                predicates.add(
                    criteriaBuilder.equal(
                        contrato.get("empresa").get("cnpj"),
                        filtros.empresa()
                    )
                );
            }

            if (filtros.area() != null && !filtros.area().isBlank()) {

                Join<Estagiario, Contrato> contrato =
                    root.join("contratos");

                predicates.add(
                    criteriaBuilder.equal(
                        contrato.get("area"),
                        filtros.area()
                    )
                );
            }

            if ((filtros.empresa() != null && !filtros.empresa().isBlank())
                    || (filtros.area() != null && !filtros.area().isBlank())) {

                Join<Estagiario, Contrato> contrato =
                    root.join("contratos");

                if (filtros.empresa() != null && !filtros.empresa().isBlank()) {
                    predicates.add(
                        criteriaBuilder.equal(
                            contrato.get("empresa").get("cnpj"),
                            filtros.empresa()
                        )
                    );
                }

                if (filtros.area() != null && !filtros.area().isBlank()) {
                    predicates.add(
                        criteriaBuilder.equal(
                            contrato.get("area"),
                            filtros.area()
                        )
                    );
                }

                query.distinct(true);
            }

            if (filtros.situacao() != null && !filtros.situacao().isBlank()) {

                Join<Estagiario, Contrato> contrato = root.join("contratos");

                LocalDate hoje = LocalDate.now();

                switch (filtros.situacao()) {

                    case "PENDENTE" -> predicates.add(
                        criteriaBuilder.greaterThan(
                            contrato.get("dataInicio"), hoje
                        )
                    );

                    case "ATIVO" -> {
                        predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                contrato.get("dataInicio"), hoje
                            )
                        );

                        predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                contrato.get("dataTermino"), hoje
                            )
                        );
                    }

                    case "ENCERRADO" -> predicates.add(
                        criteriaBuilder.lessThan(
                            contrato.get("dataTermino"), hoje
                        )
                    );
                }

                query.distinct(true);
            }

            return criteriaBuilder.and(
                predicates.toArray(new Predicate[0])
            );
        };
    }
}
