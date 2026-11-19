package br.edu.formulario.estagiario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DadosCadastroEstagiario(
    @NotNull(message = "O RA é obrigatório")
    Long ra,
    @NotBlank(message = "O nome é obrigatório")
    String nome,
    @NotBlank(message = "A escolaridade é obrigatório")
    String escolaridade,
    String telefone,
    String email,
    @NotBlank(message = "O semestre é obrigatório")
    int semestre,
    @NotBlank(message = "O periodo é obrigatório")
    String periodo,
    @NotBlank(message = "O curso é obrigatório")
    String curso,
    String genero,
    String etnia,
    double rendaFamiliar,
    @NotBlank(message = "O")
    boolean pcd,
    @NotBlank(message = "O")
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
) 
{
    // Formulário em branco (no lugar do construtor vazio)
    public static DadosCadastroEstagiario vazio() {
        return new DadosCadastroEstagiario(
            null, "", "", "", "", 1, "", "", "", "", 0.0, false, false,
            "", "", "", "", "", "", "", "", null, false);
    }
}