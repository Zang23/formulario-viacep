package br.edu.formulario.empresa;
//import java.io.Serializable;
import java.util.List;

import br.edu.formulario.contrato.Contrato;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empresa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cnpj")
public class Empresa {

    @Id
    private String cnpj;
    private String nome;

    private String telefone;
    private String email;
    private String area;
    private String cidade;
    private String cep;
    private String site;
    private String departamento;
    private String linkedin;

    @OneToMany(mappedBy = "empresa")
    List<Contrato> contratos;

    public Empresa(DadosCadastroEmpresa dados) {
        this.cnpj = dados.cnpj();
        this.nome = dados.nome();
        this.area = dados.area();
        this.cidade = dados.cidade();
        this.cep = dados.cep();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.departamento = dados.departamento();
        this.linkedin = dados.linkedin();
        this.site = dados.site();
    }
    
    public void atualizarInformacoes(DadosAtualizacaoEmpresa dados) {
    if (dados.nome() != null) {
        this.nome = dados.nome();
    }
    if (dados.area() != null) {
        this.area = dados.area();
    }
    if (dados.cidade() != null) {
        this.cidade = dados.cidade();
    }
    if (dados.cep() != null) {
        this.cep = dados.cep();
    }
    if (dados.email() != null) {
        this.email = dados.email();
    }
    if (dados.telefone() != null) {
        this.telefone = dados.telefone();
    }
    if (dados.departamento() != null) {
        this.departamento = dados.departamento();
    }
    if (dados.linkedin() != null) {
        this.linkedin = dados.linkedin();
    }
    if (dados.site() != null) {
        this.site = dados.site();
    }
}
}
