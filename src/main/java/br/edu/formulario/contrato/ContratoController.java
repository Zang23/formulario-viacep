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
import br.edu.formulario.contrato.DadosCadastroContrato;

@Controller
@RequestMapping("/contrato/formulario")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @GetMapping
    public String novoFormulario(Model model) {
        model.addAttribute("dados", DadosCadastroContrato.vazio());
        return "contrato/formulario";
    }

    @GetMapping("/{id}")
    public String carregarFormulario(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes,
            Model model) {

        try {
            Contrato contrato = contratoService.findById(id);

            // Temporariamente, para exibir o objeto diretamente:
            model.addAttribute("dados", contrato);

            return "contrato/formulario";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                "errorMessage",
                "Erro ao carregar o formulário: " + e.getMessage()
            );
            return "redirect:/contrato/formulario";
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
            @Valid @ModelAttribute("dados") DadosCadastroContrato dados,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {

        System.out.println("=== RECEBI O POST /salvar ===");
        System.out.println("Dados recebidos: " + dados);

        if (result.hasErrors()) {
            System.out.println("=== ERROS DE VALIDAÇÃO ===");

            result.getFieldErrors().forEach(erro ->
                System.out.println(
                    "Campo: " + erro.getField()
                    + " | Erro: " + erro.getDefaultMessage()
                    + " | Valor rejeitado: " + erro.getRejectedValue()
                )
            );

            return "contrato/formulario";
        }

        try {
            System.out.println("=== PASSEI PELA VALIDAÇÃO ===");

            contratoService.cadastrarContrato(dados);

            System.out.println("=== CONTRATO CADASTRADO ===");

            redirectAttributes.addFlashAttribute(
                "message",
                "Contrato cadastrado com sucesso!"
            );

            return "redirect:/contrato/formulario";

        } catch (Exception e) {
            e.printStackTrace();

            model.addAttribute(
                "errorMessage",
                "Erro ao salvar contrato: " + e.getMessage()
            );

            return "contrato/formulario";
        }
    }

}
