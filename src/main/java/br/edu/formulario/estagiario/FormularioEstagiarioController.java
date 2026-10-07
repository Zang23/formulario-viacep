package br.edu.formulario.estagiario;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/formularioest")
@RequiredArgsConstructor
public class FormularioEstagiarioController {



    @GetMapping
    public String formulario(Model model) {

        model.addAttribute(
            "form",
            new Estagiario()
        );

        return "formularioest";
    }


   // @PostMapping("/salvar")
   // public String salvar(@ModelAttribute("form") FormularioDTO form) {


  //      return "redirect:/dashboard";
   // }
}
