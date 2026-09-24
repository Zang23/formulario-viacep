package br.edu.formulario.estagiario;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import br.edu.formulario.contrato.Contrato;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "estagiario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cpf")
public class Estagiario{

    @Id
    private String cpf;

    private String nome;
    private String telefone;
    private String email;
    private String curso;
    private String periodo;
    private int semestre;

    @OneToOne
    @JoinColumn(name = "contrato_id", nullable = false, unique = true)
    private Contrato contrato;

    public Estagiario(DadosCadastroEstagiario dados) {
        this.cpf = dados.cpf();
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.curso = dados.curso();
        this.periodo = dados.periodo();
        this.semestre = dados.semestre();
    }

    public void atualizarInformacoes(DadosAtualizacaoEstagiario dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.curso() != null) {
            this.curso = dados.curso();
        }
        if (dados.periodo() != null) {
            this.periodo = dados.periodo();
        }
        if (dados.semestre() > 0) {
            this.semestre = dados.semestre();
        }
    }

}
