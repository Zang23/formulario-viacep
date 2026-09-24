package br.edu.formulario;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public String exibirDashboard(Model model) {

        model.addAttribute(
            "estagiarios",
            dashboardService.listarEstagiarios()
        );

        return "dashboard";
    }
}

