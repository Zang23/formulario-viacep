package br.edu.formulario.estagiario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EstagiarioRepository extends JpaRepository<Estagiario, Long> {
    @Query("select c.estagiario from Contrato c where c.empresa.cnpj = :cnpj")
    List<Estagiario> findByContratoEmpresaCnpj(@Param("cnpj") String cnpj);
    
}
