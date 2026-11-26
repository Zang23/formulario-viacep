
package br.edu.formulario.empresa;

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
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/formulario")
    public String novaEmpresa(Model model) {
        model.addAttribute("dados", DadosCadastroEmpresa.vazio());
        model.addAttribute("editando", false);

        return "empresa/formulario";
    }

    @PostMapping("/formulario/salvar")
    public String salvarEmpresa(
            @Valid @ModelAttribute("dados") DadosCadastroEmpresa dados,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {

            if (result.hasErrors()) {

                result.getFieldErrors().forEach(erro -> {
                    System.err.println(
                        "Campo: " + erro.getField()
                        + " | Erro: " + erro.getDefaultMessage()
                    );
                });

                return "empresa/formulario";
            }

        try {
            empresaService.cadastrarEmpresa(dados);

            redirectAttributes.addFlashAttribute(
                    "message",
                    "Empresa salva com sucesso!");

            return "redirect:/empresa/dashboard";

        } catch (Exception e) {

            model.addAttribute(
                    "errorMessage",
                    "Erro ao salvar a empresa: " + e.getMessage());

            return "empresa/formulario";
        }
    }

    @GetMapping("/dashboard")
    public String listaEmpresa(
            @ModelAttribute("filtros") DadosFiltroEmpresa filtros,
            Model model) {

        List<Empresa> empresas = empresaService.buscarComFiltros(filtros);

        model.addAttribute("empresas", empresas);
        model.addAttribute("totalEmpresas", empresaService.listarEmpresas().size());

        return "empresa/lista";
    }

    @GetMapping("/visualizar/{cnpj}")
    public String visualizarEmpresa(
            @PathVariable("cnpj") String cnpj,
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model) {

        try {

            Empresa empresa = empresaService.findByCnpj(cnpj);

            DadosCadastroEmpresa dados = new DadosCadastroEmpresa(
                empresa.getCnpj(),
                empresa.getNome(),
                empresa.getRazaoSocial(),
                empresa.getSegmentoMercado(),
                empresa.getEmail(),
                empresa.getSite(),
                empresa.getTelefone(),
                empresa.getDepartamento(),
                empresa.getLinkedin(),
                empresa.getEndereco().getCep(),
                empresa.getEndereco().getLogradouro(),
                empresa.getEndereco().getBairro(),
                empresa.getEndereco().getNumPorta(),
                empresa.getEndereco().getUf(),
                empresa.getEndereco().getLocalidade(),
                empresa.getEndereco().getComplemento()
            );

            model.addAttribute("dados", dados);

            return "empresa/visualizar";

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Erro ao carregar o formulário: " + e.getMessage());

            return "redirect:/empresa/dashboard";
        }
    }


    @GetMapping("/editar/{cnpj}")
    public String carregarFormulario(
            @PathVariable("cnpj") String cnpj,
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model) {

        try {

            Empresa empresa = empresaService.findByCnpj(cnpj);

            DadosCadastroEmpresa dados = new DadosCadastroEmpresa(
                empresa.getCnpj(),
                empresa.getNome(),
                empresa.getRazaoSocial(),
                empresa.getSegmentoMercado(),
                empresa.getEmail(),
                empresa.getSite(),
                empresa.getTelefone(),
                empresa.getDepartamento(),
                empresa.getLinkedin(),
                empresa.getEndereco().getCep(),
                empresa.getEndereco().getLogradouro(),
                empresa.getEndereco().getBairro(),
                empresa.getEndereco().getNumPorta(),
                empresa.getEndereco().getUf(),
                empresa.getEndereco().getLocalidade(),
                empresa.getEndereco().getComplemento()
            );

            model.addAttribute("dados", dados);
            model.addAttribute("editando", true);

            return "empresa/formulario";

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Erro ao carregar o formulário: " + e.getMessage());

            return "redirect:/empresa/dashboard";
        }
    }

    @PostMapping("/atualizar")
    public String atualizarEmpresa(
            @Valid @ModelAttribute("dados") DadosAtualizacaoEmpresa dados,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("editando", true);
            return "empresa/formulario";
        }

        try {
            empresaService.atualizarEmpresa(dados);

            redirectAttributes.addFlashAttribute(
                    "message",
                    "Empresa atualizada com sucesso!");

            return "redirect:/empresa/dashboard";

        } catch (Exception e) {

            model.addAttribute("editando", true);
            model.addAttribute(
                    "errorMessage",
                    "Erro ao atualizar empresa: " + e.getMessage());

            return "empresa/formulario";
        }
    }

    @PostMapping("/excluir/{cnpj}")
    @Transactional
    public String deletarEmpresa(
            @PathVariable("cnpj") String cnpj,
            RedirectAttributes redirectAttributes) {

        try {

            empresaService.deletarEmpresa(cnpj);

            redirectAttributes.addFlashAttribute(
                    "message",
                    "Empresa com CNPJ " + cnpj + " excluída com sucesso!");

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Erro ao excluir empresa: " + e.getMessage());
        }

        return "redirect:/empresa/dashboard";
    }

}
