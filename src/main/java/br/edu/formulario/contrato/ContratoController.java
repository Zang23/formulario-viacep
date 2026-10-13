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

import jakarta.validation.Valid;
import br.edu.formulario.empresa.EmpresaRepository;

@Controller
@RequestMapping("/contrato")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @GetMapping
    public String novoFormulario(Model model) {
        if (!model.containsAttribute("dadosFormulario")) {
            model.addAttribute("dadosFormulario", new DadosCadastroContrato());
        }
        return "formulariocon";
    }

    @GetMapping("/{id}")
    public String carregarFormulario(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes,
            Model model) {

        try {
            Contrato contrato = contratoService.findById(id);
            model.addAttribute("dadosFormulario", contrato);
            return "formulariocon";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                "errorMessage", 
                "Erro ao carregar o formulário: " + e.getMessage()
            );
            return "redirect:/formulariocon";
        }
    }

    /*@GetMapping
    public String listarContratos(Model model) {

        model.addAttribute("activePage", "contratos");

        model.addAttribute(
            "contratos",
            contratoService.listarContratos()
        );

        return "contrato/lista";
    }*/

    @PostMapping("/salvar")
    public String salvarContrato(
            @Valid @ModelAttribute("dadosFormulario") DadosCadastroContrato dados,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            return "formulariocon";
        }

        try {
            contratoService.cadastrarContrato(dados);

            redirectAttributes.addFlashAttribute(
                "message",
                "Contrato cadastrado com sucesso!"
            );

            return "redirect:/formulariocon";

        } catch (Exception e) {
            model.addAttribute(
                "errorMessage",
                "Erro ao salvar contrato: " + e.getMessage()
            );
            return "formulariocon";
        }
    }
}
