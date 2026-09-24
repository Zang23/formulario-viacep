package br.edu.formulario;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.formulario.estagiario.EstagiarioDashboardDTO;
import br.edu.formulario.contrato.Contrato;
import br.edu.formulario.contrato.ContratoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ContratoRepository contratoRepository;

    public List<EstagiarioDashboardDTO> listarEstagiarios() {

        List<Contrato> contratos = contratoRepository.findAll();

        return contratos.stream()
                .map(contrato -> new EstagiarioDashboardDTO(

                        contrato.getEstagiario().getNome(),
                        contrato.getEstagiario().getEmail(),
                        contrato.getEstagiario().getTelefone(),
                        contrato.getEstagiario().getCurso(),
                        contrato.getEstagiario().getPeriodo(),

                        contrato.getEmpresa().getNome(),

                        contrato.getArea(),

                        contrato.getSupervisor().getNome(),

                        contrato.getInicio(),
                        contrato.getTermino()

                ))
                .toList();
    }
}