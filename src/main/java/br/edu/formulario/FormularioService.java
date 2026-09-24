package br.edu.formulario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.formulario.contrato.*;
import br.edu.formulario.empresa.*;
import br.edu.formulario.estagiario.*;
import br.edu.formulario.supervisor.*;

import lombok.RequiredArgsConstructor;
import jakarta.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class FormularioService {

	private final EstagiarioRepository estagiarioRepository;
	private final EmpresaRepository empresaRepository;
	private final ContratoRepository contratoRepository;
	private final SupervisorRepository supervisorRepository;
	private final EntityManager entityManager;
	@Transactional
	public void salvar(FormularioDTO form) {

		/*
		 * ============================== ESTAGIÁRIO ==============================
		 */

		Estagiario estagiario = new Estagiario();

		estagiario.setNome(form.getNomeEstagiario());
		estagiario.setCpf(form.getCpfEstagiario());
		estagiario.setTelefone(form.getTelefoneEstagiario());
		estagiario.setEmail(form.getEmailEstagiario());
		estagiario.setCurso(form.getCursoEstagiario());
		estagiario.setPeriodo(form.getPeriodoEstagiario());

		/*
		 * ============================== EMPRESA ==============================
		 */

		Empresa empresa = new Empresa();

		empresa.setNome(form.getNomeEmpresa());
		empresa.setSetor(form.getAreaEmpresa());
		empresa.setCnpj(form.getCnpjEmpresa());
		empresa.setCidade(form.getCidadeEmpresa());
		empresa.setCep(form.getCepEmpresa());
		empresa.setEmail(form.getEmailEmpresa());
		empresa.setSite(form.getSiteEmpresa());
		empresa.setTelefone(form.getTelefoneEmpresa());
		empresa.setLinkedin(form.getLinkedinEmpresa());

		/*
		 * ============================== SUPERVISOR ==============================
		 */

		Supervisor supervisor = null;

		if (form.getSupervisor() != null) {

			supervisor = supervisorRepository.findById(form.getSupervisor())
					.orElseThrow(() -> new RuntimeException("Supervisor não encontrado"));

		} else {

			supervisor = new Supervisor();

			supervisor.setNome(form.getNomeSupervisor());
			supervisor.setCargo(form.getCargoSupervisor());

			supervisor = supervisorRepository.save(supervisor);
		}

		/*
		 * ============================== CONTRATO ==============================
		 */

		Contrato contrato = new Contrato();

		LocalTime horarioEntrada = form.getHorarioEntrada();
		LocalTime horarioSaida = form.getHorarioSaida();

		Date dataEntrada = Date
				.from(LocalDate.of(2000, 1, 1).atTime(horarioEntrada).atZone(ZoneId.systemDefault()).toInstant());

		Date dataSaida = Date
				.from(LocalDate.of(2000, 1, 1).atTime(horarioSaida).atZone(ZoneId.systemDefault()).toInstant());

		contrato.setInicio(new java.sql.Date(dataEntrada.getTime()));
		contrato.setTermino(new java.sql.Date(dataSaida.getTime()));


		contrato.setCargaHoraria(Integer.parseInt(form.getCargaHoraria()));

		contrato.setArea(form.getDepartamento());
		/*
		 * ============================== RELACIONAMENTOS ==============================
		 */

		contrato.setEmpresa(empresa);
		contrato.setSupervisor(supervisor);

		empresaRepository.save(empresa);
		supervisorRepository.save(supervisor);
		contratoRepository.save(contrato);

		estagiario.setContrato(contrato);
		estagiarioRepository.save(estagiario);



	}
}
