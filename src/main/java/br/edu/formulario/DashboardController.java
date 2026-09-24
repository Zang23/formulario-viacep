package br.edu.formulario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
	 // Página pública (opcional)
    @GetMapping("/dashboard")
    public String exibirDashboard() {
        return "dashboard"; 
    }

}
