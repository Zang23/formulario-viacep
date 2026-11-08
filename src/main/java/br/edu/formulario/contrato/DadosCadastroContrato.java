package br.edu.formulario.contrato;

import java.time.LocalTime;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroContrato (
	Long idContrato,
    String empresaCnpj,
    String cpfSupervisor,
    Long estagiarioId,
    LocalTime inicio,
    LocalTime termino,
    int cargaHoraria,
    String area,
    String funcao,
    boolean bancoHoras,
    String descricao,
    boolean estagioArea
){
    public static DadosCadastroContrato vazio() {
        return new DadosCadastroContrato(null, "", "", null, null, null, 0, "", "", false, "", false);
    }
}
