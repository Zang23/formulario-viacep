package br.edu.formulario.contato;

import br.edu.formulario.empresa.Empresa;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contato")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private String cargo;
    private String email;

    @ManyToOne
    @JoinColumn(name = "empresacnpjpj")
    private Empresa empresa;

    public Contato(DadosCadastroContato dados, Empresa empresa) {
        this.nome = dados.nome();
        this.telefone = dados.telefone() == null ? null : dados.telefone().replaceAll("\\D", "");
        this.cargo = dados.cargo();
        this.email = dados.email();
        this.empresa = empresa;
    }
}