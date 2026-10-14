package br.edu.formulario.empresa;
import jakarta.validation.constraints.NotBlank;
// Fiz de acordo com o da profª
public record DadosCadastroEmpresa(

    @NotBlank
    String cnpj,
    String nome,
    String razaoSocial,
    String SegmentoMercado,
    String email,
    String site,
    String telefone,
    String departamento,
    String linkedin,
    
    //endereço
    String cep,
    String logradouro,
    String bairro,
    String numPorta,
    String uf,
    String localidade,
    String complemento) {

}


