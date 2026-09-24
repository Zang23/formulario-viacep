package br.edu.formulario;

import java.time.LocalTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormularioDTO {

    // =========================
    // ESTAGIÁRIO
    // =========================

    private String NomeEstagiario;
    private String CpfEstagiario;
    private String TelefoneEstagiario;
    private String EmailEstagiario;
    private String CursoEstagiario;
    private String PeriodoEstagiario;


    // =========================
    // EMPRESA
    // =========================

    private String NomeEmpresa;
    private String AreaEmpresa;
    private String CnpjEmpresa;
    private String CidadeEmpresa;
    private String CepEmpresa;
    private String EmailEmpresa;
    private String SiteEmpresa;
    private String TelefoneEmpresa;
    private String LinkedinEmpresa;


    // =========================
    // CONTRATO / ESTÁGIO
    // =========================

    private LocalTime HorarioEntrada;
    private LocalTime HorarioSaida;
    private String CargaHoraria;
    private String Departamento;


    // =========================
    // SUPERVISOR
    // =========================

    private Long Supervisor;
    private String NomeSupervisor;
    private String CargoSupervisor;
}
