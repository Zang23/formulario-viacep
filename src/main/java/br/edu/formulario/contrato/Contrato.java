package br.edu.formulario.contrato;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import br.edu.formulario.empresa.Empresa;
import br.edu.formulario.estagiario.Estagiario;
import br.edu.formulario.supervisor.Supervisor;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "contrato")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idContrato")
public class Contrato {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idContrato;

    @ManyToOne
    @JoinColumn(name = "empresa_cnpj", nullable = false)
    Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "supervisor_id", nullable = false)
    Supervisor supervisor;

    @OneToMany(mappedBy = "contrato")
    private List<Estagiario> estagiarios;

    //@ForeignKey
    //private String empresaCnpj;

    //@ForeignKey
    //private Long supervisorIdSupervisor

    private Date inicio;
    private Date termino;
    private int cargaHoraria;
    private String area;
    private String funcao;

}
