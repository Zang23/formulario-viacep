package br.edu.formulario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormularioController {
	 // Página pública (opcional)
    @GetMapping("/formulario")
    public String exibirFormulario() {
        return "formulario"; 
    }

}
