
package br.edu.formulario.empresa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.edu.formulario.endereco.Endereco;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

public class EmpresaSpecification {

    public static Specification<Empresa> comFiltros(DadosFiltroEmpresa filtros) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filtros.nome() != null && !filtros.nome().isBlank()) {
                predicates.add(
                    criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nome")),
                        "%" + filtros.nome().toLowerCase() + "%"
                    )
                );
            }

            if (filtros.cnpj() != null && !filtros.cnpj().isBlank()) {
                predicates.add(
                    criteriaBuilder.like(
                        root.get("cnpj"),
                        "%" + filtros.cnpj() + "%"
                    )
                );
            }

            if (filtros.segmentoMercado() != null
                    && !filtros.segmentoMercado().isBlank()) {

                predicates.add(
                    criteriaBuilder.equal(
                        root.get("segmentoMercado"),
                        filtros.segmentoMercado()
                    )
                );
            }

            if (filtros.cidade() != null && !filtros.cidade().isBlank()) {

                Join<Empresa, Endereco> endereco =
                    root.join("endereco", JoinType.LEFT);

                predicates.add(
                    criteriaBuilder.like(
                        criteriaBuilder.lower(endereco.get("localidade")),
                        "%" + filtros.cidade().toLowerCase() + "%"
                    )
                );
            }

            return criteriaBuilder.and(
                predicates.toArray(new Predicate[0])
            );
        };
    }
}
