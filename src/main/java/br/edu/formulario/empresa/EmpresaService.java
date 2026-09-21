package br.edu.formulario.empresa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {
    @Autowired 
    private EmpresaRepository empresaRepository;

    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    public Empresa cadastrarEmpresa(DadosCadastroEmpresa dados) {
        Empresa empresa = new Empresa(dados);
        return empresaRepository.save(empresa);
    }
    
    public void atualizarEmpresa(DadosAtualizacaoEmpresa dados) {
        Empresa empresa = empresaRepository.getReferenceById(dados.cnpj());
        empresa.atualizarInformacoes(dados);;
    }

    public List<Empresa> buscarPorCidade(String cidade) {
        return empresaRepository.findByCidade(cidade);
    }

    public List<Empresa> buscarPorAreaAtuacao(String areaAtuacao) {
        return empresaRepository.findByAreaAtuacao(areaAtuacao);
    }  

    public boolean verificarCnpjExistente(String cnpj) {
        return empresaRepository.existsByCnpj(cnpj);
    }

    public Empresa findByCnpj(String cnpj) {
        return empresaRepository.findById(cnpj)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com o CNPJ: " + cnpj));
    }

    public void deletarEmpresa(String cnpj) {
        empresaRepository.deleteById(cnpj);
    }

}
