package br.edu.formulario.empresa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository 
@Transactional
public interface EmpresaRepository extends JpaRepository<Empresa, String> {
    //verifica se o cnpj existe
    boolean existsByCnpj(String cnpj);

    //busca por email
    @Query("SELECT e FROM Empresa e WHERE e.email = :email")
    Optional<Empresa> findByEmail(@Param("email") String email);


    //busca por razão social
    @Query("SELECT e FROM Empresa e WHERE e.razaoSocial = :razaoSocial")
    List<Empresa> findByRazaoSocial(@Param("razaoSocial") String razaoSocial);

}
