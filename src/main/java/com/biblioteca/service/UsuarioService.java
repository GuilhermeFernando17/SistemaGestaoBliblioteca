package com.biblioteca.service;

import com.biblioteca.model.Usuario;
import com.biblioteca.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired private UsuarioRepository repo;
    @Autowired private EmprestimoRepository empRepo;
    @Autowired private ReservaRepository resRepo;

    public List<Usuario> listarTodos() { return repo.findAll(); }

    public List<Usuario> buscar(String termo) {
        if (termo == null || termo.isBlank()) return repo.findAll();
        String t = termo.toLowerCase();
        return repo.findAll().stream()
                .filter(u -> u.getNome().toLowerCase().contains(t)
                          || u.getEmail().toLowerCase().contains(t))
                .collect(Collectors.toList());
    }

    public Optional<Usuario> buscarPorId(String id) { return repo.findById(id); }
    public Usuario salvar(Usuario u) { return repo.save(u); }
    public long contarAtivos() { return repo.countByStatus("Ativo"); }

    public void deletar(String id) {
        empRepo.deleteAll(empRepo.findByUsuario_Id(id));
        resRepo.deleteAll(resRepo.findByUsuario_Id(id));
        repo.deleteById(id);
    }
}
