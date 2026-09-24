package br.edu.formulario.supervisor;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroSupervisor(
    @NotBlank 
    Long id,
    String nome,
    String cargo
   ) {

}
