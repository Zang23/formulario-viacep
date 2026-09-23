package br.edu.formulario.estagiario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EstagiarioRepository extends JpaRepository<Estagiario, String>{

    List<Estagiario> findByContratoEmpresaCnpj(String cnpj);

}
