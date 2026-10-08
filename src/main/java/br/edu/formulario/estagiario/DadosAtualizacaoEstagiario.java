package br.edu.formulario.estagiario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoEstagiario(
    String nome,
    String escolaridade,
    String telefone,
    String email,
    int semestre,
    String periodo,
    String curso,
    char genero,
    String etnia,
    double rendaFamiliar,
    boolean pcd,
    boolean trabalhaNaArea  
) {

}
