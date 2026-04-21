package com.biblioteca.controller;

import com.biblioteca.model.Usuario;
import com.biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired private UsuarioService service;

    @GetMapping
    public String listar(@RequestParam(required = false) String busca, Model model) {
        model.addAttribute("activePage", "usuarios");
        model.addAttribute("usuarios", service.buscar(busca));
        model.addAttribute("busca", busca != null ? busca : "");
        return "usuarios";
    }

    @PostMapping
    public String criar(@ModelAttribute Usuario usuario, RedirectAttributes ra) {
        service.salvar(usuario);
        ra.addFlashAttribute("sucesso", "Usuario criado com sucesso!");
        return "redirect:/usuarios";
    }

    @PostMapping("/{id}/editar")
    public String editar(@PathVariable String id, @ModelAttribute Usuario usuario, RedirectAttributes ra) {
        usuario.setId(id);
        service.salvar(usuario);
        ra.addFlashAttribute("sucesso", "Usuario atualizado com sucesso!");
        return "redirect:/usuarios";
    }

    @PostMapping("/{id}/deletar")
    public String deletar(@PathVariable String id, RedirectAttributes ra) {
        service.deletar(id);
        ra.addFlashAttribute("sucesso", "Usuario removido com sucesso!");
        return "redirect:/usuarios";
    }
}
