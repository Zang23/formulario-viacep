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
@EqualsAndHashCode(of = "ra")
public class Estagiario{

    @Id
    private int ra;
    private String nome;
    private String escolaridade;
    private String telefone;
    private String email;
    private int semestre;
    private String periodo;
    private String curso;
    private char genero;
    private String etnia;
    private double rendaFamiliar;
    private boolean pcd;
    private boolean trabalhaNaArea;

    @OneToOne
    @JoinColumn(name = "contrato_id", nullable = false, unique = true)
    private Contrato contrato;

    //O construtor usa os dados da DTO para criar um novo estagiário
    public Estagiario() {
        this.ra = dados.ra();
        this.nome = dados.nome();
        this.escolaridade = dados.escolaridade();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.semestre = dados.semestre();
        this.periodo = dados.periodo();
        this.curso = dados.curso();
        this.genero = dados.genero();
        this.etnia = dados.etnia();
        this.rendaFamiliar = dados.rendaFamiliar();
        this.pcd = dados.pcd();
        this.trabalhaNaArea = dados.trabalhaNaArea();
    }

    public void atualizarInformacoes(DadosAtualizacaoEstagiario dados) {
        if (dados.nome() != null) {
        this.nome = dados.nome();
        }
        if (dados.escolaridade() != null) {
            this.escolaridade = dados.escolaridade();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.semestre() > 0) {
            this.semestre = dados.semestre();
        }
        if (dados.periodo() != null) {
            this.periodo = dados.periodo();
        }
        if (dados.curso() != null) {
            this.curso = dados.curso();
        }
        if (dados.genero() != ' ') { 
            this.genero = dados.genero();
        }
        if (dados.etnia() != null) {
            this.etnia = dados.etnia();
        }
        if (dados.rendaFamiliar() > 0) {
            this.rendaFamiliar = dados.rendaFamiliar();
        }
        
        this.pcd = dados.pcd();
        this.trabalhaNaArea = dados.trabalhaNaArea();
    }

}
