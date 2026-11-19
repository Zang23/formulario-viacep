package br.edu.formulario.contato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.formulario.empresa.EmpresaRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/formulariocontato")
public class ContatoController {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping
    public String novoFormulario(Model model) {
        if (!model.containsAttribute("dadosFormulario")) {
            model.addAttribute("dadosFormulario", new DadosCadastroContato(null, null, null, null, null));
        }
        model.addAttribute("empresas", empresaRepository.findAll());
        return "formulariocontato";
    }

    @PostMapping("/salvar")
    public String salvarContato(
            @Valid @ModelAttribute("dadosFormulario") DadosCadastroContato dados,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("empresas", empresaRepository.findAll());
            return "formulariocontato";
        }

        try {
            var empresa = empresaRepository.findById(dados.empresaCnpj())
                    .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada"));

            Contato contato = new Contato(dados, empresa);
            contatoRepository.save(contato);

            redirectAttributes.addFlashAttribute("message", "Contato cadastrado com sucesso!");
            return "redirect:/formulariocontato";

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erro ao salvar contato: " + e.getMessage());
            model.addAttribute("empresas", empresaRepository.findAll());
            return "formulariocontato";
        }
    }
}
