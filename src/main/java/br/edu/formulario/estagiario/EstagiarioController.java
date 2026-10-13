package br.edu.formulario.estagiario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/formularioest")
public class EstagiarioController {

    @Autowired 
    private EstagiarioService estagiarioService;

    @GetMapping
    public String novoEstagiario(Model model) {
        model.addAttribute("dados", DadosCadastroEstagiario.vazio());
        return "formularioest";
    }

    /*@GetMapping("/{ra}")
    public String carregarFormulario(@PathVariable("ra") Long ra,
                HttpSession session,
                RedirectAttributes redirectAttributes,
                Model model) {
        try {
            Estagiario estagiario = estagiarioService.findByRa(ra);
            model.addAttribute("dados", estagiario); 
            return "formularioest/formulario";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao carregar o formulário: " + e.getMessage());
            return "formularioest/formulario";
        }
    }

    @GetMapping
    public String listarEstagiarios(Model model, HttpSession session) {
        model.addAttribute("activePage", "estagiarios");
        model.addAttribute("estagiarios", estagiarioService.listarEstagiarios());
        return "formularioest/lista";
    }*/

    @PostMapping("/salvar")
    public String salvarEstagiario(@Valid @ModelAttribute("dados") DadosCadastroEstagiario dados,
                                   BindingResult result,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "formularioest";
        }
        try {
            estagiarioService.cadastrarEstagiario(dados);
            redirectAttributes.addFlashAttribute("message", "Estagiário salvo com sucesso!");
            return "redirect:/formularioest";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao salvar dados do estagiário: " + e.getMessage());
            return "formularioest";
        }
    }   
}