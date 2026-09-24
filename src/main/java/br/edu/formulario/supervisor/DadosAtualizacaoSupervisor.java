package br.edu.formulario.supervisor;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoSupervisor(
    @NotNull
    Long id,
    String nome,
    String cargo
) {
}