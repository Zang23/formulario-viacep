package br.edu.formulario.contrato;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    //Encontrar contratos por CNPJ da empresa
    List<Contrato> findByEmpresaCnpj(String cnpj);
    //Encontrar contratos por ID do supervisor
    List<Contrato> findBySupervisor_Id(Long id);


}
