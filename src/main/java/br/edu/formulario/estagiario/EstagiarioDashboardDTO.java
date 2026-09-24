package br.edu.formulario.estagiario;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstagiarioDashboardDTO {

    private String nomeEstagiario;
    private String emailEstagiario;
    private String telefoneEstagiario;
    private String curso;
    private String periodo;

    private String nomeEmpresa;
    private String area;

    private String nomeSupervisor;

    private Date inicio;
    private Date termino;
}
