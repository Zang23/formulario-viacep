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
        Estagiario estagiario = estagiarioRepository.getReferenceById(dados.ra());
        estagiario.atualizarInformacoes(dados);
    }

    public Estagiario findByRa(Long ra) {
        return estagiarioRepository.findById(ra)
                .orElseThrow(() -> new RuntimeException("Estagiário não encontrado com o RA: " + ra));
    }

    public List<Estagiario> buscarPorCnpjEmpresa(String cnpj) {
        return estagiarioRepository.findByContratoEmpresaCnpj(cnpj);
    }

    @Transactional
    public void deletarEstagiario(Long ra) {
        estagiarioRepository.deleteById(ra);
    }
}