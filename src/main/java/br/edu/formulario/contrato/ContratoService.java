package br.edu.formulario.contrato;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.formulario.empresa.Empresa;
import br.edu.formulario.empresa.EmpresaRepository;
import br.edu.formulario.estagiario.Estagiario;
import br.edu.formulario.estagiario.EstagiarioRepository;
import jakarta.transaction.Transactional;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EstagiarioRepository estagiarioRepository;

    public List<Contrato> listarContratos() {
        return contratoRepository.findAll();
    }

    public Contrato findById(Long id) {
        return contratoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado com o ID: " + id));
    }

    @Transactional
    public Contrato cadastrarContrato(DadosCadastroContrato dados) {
        Empresa empresa = empresaRepository.findById(dados.empresaCnpj())
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada com CNPJ: " + dados.empresaCnpj()));

        Estagiario estagiario = estagiarioRepository.findById(dados.estagiarioId())
                .orElseThrow(() -> new IllegalArgumentException("Estagiário não encontrado com o RA: " + dados.estagiarioId()));

        Contrato contrato = new Contrato(dados);
        contrato.setEmpresa(empresa);
        contrato.setEstagiario(estagiario);

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
}