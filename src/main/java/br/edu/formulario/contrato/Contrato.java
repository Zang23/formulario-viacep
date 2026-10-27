package br.edu.formulario.contrato;

import java.time.LocalTime;

import br.edu.formulario.empresa.Empresa;
import br.edu.formulario.estagiario.Estagiario;
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
@Table (name = "contrato")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "idContrato")
public class Contrato {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idContrato;

    @ManyToOne
    @JoinColumn(name = "empresaCnpj", nullable = false)
    Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "estagiariora")
    private Estagiario estagiario;

    private LocalTime inicio;
    private LocalTime termino;
    private int cargaHoraria;
    private String area;
    private String funcao;
    private boolean bancoHoras;
    private String descricao;
    private boolean estagioArea;
    private String cpfSupervisor;

	public Contrato(DadosCadastroContrato dados) {
        this.idContrato = dados.idContrato();
        this.inicio = dados.inicio();
        this.termino = dados.termino();
        this.cargaHoraria = dados.cargaHoraria();
        this.area = dados.area();
        this.funcao = dados.funcao();
        this.bancoHoras = dados.bancoHoras();
        this.descricao = dados.descricao();
        this.estagioArea = dados.estagioArea();
        this.cpfSupervisor = dados.cpfSupervisor();
    }

    // public void atualizarInformacoes(DadosAtualizacaoContrato dados) {
    //     if (dados.inicio() != null) {
    //         this.inicio = dados.inicio();
    //     }
    //     if (dados.termino() != null) {
    //         this.termino = dados.termino();
    //     }
    //     if (dados.cargaHoraria() != 0) {
    //         this.cargaHoraria = dados.cargaHoraria();
    //     }
    //     if (dados.area() != null) {
    //         this.area = dados.area();
    //     }
    //     if (dados.funcao() != null) {
    //         this.funcao = dados.funcao();
    //     }
    // }

    void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    void setEstagiario(Estagiario estagiario) {
        this.estagiario = estagiario;
    }

}
