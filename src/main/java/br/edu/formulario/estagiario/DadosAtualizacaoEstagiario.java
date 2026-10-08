package br.edu.formulario.estagiario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoEstagiario(
    @NotNull 
    Long ra,
    String nome,
    String escolaridade,
    String telefone,
    String email,
    int semestre,
    String periodo,
    String curso,
    String genero,
    String etnia,
    double rendaFamiliar,
    boolean pcd,
    boolean trabalhaNaArea,
    // endereco
    String cep,
    String logradouro,
    String bairro,
    String numPorta,
    String uf,
    String localidade,
    String complemento,

    // escola
    String escolaNome,
    Integer escolaDistancia,
    boolean ensinoTecnico
) {

}
