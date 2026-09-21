package br.edu.formulario.empresa;
// Só agilizei essa parte p/ mim começar o projeto
// Mas pode editar da forma que achar melhor 
// By: Dai
import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@EqualsAndHashCode 
public class Empresa implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Deixei como String porque pode haver 0s a esquerda
    @Id
    private String cnpj;

    private String nome;
    private String areaAtuacao;
    private String cidade;
    private String cep;
    private String email;
    private String telefone;
    private String departamento;
    private String linkedin;
    private String cargo;
    
    //@OneToMany (mappedBy = "empresa")
    //private List<Contrato> contratos = new ArrayList();

    public Empresa(DadosCadastroEmpresa dados) {
        this.cnpj = dados.cnpj();
        this.nome = dados.nome();
        this.areaAtuacao = dados.areaAtuacao();
        this.cidade = dados.cidade();
        this.cep = dados.cep();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.departamento = dados.departamento();
        this.linkedin = dados.linkedin();
        this.cargo = dados.cargo();
    }
    
    public void atualizarInformacoes(DadosAtualizacaoEmpresa dados) {
    if (dados.nome() != null) {
        this.nome = dados.nome();
    }
    if (dados.areaAtuacao() != null) {
        this.areaAtuacao = dados.areaAtuacao();
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
    if (dados.cargo() != null) {
        this.cargo = dados.cargo();
    }
}
}
