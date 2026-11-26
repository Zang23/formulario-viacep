
package br.edu.formulario.contrato;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContratoRepository extends
        JpaRepository<Contrato, Long>,
        JpaSpecificationExecutor<Contrato> {

    // Encontrar contratos por CNPJ da empresa
    List<Contrato> findByEmpresaCnpj(String cnpj);
}
