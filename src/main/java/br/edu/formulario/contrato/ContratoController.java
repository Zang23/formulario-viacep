
package br.edu.formulario.contrato;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            @RequestParam(required = false) String empresa,
            @RequestParam(required = false) String estagiario,
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String funcao,
            @RequestParam(required = false) Integer cargaHoraria,
            @RequestParam(required = false) Boolean bancoHoras,
            @RequestParam(required = false) Boolean estagioArea,
            @RequestParam(required = false) String cpfSupervisor,
            Model model) {

        List<Contrato> todosContratos = contratoService.listarContratos();

        Stream<Contrato> resultado = todosContratos.stream();

        if (empresa != null && !empresa.isBlank()) {
            String filtro = empresa.toLowerCase(Locale.ROOT);

            resultado = resultado.filter(c ->
                c.getEmpresa() != null &&
                c.getEmpresa().getNome() != null &&
                c.getEmpresa().getNome().toLowerCase(Locale.ROOT).contains(filtro)
            );
        }

        if (estagiario != null && !estagiario.isBlank()) {
            String filtro = estagiario.toLowerCase(Locale.ROOT);

            resultado = resultado.filter(c ->
                c.getEstagiario() != null &&
                c.getEstagiario().getNome() != null &&
                c.getEstagiario().getNome().toLowerCase(Locale.ROOT).contains(filtro)
            );
        }

        if (area != null && !area.isBlank()) {
            String filtro = area.toLowerCase(Locale.ROOT);

            resultado = resultado.filter(c ->
                c.getArea() != null &&
                c.getArea().toLowerCase(Locale.ROOT).contains(filtro)
            );
        }

        if (funcao != null && !funcao.isBlank()) {
            String filtro = funcao.toLowerCase(Locale.ROOT);

            resultado = resultado.filter(c ->
                c.getFuncao() != null &&
                c.getFuncao().toLowerCase(Locale.ROOT).contains(filtro)
            );
        }

        if (cargaHoraria != null) {
            resultado = resultado.filter(c ->
                c.getCargaHoraria() == cargaHoraria
            );
        }

        if (bancoHoras != null) {
            resultado = resultado.filter(c ->
                c.isBancoHoras() == bancoHoras
            );
        }

        if (estagioArea != null) {
            resultado = resultado.filter(c ->
                c.isEstagioArea() == estagioArea
            );
        }

        if (cpfSupervisor != null && !cpfSupervisor.isBlank()) {
            String filtro = cpfSupervisor.replaceAll("\\D", "");

            resultado = resultado.filter(c ->
                c.getCpfSupervisor() != null &&
                c.getCpfSupervisor().replaceAll("\\D", "").contains(filtro)
            );
        }

        List<Contrato> contratos = resultado.toList();

        model.addAttribute("contratos", contratos);
        model.addAttribute("totalContratos", todosContratos.size());

        return "contrato/lista";
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

            model.addAttribute("dados", contrato);

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
    @PostMapping("/salvar")
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

    // Visualizar contrato
    @GetMapping("/visualizar/{id}")
    public String visualizarContrato(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes,
            Model model) {

        try {

            Contrato contrato = contratoService.findById(id);

            model.addAttribute("contrato", contrato);

            return "contrato/visualizar";

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                "errorMessage",
                "Erro ao carregar contrato: " + e.getMessage()
            );

            return "redirect:/contrato";
        }
    }
}
