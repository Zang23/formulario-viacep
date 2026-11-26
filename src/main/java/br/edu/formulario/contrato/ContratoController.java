
package br.edu.formulario.contrato;

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
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/contrato")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    // Novo contrato
    @GetMapping("/formulario")
    public String novoFormulario(Model model) {
        model.addAttribute("dados", DadosCadastroContrato.vazio());
        return "contrato/formulario";
    }

    // Listagem de contratos e filtros

    @GetMapping({"", "/", "/dashboard"})
    public String listaContrato(
            @ModelAttribute("filtros") DadosFiltroContrato filtros,
            Model model) {

        List<Contrato> contratos =
            contratoService.buscarComFiltros(filtros);

        model.addAttribute("contratos", contratos);
        model.addAttribute(
            "totalContratos",
            contratoService.contarContratos()
        );

        return "contrato/lista";
    }


    @GetMapping("/visualizar/{id}")
    public String visualizarContrato(
            @PathVariable("id") Long id,
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model) {

        try {

            Contrato contrato = contratoService.findById(id);

            model.addAttribute("dados", contrato);

            return "contrato/visualizar";

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                "errorMessage",
                "Erro ao carregar o contrato: " + e.getMessage()
            );

            return "redirect:/contrato";
        }
    }

    // Carregar contrato para edição
    @GetMapping("/editar/{id}")
    public String carregarFormulario(
            @PathVariable("id") Long id,
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model) {

        try {

            Contrato contrato = contratoService.findById(id);

            DadosCadastroContrato dadosContrato = new DadosCadastroContrato(
            	    contrato.getIdContrato(),
            	    contrato.getEmpresa().getCnpj(),
            	    contrato.getCpfSupervisor(),
            	    contrato.getEstagiario().getRa(),
            	    contrato.getDataInicio(),
            	    contrato.getDataTermino(),
            	    contrato.getCargaHoraria(),
            	    contrato.getArea(),
            	    contrato.getFuncao(),
            	    contrato.isBancoHoras(),
            	    contrato.getDescricao(),
            	    contrato.isEstagioArea()
            	);
            model.addAttribute("dados", dadosContrato);

            return "contrato/formulario";

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                "errorMessage",
                "Erro ao carregar o contrato: " + e.getMessage()
            );

            return "redirect:/contrato";
        }
    }

    // Salvar novo contrato
    @PostMapping("/formulario/salvar")
    public String salvarContrato(
            @Valid @ModelAttribute("dados") DadosCadastroContrato dados,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute(
                "errorMessage",
                "Preencha todos os campos obrigatórios corretamente!"
            );

            return "contrato/formulario";
        }

        try {

            contratoService.cadastrarContrato(dados);

            redirectAttributes.addFlashAttribute(
                "message",
                "Contrato cadastrado com sucesso!"
            );

            return "redirect:/contrato";

        } catch (Exception e) {

            model.addAttribute(
                "errorMessage",
                "Erro ao salvar contrato: " + e.getMessage()
            );

            return "contrato/formulario";
        }
    }

    // Atualizar contrato existente
    @PostMapping("/atualizar")
    public String atualizarContrato(
            @Valid @ModelAttribute DadosAtualizacaoContrato dados,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {

            redirectAttributes.addFlashAttribute(
                "errorMessage",
                "Verifique os campos preenchidos."
            );

            return "redirect:/contrato/editar/" + dados.idContrato();
        }

        try {

            contratoService.atualizarContrato(dados);

            redirectAttributes.addFlashAttribute(
                "message",
                "Contrato atualizado com sucesso!"
            );

            return "redirect:/contrato";

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                "errorMessage",
                "Erro ao atualizar contrato: " + e.getMessage()
            );

            return "redirect:/contrato/editar/" + dados.idContrato();
        }
    }

    // Excluir contrato
    @PostMapping("/excluir/{id}")

    @Transactional
    public String deletarContrato(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes) {

        try {

            contratoService.deletarContrato(id);

            redirectAttributes.addFlashAttribute(
                "message",
                "Contrato excluído com sucesso!"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                "errorMessage",
                "Erro ao excluir contrato: " + e.getMessage()
            );
        }

        return "redirect:/contrato";
    }


}
