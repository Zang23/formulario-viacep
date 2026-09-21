package br.edu.formulario.empresa;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoEmpresa(
    //Coloquei  @NotBlank porque o cnpj  é String e definimos ele como id na DER
    //Se preferir um outro id, como Long, o ideal é a gente usar o @NotNull
    @NotBlank
    String cnpj,
    String nome,
    String areaAtuacao,
    String cidade,
    String cep,
    String email,
    String telefone,
    String departamento,
    String linkedin,
    String cargo) {

}
