package br.edu.formulario.empresa;

import java.util.List;

import br.edu.formulario.contrato.Contrato;
import br.edu.formulario.endereco.Endereco;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@EqualsAndHashCode(of = "cnpj")
public class Empresa {

    @Id
    private String cnpj;

    private String nome;
    private String razaoSocial;
    private String segmentoMercado;

    private String email;
    private String site;

    private String telefone;
    private String departamento;
    private String linkedin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToMany(mappedBy = "empresa")
    List<Contrato> contratos;

    public Empresa(DadosCadastroEmpresa dados) {
        this.cnpj = dados.cnpj();
        this.nome = dados.nome();
        this.razaoSocial = dados.razaoSocial();
        this.segmentoMercado = dados.SegmentoMercado();
        this.email = dados.email();
        this.telefone = dados.telefone() == null ? null : dados.telefone().replaceAll("\\D", "");
        this.departamento = dados.departamento();
        this.linkedin = dados.linkedin();
        this.site = dados.site();

        Endereco end = new Endereco();
        end.setCep(dados.cep());
        end.setLogradouro(dados.logradouro());
        end.setBairro(dados.bairro());
        end.setNumPorta(dados.numPorta());
        end.setUf(dados.uf());
        end.setLocalidade(dados.localidade());
        end.setComplemento(dados.complemento());
        this.endereco = end;
    }

    public void atualizarInformacoes(DadosAtualizacaoEmpresa dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
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
        if (dados.site() != null) {
            this.site = dados.site();
        }
    }
}
