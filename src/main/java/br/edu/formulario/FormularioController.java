package br.edu.formulario;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/formulario")
@RequiredArgsConstructor
public class FormularioController {

    private final FormularioService formularioService;


    @GetMapping
    public String formulario(Model model) {

        model.addAttribute(
            "form",
            new FormularioDTO()
        );

        return "formulario";
    }


    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute("form") FormularioDTO form) {
    	System.out.println("================================");
        System.out.println("CHEGOU NO CONTROLLER!");
        System.out.println("Estagiário: " + form.getNomeEstagiario());
        System.out.println("Empresa: " + form.getNomeEmpresa());
        System.out.println("================================");
        formularioService.salvar(form);

        return "redirect:/dashboard";
    }
}
