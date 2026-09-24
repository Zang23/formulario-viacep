package br.edu.formulario.contrato;

import java.time.LocalDate;

public record DadosCadastroContrato(
    Long idContrato,
    String empresaCnpj,
    String supervisorId,
    String estagiarioId,
    LocalDate inicio,
    LocalDate termino,
    int cargaHoraria,
    String area,
    String funcao) {

}
