package br.edu.formulario.contato;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroContato(
    @NotBlank
    String nome,
    String telefone,
    String cargo,

    @NotBlank
    @Email
    String email,
    String empresaCnpj
) {}