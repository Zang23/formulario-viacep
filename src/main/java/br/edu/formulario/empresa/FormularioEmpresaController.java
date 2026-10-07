package br.edu.formulario.empresa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/formularioemp")
@RequiredArgsConstructor
public class FormularioEmpresaController {



    @GetMapping
    public String formulario(Model model) {

        model.addAttribute(
            "form",
            new Empresa()
        );

        return "formularioemp";
    }


 //   @PostMapping("/salvar")
 //   public String salvar(@ModelAttribute("form") FormularioDTO form) {

 //       return "redirect:/dashboard";
 //   }
}
