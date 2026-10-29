package br.edu.formulario.contrato;

import java.time.LocalTime;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoContrato(
    @NotNull
    Long idContrato,
    String empresaCnpj,
    String supervisorCpf,
    Long estagiarioId,
    LocalTime inicio,
    LocalTime termino,
    int cargaHoraria,
    String area,
    String funcao) {

}
