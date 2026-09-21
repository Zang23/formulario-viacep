package br.edu.formulario.empresa;

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

}
