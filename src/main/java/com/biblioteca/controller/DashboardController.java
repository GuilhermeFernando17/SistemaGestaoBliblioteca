package com.biblioteca.controller;

import com.biblioteca.service.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @Autowired private UsuarioService    usuarioService;
    @Autowired private MaterialService   materialService;
    @Autowired private EmprestimoService emprestimoService;
    @Autowired private ReservaService    reservaService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        emprestimoService.atualizarAtrasados();
        model.addAttribute("activePage", "dashboard");
        model.addAttribute("usuariosAtivos",    usuarioService.contarAtivos());
        model.addAttribute("totalMateriais",     materialService.contarTodos());
        model.addAttribute("emprestimosAtivos",  emprestimoService.contarAtivos());
        model.addAttribute("reservasPendentes",  reservaService.contarPendentes());
        model.addAttribute("emprestimosRecentes",emprestimoService.listarRecentes());
        return "dashboard";
    }
}
