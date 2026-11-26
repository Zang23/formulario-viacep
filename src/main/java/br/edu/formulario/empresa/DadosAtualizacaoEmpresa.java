
package br.edu.formulario.empresa;

import jakarta.validation.constraints.*;

public record DadosAtualizacaoEmpresa(

    @NotBlank(message = "O CNPJ é obrigatório")
    @Pattern(
        regexp = "\\d{14}",
        message = "O CNPJ deve conter 14 números"
    )
    String cnpj,

    @NotBlank(message = "O nome é obrigatório")
    @Pattern(
        regexp = "^[\\p{L} .'-]+$",
        message = "O nome não pode conter números ou caracteres inválidos"
    )
    String nome,

    @NotBlank(message = "A razão social é obrigatória")
    String razaoSocial,

    @NotBlank(message = "O segmento de mercado é obrigatório")
    String segmentoMercado,

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Informe um e-mail válido")
    String email,

    @Pattern(
        regexp = "^$|^(https?://)?[\\w.-]+\\.[a-zA-Z]{2,}.*$",
        message = "Informe um site válido"
    )
    String site,

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(
        regexp = "^[0-9()\\s+-]{10,20}$",
        message = "Informe um telefone válido"
    )
    String telefone,

    @NotBlank(message = "O departamento é obrigatório")
    String departamento,

    String linkedin,

    // Endereço
    String cep,
    String logradouro,
    String bairro,
    String numPorta,
    String uf,
    String localidade,
    String complemento

) {}
