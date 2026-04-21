package com.biblioteca.controller;

import com.biblioteca.model.Material;
import com.biblioteca.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/materiais")
public class MaterialController {
    @Autowired private MaterialService service;

    @GetMapping
    public String listar(@RequestParam(required = false) String busca, Model model) {
        model.addAttribute("activePage", "materiais");
        model.addAttribute("materiais", service.buscar(busca));
        model.addAttribute("busca", busca != null ? busca : "");
        return "materiais";
    }

    @PostMapping
    public String criar(@ModelAttribute Material material, RedirectAttributes ra) {
        service.salvar(material);
        ra.addFlashAttribute("sucesso", "Material criado com sucesso!");
        return "redirect:/materiais";
    }

    @PostMapping("/{id}/editar")
    public String editar(@PathVariable String id, @ModelAttribute Material material, RedirectAttributes ra) {
        material.setId(id);
        service.salvar(material);
        ra.addFlashAttribute("sucesso", "Material atualizado com sucesso!");
        return "redirect:/materiais";
    }

    @PostMapping("/{id}/deletar")
    public String deletar(@PathVariable String id, RedirectAttributes ra) {
        service.deletar(id);
        ra.addFlashAttribute("sucesso", "Material removido com sucesso!");
        return "redirect:/materiais";
    }
}
