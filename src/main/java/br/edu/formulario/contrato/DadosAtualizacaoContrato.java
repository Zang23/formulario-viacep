package br.edu.formulario.contrato;

import java.sql.Date;

public record DadosAtualizacaoContrato(
    Long idContrato,
    String empresaCnpj,
    String supervisorId,
    String estagiarioId,
    Date inicio,
    Date termino,
    int cargaHoraria,
    String area,
    String funcao) {

}
