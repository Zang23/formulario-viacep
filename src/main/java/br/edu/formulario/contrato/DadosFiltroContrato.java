
package br.edu.formulario.contrato;

public record DadosFiltroContrato(
    String empresa,
    String estagiario,
    String area,
    String funcao,
    Integer cargaHoraria,
    Boolean bancoHoras,
    Boolean estagioArea,
    String cpfSupervisor
) {}
