package br.edu.formulario.contrato;

import java.time.LocalTime;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroContrato (
	Long idContrato,
    @NotNull(message = "O CNPJ da empresa é obrigatório")
    String empresaCnpj,
    @NotNull(message = "O CPF do supervisor é obrigatório")
    String cpfSupervisor,
    @NotNull(message = "O ID do estágiario é obrigatório")
    Long estagiarioId,
    @NotNull(message = "O horario de inicio é obrigatório")
    LocalTime inicio,
    @NotNull(message = "O horario de término é obrigatório")
    LocalTime termino,
    int cargaHoraria,
    String area,
    String funcao,
    boolean bancoHoras,
    String descricao,
    @NotNull(message = "A área de estágio é obrigatório")
    boolean estagioArea
){
    public static DadosCadastroContrato vazio() {
        return new DadosCadastroContrato(null, "", "", null, null, null, 0, "", "", false, "", false);
    }
}
