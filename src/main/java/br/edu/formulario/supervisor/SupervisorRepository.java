package br.edu.formulario.supervisor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {
    Optional<Supervisor> findByNome(String nome);
}