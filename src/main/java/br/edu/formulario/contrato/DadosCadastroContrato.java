package br.edu.formulario.contrato;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DadosCadastroContrato {
	private Long idContrato;
    private String empresaCnpj;
    private Long supervisorId;
    private String estagiarioId;
    private Date inicio;
    private Date termino;
    private int cargaHoraria;
    private String area;
    private String funcao;
    
	

}


