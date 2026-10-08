package br.edu.formulario.estagiario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroEstagiario(
    @NotNull(message = "Informe o RA")
    Long ra,
    @NotBlank(message = "Informe o nome")
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
    // Formulário em branco (no lugar do construtor vazio)
    public static DadosCadastroEstagiario vazio() {
        return new DadosCadastroEstagiario(
            null, "", "", "", "", 1, "", "", "", "", 0.0, false, false,
            "", "", "", "", "", "", "", "", null, false);
    }
}