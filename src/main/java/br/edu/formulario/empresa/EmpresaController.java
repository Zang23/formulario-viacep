package br.edu.formulario.empresa;

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
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/formulario/{cnpj}")
    public String carregarFormulario(@PathVariable("cnpj") String cnpj,
        HttpSession session,
        RedirectAttributes redirectAttributes,
        Model model) {
        
        try{
            Empresa empresa = empresaService.findByCnpj(cnpj);
            model.addAttribute("empresa", empresa);
            return "empresa/formulario";
        }catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao carregar o formulário: " + e.getMessage());
            return "redirect:/empresa";
        }
    }

    @GetMapping("/formulario")
    public String novaEmpresa(Model model) {
        model.addAttribute("empresa", new Empresa());
        return "empresa/formulario";
    }

    @GetMapping 
    public String listarEmpresas(Model model, HttpSession session) {
        model.addAttribute("activePage", "empresas");
        model.addAttribute("empresas", empresaService.listarEmpresas());
        return "empresa/lista";
    }

    @PostMapping("/salvar")
    public String salvarEmpresa(@Valid @ModelAttribute("dados") DadosCadastroEmpresa dados,
            BindingResult result,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "empresa/formulario";
        }
        try {
            empresaService.cadastrarEmpresa(dados);
            redirectAttributes.addFlashAttribute("message", "Empresa salva com sucesso!");
            return "redirect:/empresa";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao salvar a empresa: " + e.getMessage());
            return "redirect:/empresa/formulario";
        }
    }   

    @PostMapping("/atualizar")
    public String atualizarEmpresa(@Valid @ModelAttribute("dados") DadosAtualizacaoEmpresa dados,
            BindingResult result,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "empresa/formulario";
        }
        try {
            empresaService.atualizarEmpresa(dados);
            redirectAttributes.addFlashAttribute("message", "Empresa atualizada com sucesso!");
            return "redirect:/empresa";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao atualizar empresa: " + e.getMessage());
            return "redirect:/empresa/formulario/" + dados.cnpj();
        }
    }

    @GetMapping("/delete/{cnpj}")
    @Transactional
    public String deletarEmpresa(@PathVariable("cnpj") String cnpj,
            Model model,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        try {
            empresaService.deletarEmpresa(cnpj);
            redirectAttributes.addFlashAttribute("message", "A empresa com CNPJ " + cnpj + " foi apagada!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/empresa";
    }
}
