package br.edu.formulario.escola;

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
@Table(name = "escola")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Escola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int distancia;
    private boolean ensinoTecnico;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public void setEnsinoTecnico(boolean ensinoTecnico) {
        this.ensinoTecnico = ensinoTecnico;
    }


}