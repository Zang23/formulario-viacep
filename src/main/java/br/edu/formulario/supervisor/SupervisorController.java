package br.edu.formulario.supervisor;

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
@RequestMapping("/supervisor")
public class SupervisorController {

    @Autowired
    private SupervisorService supervisorService;

    @GetMapping("/formulario/{nome}")
    public String carregarFormulario(@PathVariable("nome") String nome,
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model) {
        try {
            Supervisor supervisor = supervisorService.findByNome(nome);
            model.addAttribute("dados", supervisor);
            return "supervisor/formulario";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao carregar o formulário: " + e.getMessage());
            return "redirect:/supervisor";
        }
    }

    @GetMapping("/formulario")
    public String novoSupervisor(Model model) {
        model.addAttribute("dados", new Supervisor());
        return "supervisor/formulario";
    }

    @GetMapping
    public String listarSupervisor(Model model, HttpSession session) {
        model.addAttribute("activePage", "supervisor");
        model.addAttribute("supervisor", supervisorService.listarSupervisor());
        return "supervisor/lista";
    }

    @PostMapping("/salvar")
    public String salvarSupervisor(@Valid @ModelAttribute("dados") DadosCadastroSupervisor dados,
            BindingResult result,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "supervisor/formulario";
        }
        try {
            supervisorService.cadastrarSupervisor(dados);
            redirectAttributes.addFlashAttribute("message", "Supervisor salvo com sucesso!");
            return "redirect:/supervisor";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao salvar dados do supervisor: " + e.getMessage());
            return "redirect:/supervisor/formulario";
        }
    }
}