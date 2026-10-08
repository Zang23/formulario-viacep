package br.edu.formulario.contrato;

import java.sql.Date;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroContrato (
    @NotNull
	Long idContrato,
    String empresaCnpj,
    String supervisorCpf,
    Long estagiarioId,
    Date inicio,
    Date termino,
    int cargaHoraria,
    String area,
    String funcao
){
    public DadosCadastroContrato() {
        this(null, "", "", null, null, null, 0, "", "");
    }
}


