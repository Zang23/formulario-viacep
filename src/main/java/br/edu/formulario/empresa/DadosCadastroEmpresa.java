package br.edu.formulario.empresa;
import jakarta.validation.constraints.NotBlank;
// Fiz de acordo com o da profª
public record DadosCadastroEmpresa(

    @NotBlank
    String cnpj,
    String nome,
    String area,
    String cidade,
    String cep,
    String email,
    String telefone,
    String departamento,
    String linkedin,
    String site) {

}
