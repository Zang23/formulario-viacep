package br.edu.formulario.viacep;

public record ViaCepEndereco(
    String cep,
    String logradouro,
    String complemento,
    String bairro,
    String localidade,
    String uf,
    String erro
) {
}
