package br.edu.formulario.endereco;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data 
@Entity
@Table(name = "endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 8)
    private String cep;

    private String logradouro;
    private String bairro;

    @Column(length = 6)
    private String numPorta;

    @Column(length = 2)
    private String uf;

    private String localidade;
    private String complemento;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setNumPorta(String numPorta) {
        this.numPorta = numPorta;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    
}