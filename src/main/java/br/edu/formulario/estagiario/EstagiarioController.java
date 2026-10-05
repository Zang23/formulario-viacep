package br.edu.formulario.estagiario;

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

    @GetMapping("/formulario/{cpf}")
    public String carregarFormulario(@PathVariable("cpf") String cpf,
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model) {
        try {
            Estagiario estagiario = estagiarioService.findByCpf(cpf);
            model.addAttribute("dados", estagiario); 
            return "estagiario/formulario";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao carregar o formulário: " + e.getMessage());
            return "redirect:/estagiario";
        }
    }

    @GetMapping("/formulario")
    public String novoEstagiario(Model model) {
        model.addAttribute("dados", new Estagiario());
        return "estagiario/formulario";
    }

    @GetMapping
    public String listarEstagiarios(Model model, HttpSession session) {
        model.addAttribute("activePage", "estagiarios");
        model.addAttribute("estagiarios", estagiarioService.listarEstagiarios());
        return "estagiario/lista";
    }

    @PostMapping("/salvar")
    public String salvarEstagiario(@Valid @ModelAttribute("dados") DadosCadastroEstagiario dados,
            BindingResult result,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "estagiario/formulario";
        }
        try {
            estagiarioService.cadastrarEstagiario(dados);
            redirectAttributes.addFlashAttribute("message", "Estagiário salvo com sucesso!");
            return "redirect:/estagiario";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao salvar dados do estagiário: " + e.getMessage());
            return "redirect:/estagiario/formulario";
        }
    }   
}