package br.edu.formulario.estagiario;

import br.edu.formulario.endereco.Endereco;
import br.edu.formulario.escola.Escola;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private Long ra;
    private String nome;
    private String escolaridade;
    private String telefone;
    private String email;
    private int semestre;
    private String periodo;
    private String curso;
    private String genero;
    private String etnia;
    private double rendaFamiliar;
    private boolean pcd;
    private boolean trabalhaNaArea;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "escola_id")
    private Escola escola;

    public Estagiario(DadosCadastroEstagiario dados) {
        this.ra = dados.ra();
        this.nome = dados.nome();
        this.escolaridade = dados.escolaridade();
        this.telefone = dados.telefone() == null ? null : dados.telefone().replaceAll("\\D", "");
        this.email = dados.email();
        this.semestre = dados.semestre();
        this.periodo = dados.periodo();
        this.curso = dados.curso();
        this.genero = dados.genero();
        this.etnia = dados.etnia();
        this.rendaFamiliar = dados.rendaFamiliar();
        this.pcd = dados.pcd();
        this.trabalhaNaArea = dados.trabalhaNaArea();

        Endereco end = new Endereco();
        end.setCep(dados.cep());
        end.setLogradouro(dados.logradouro());
        end.setBairro(dados.bairro());
        end.setNumPorta(dados.numPorta());
        end.setUf(dados.uf());
        end.setLocalidade(dados.localidade());
        end.setComplemento(dados.complemento());
        this.endereco = end;

        Escola esc = new Escola();
        esc.setNome(dados.escolaNome());
        if (dados.escolaDistancia() != null) {
            esc.setDistancia(dados.escolaDistancia());
        }
        esc.setEnsinoTecnico(dados.ensinoTecnico());
        this.escola = esc;
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
        if (dados.genero() != null && !dados.genero().isBlank()) {
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
