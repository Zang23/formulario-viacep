package br.edu.formulario.contrato;

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
@RequestMapping("/contrato")
public class ContratoController {
    @Autowired 
    private ContratoService contratoService;

    @Autowired(required = false)
    private SupervisorService supervisorService;
    
    @GetMapping("/formulario")
    public String novoContrato(Model model) {
        model.addAttribute("dadosFormulario", new DadosCadastroContrato());
        
        if (supervisorService != null) {
            model.addAttribute("supervisores", supervisorService.listarSupervisores());
        }
        
        return "contrato/formulario";
    }

    @GetMapping("/formulario/{id}")
    public String carregarFormulario(@PathVariable("id") Long id,
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model) {
        try {
            Contrato contrato = contratoService.findById(id);
            model.addAttribute("dadosFormulario", contrato);
            
            if (supervisorService != null) {
                model.addAttribute("supervisores", supervisorService.listarSupervisores());
            }
            
            return "contrato/formulario";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao carregar o formulário: " + e.getMessage());
            return "redirect:/contrato";
        }
    }

    @GetMapping
    public String listarContratos(Model model, HttpSession session) {
        model.addAttribute("activePage", "contratos");
        model.addAttribute("contratos", contratoService.listarContratos());
        return "contrato/lista";
    }

    @PostMapping("/cadastrar")
    public String cadastrarContrato(@Valid @ModelAttribute("dadosFormulario") DadosCadastroContrato dados,
            BindingResult result,
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            if (supervisorService != null) {
                model.addAttribute("supervisores", supervisorService.listarSupervisores());
            }
            return "contrato/formulario";
        }

        try {
            contratoService.cadastrarContrato(dados);
            redirectAttributes.addFlashAttribute("message", "Contrato cadastrado com sucesso!");
            return "redirect:/contrato";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao salvar contrato: " + e.getMessage());
            return "redirect:/contrato/formulario";
        }
    }
}
