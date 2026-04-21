package com.biblioteca.controller;

import com.biblioteca.model.Emprestimo;
import com.biblioteca.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {
    @Autowired private EmprestimoService service;
    @Autowired private UsuarioService    usuarioService;
    @Autowired private MaterialService   materialService;

    @GetMapping
    public String listar(@RequestParam(required = false) String busca,
                         @RequestParam(required = false, defaultValue = "Ativo") String status,
                         Model model) {
        service.atualizarAtrasados();
        model.addAttribute("activePage", "emprestimos");

        List<Emprestimo> lista;
        if (busca != null && !busca.isBlank()) {
            lista = service.buscarPorNome(busca);
        } else {
            lista = service.listarPorStatus(status);
        }

        model.addAttribute("emprestimos",     lista);
        model.addAttribute("busca",           busca != null ? busca : "");
        model.addAttribute("statusAtual",     status);
        model.addAttribute("totalAtivos",     service.contarAtivos());
        model.addAttribute("totalAtrasados",  service.contarAtrasados());
        model.addAttribute("totalDevolvidos", service.contarDevolvidos());
        model.addAttribute("usuarios",       usuarioService.listarTodos());
        model.addAttribute("materiais",      materialService.listarTodos());
        return "emprestimos";
    }

    @PostMapping
    public String criar(@RequestParam String usuarioId, @RequestParam String materialId,
                        @RequestParam String dataEmprestimo, @RequestParam String dataDevolucao,
                        RedirectAttributes ra) {
        Emprestimo e = new Emprestimo();
        e.setUsuario(usuarioService.buscarPorId(usuarioId).orElseThrow());
        e.setMaterial(materialService.buscarPorId(materialId).orElseThrow());
        e.setDataEmprestimo(LocalDate.parse(dataEmprestimo));
        e.setDataDevolucao(LocalDate.parse(dataDevolucao));
        e.setStatus("Ativo");
        service.salvar(e);
        ra.addFlashAttribute("sucesso", "Emprestimo criado com sucesso!");
        return "redirect:/emprestimos";
    }

    @PostMapping("/{id}/editar")
    public String editar(@PathVariable String id,
                         @RequestParam String usuarioId, @RequestParam String materialId,
                         @RequestParam String dataEmprestimo, @RequestParam String dataDevolucao,
                         @RequestParam String status, RedirectAttributes ra) {
        Emprestimo e = service.buscarPorId(id).orElseThrow();
        e.setUsuario(usuarioService.buscarPorId(usuarioId).orElseThrow());
        e.setMaterial(materialService.buscarPorId(materialId).orElseThrow());
        e.setDataEmprestimo(LocalDate.parse(dataEmprestimo));
        e.setDataDevolucao(LocalDate.parse(dataDevolucao));
        e.setStatus(status);
        service.salvar(e);
        ra.addFlashAttribute("sucesso", "Emprestimo atualizado com sucesso!");
        return "redirect:/emprestimos";
    }

    @PostMapping("/{id}/deletar")
    public String deletar(@PathVariable String id, RedirectAttributes ra) {
        service.deletar(id);
        ra.addFlashAttribute("sucesso", "Emprestimo removido com sucesso!");
        return "redirect:/emprestimos";
    }
}
