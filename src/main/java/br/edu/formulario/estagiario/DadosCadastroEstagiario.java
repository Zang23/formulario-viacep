package br.edu.formulario.estagiario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroEstagiario(
    @NotBlank
    String cpf,
    String nome,
    String telefone,
    String email,
    String curso,
    String periodo,
    int semestre
) {

}
