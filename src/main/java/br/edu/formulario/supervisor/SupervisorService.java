package br.edu.formulario.supervisor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupervisorService {

    @Autowired
    private SupervisorRepository supervisorRepository;

    public List<Supervisor> listarSupervisor() {
        return supervisorRepository.findAll();
    }

    @Transactional
    public Supervisor cadastrarSupervisor(DadosCadastroSupervisor dados) {
        Supervisor supervisor = new Supervisor(dados);
        return supervisorRepository.save(supervisor);
    }

    public Supervisor findByNome(String nome) {
        return supervisorRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Supervisor não encontrado com o nome: " + nome));
    }
}