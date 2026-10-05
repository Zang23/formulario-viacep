package br.edu.formulario.estagiario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstagiarioService {
    @Autowired 
    private EstagiarioRepository estagiarioRepository;

    public List<Estagiario> listarEstagiarios() {
        return estagiarioRepository.findAll();
    }

    @Transactional
    public Estagiario cadastrarEstagiario(DadosCadastroEstagiario dados) {
        Estagiario estagiario = new Estagiario(dados);
        return estagiarioRepository.save(estagiario);
    }

    @Transactional
    public void atualizarEstagiario(DadosAtualizacaoEstagiario dados) {
        Estagiario estagiario = estagiarioRepository.getReferenceById(dados.cpf());
        estagiario.atualizarInformacoes(dados);
    }

    public Estagiario findByCpf(String cpf){
        return estagiarioRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Estagiário não encontradp com o CPF: " + cpf));
    }

    public List<Estagiario> buscarPorCnpjEmpresa(String cnpj) {
        return estagiarioRepository.findByContratoEmpresaCnpj(cnpj);
    }

    @Transactional
    public void deletarEstagiario(String cpf) {
        estagiarioRepository.deleteById(cpf);
    }
    
}
