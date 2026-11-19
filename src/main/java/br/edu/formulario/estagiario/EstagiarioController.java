package br.edu.formulario.estagiario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/estagiario")
public class EstagiarioController {

	@Autowired
	private EstagiarioService estagiarioService;

	@GetMapping("/formulario")
	public String novoEstagiario(Model model) {
		model.addAttribute("dados", DadosCadastroEstagiario.vazio());
		return "/estagiario/formulario";
	}

	@GetMapping("/dashboard")
	public String listaEstagiario(Model model) {
		List<Estagiario> estagiarios = estagiarioService.listarEstagiarios();
		model.addAttribute("estagiarios", estagiarios);
		model.addAttribute("totalEstagiarios", estagiarios.size());
		return "estagiario/lista";
	}

	/*
	 * @GetMapping("/formulario/editar") public String editarEstagiario(Model model)
	 * {
	 * 
	 * model.addAttribute("dados", DadosAtualizacaoEstagiario.class); return
	 * "/estagiario/formulario"; }
	 */

	@PostMapping("excluir/{ra}")
	public String deletarEstagiario(@PathVariable("ra") Long ra, HttpSession session,
			RedirectAttributes redirectAttributes, Model model) {
		try {		
			estagiarioService.deletarEstagiario(ra);
		}catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Erro ao deletar o estagiário: " + e.getMessage());
			return "estagiario/formulario";
		}
		return "estagiario/lista";
	}

	@GetMapping("editar/{ra}")
	public String carregarFormulario(@PathVariable("ra") Long ra, HttpSession session,
			RedirectAttributes redirectAttributes, Model model) {
		try {
			Estagiario estagiario = estagiarioService.findByRa(ra);
			DadosCadastroEstagiario dados = new DadosCadastroEstagiario(estagiario.getRa(), estagiario.getNome(),
					estagiario.getEscolaridade(), estagiario.getTelefone(), estagiario.getEmail(),
					estagiario.getSemestre(), estagiario.getPeriodo(), estagiario.getCurso(), estagiario.getGenero(),
					estagiario.getEtnia(), estagiario.getRendaFamiliar(), estagiario.isPcd(),
					estagiario.isTrabalhaNaArea(),

					// Endereço
					estagiario.getEndereco().getCep(), estagiario.getEndereco().getLogradouro(),
					estagiario.getEndereco().getBairro(), estagiario.getEndereco().getNumPorta(),
					estagiario.getEndereco().getUf(), estagiario.getEndereco().getLocalidade(),
					estagiario.getEndereco().getComplemento(),

					// Escola
					estagiario.getEscola().getNome(), estagiario.getEscola().getDistancia(),
					estagiario.getEscola().isEnsinoTecnico());
			model.addAttribute("dados", dados);
			return "estagiario/formulario";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Erro ao carregar o formulário: " + e.getMessage());
			return "estagiario/formulario";
		}
	}

	/*
	 * @GetMapping public String listarEstagiarios(Model model, HttpSession session)
	 * { model.addAttribute("activePage", "estagiarios");
	 * model.addAttribute("estagiarios", estagiarioService.listarEstagiarios());
	 * return "formularioest/lista"; }
	 */

	@PostMapping("/formulario/salvar")
	public String salvarEstagiario(@Valid @ModelAttribute("dados") DadosCadastroEstagiario dados, BindingResult result,
			HttpSession session, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "/estagiario/formulario";
		}
		try {
			estagiarioService.cadastrarEstagiario(dados);
			redirectAttributes.addFlashAttribute("message", "Estagiário salvo com sucesso!");
			return "redirect:/";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage",
					"Erro ao salvar dados do estagiário: " + e.getMessage());
			return "/estagiario/formulario";
		}
	}
}
