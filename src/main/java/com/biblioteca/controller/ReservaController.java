package com.biblioteca.controller;

import com.biblioteca.model.Reserva;
import com.biblioteca.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;

@Controller
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired private ReservaService  service;
    @Autowired private UsuarioService  usuarioService;
    @Autowired private MaterialService materialService;

    @GetMapping
    public String listar(@RequestParam(required = false) String busca, Model model) {
        model.addAttribute("activePage", "reservas");
        model.addAttribute("reservas",  busca != null && !busca.isBlank()
                ? service.buscarPorNome(busca) : service.listarTodas());
        model.addAttribute("busca",     busca != null ? busca : "");
        model.addAttribute("usuarios",  usuarioService.listarTodos());
        model.addAttribute("materiais", materialService.listarTodos());
        return "reservas";
    }

    @PostMapping
    public String criar(@RequestParam String usuarioId, @RequestParam String materialId,
                        @RequestParam String dataReserva, @RequestParam String dataEmprestimoPrevisto,
                        RedirectAttributes ra) {
        Reserva r = new Reserva();
        r.setUsuario(usuarioService.buscarPorId(usuarioId).orElseThrow());
        r.setMaterial(materialService.buscarPorId(materialId).orElseThrow());
        r.setDataReserva(LocalDate.parse(dataReserva));
        r.setDataEmprestimoPrevisto(LocalDate.parse(dataEmprestimoPrevisto));
        r.setStatus("Pendente");
        service.salvar(r);
        ra.addFlashAttribute("sucesso", "Reserva criada com sucesso!");
        return "redirect:/reservas";
    }

    @PostMapping("/{id}/editar")
    public String editar(@PathVariable String id,
                         @RequestParam String usuarioId, @RequestParam String materialId,
                         @RequestParam String dataReserva, @RequestParam String dataEmprestimoPrevisto,
                         @RequestParam String status, RedirectAttributes ra) {
        Reserva r = service.buscarPorId(id).orElseThrow();
        r.setUsuario(usuarioService.buscarPorId(usuarioId).orElseThrow());
        r.setMaterial(materialService.buscarPorId(materialId).orElseThrow());
        r.setDataReserva(LocalDate.parse(dataReserva));
        r.setDataEmprestimoPrevisto(LocalDate.parse(dataEmprestimoPrevisto));
        r.setStatus(status);
        service.salvar(r);
        ra.addFlashAttribute("sucesso", "Reserva atualizada com sucesso!");
        return "redirect:/reservas";
    }

    @PostMapping("/{id}/deletar")
    public String deletar(@PathVariable String id, RedirectAttributes ra) {
        service.deletar(id);
        ra.addFlashAttribute("sucesso", "Reserva removida com sucesso!");
        return "redirect:/reservas";
    }
}
