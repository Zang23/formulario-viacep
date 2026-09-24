package br.edu.formulario.contrato;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.formulario.empresa.EmpresaRepository;
import jakarta.transaction.Transactional;

@Service
public class ContratoService {
    @Autowired 
    private ContratoRepository contratoRepository;
    
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private SupervisorRepository supervisorRepository;

    public List<Contrato> listarContratos() {
        return contratoRepository.findAll();
    }

    @Transactional 
    public Contrato cadastrarContrato(DadosCadastroContrato dados) {
        Empresa empresa = empresaRepository.findById(dados.empresaCnpj())
            .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada"));
        
        Supervisor supervisor = supervisorRepository.findById(dados.supervisorId())
            .orElseThrow(() -> new IllegalArgumentException("Supervisor não encontrado"));

        Contrato contrato = new Contrato(dados, empresa, supervisor);
        return contratoRepository.save(contrato);
    }
    
    @Transactional
    public void atualizarContrato(DadosAtualizacaoContrato dados) {
        Contrato contrato = contratoRepository.getReferenceById(dados.idContrato());
        contrato.atualizarInformacoes(dados);
    }

    @Transactional
    public void deletarContrato(Long idContrato) {
        contratoRepository.deleteById(idContrato);
    }

    public List<Contrato> buscarPorCnpjEmpresa(String cnpj) {
        return contratoRepository.findByEmpresaCnpj(cnpj);
    }

    public List<Contrato> buscarPorIdSupervisor(Long idSupervisor) {
        return contratoRepository.findBySupervisorIdSupervisor(idSupervisor);
    }
}
