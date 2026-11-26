package br.edu.formulario.contrato;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoContrato(
    @NotNull
    Long idContrato,
    String empresaCnpj,
    String supervisorCpf,
    Long estagiarioId,
    LocalDate dataInicio,
    LocalDate dataTermino,
    int cargaHoraria,
    String area,
    String funcao) {

}
