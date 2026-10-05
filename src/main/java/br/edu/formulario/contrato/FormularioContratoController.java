package br.edu.formulario.contrato;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.formulario.FormularioDTO;
import br.edu.formulario.FormularioService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/formulariocon")
@RequiredArgsConstructor
public class FormularioContratoController {



    @GetMapping
    public String formulario(Model model) {

        model.addAttribute(
            "form",
            new Contrato()
        );

        return "formulariocon";
    }


    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("form") FormularioDTO form) {


        return "redirect:/dashboard";
    }
}
