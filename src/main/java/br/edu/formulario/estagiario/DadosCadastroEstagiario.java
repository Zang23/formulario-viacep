
package br.edu.formulario.estagiario;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;


public record DadosCadastroEstagiario(

    @NotNull(message = "O RA é obrigatório")
    Long ra,

    @NotBlank(message = "O nome é obrigatório")
    String nome,

    @NotBlank(message = "A escolaridade é obrigatória")
    String escolaridade,

    @NotBlank(message = "O telefone é obrigatorio")
    String telefone,

    @NotBlank(message = "O email é obrigatorio")
    @Email(message = "Informe um email valido")
    String email,

    @NotNull(message = "O semestre é obrigatório")
    @Min(value = 1, message = "Selecione um semestre válido")
    Integer semestre,

    @NotBlank(message = "O período é obrigatório")
    String periodo,

    @NotBlank(message = "O curso é obrigatório")
    String curso,

    String genero,
    String etnia,

    Double rendaFamiliar,

    boolean pcd,
    boolean trabalhaNaArea,

    // Endereço
    String cep,
    String logradouro,
    String bairro,
    String numPorta,
    String uf,
    String localidade,
    String complemento,

    // Escola
    String escolaNome,
    Integer escolaDistancia,
    boolean ensinoTecnico
) {

    public static DadosCadastroEstagiario vazio() {
        return new DadosCadastroEstagiario(
            null, "", "", "", "",
            null, "", "", "", "",
            null, false, false,
            "", "", "", "", "", "", "",
            "", null, false
        );
    }
}
