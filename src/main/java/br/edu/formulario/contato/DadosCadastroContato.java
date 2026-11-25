package br.edu.formulario.contato;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroContato(
	@NotNull(message = "O nome é obrigatório")
    String nome,
    @NotNull(message = "O telefone é obrigatório")
    String telefone,
    String cargo,

    @NotNull(message = "O email é obrigatório")
    @Email
    String email,
    @NotNull(message = "O CNPJ da empresa é obrigatório")
    String empresaCnpj
) {}