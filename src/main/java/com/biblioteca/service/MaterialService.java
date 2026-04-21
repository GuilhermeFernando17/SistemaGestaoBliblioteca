package com.biblioteca.service;

import com.biblioteca.model.Material;
import com.biblioteca.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MaterialService {
    @Autowired private MaterialRepository repo;
    @Autowired private EmprestimoRepository empRepo;
    @Autowired private ReservaRepository resRepo;

    public List<Material> listarTodos() { return repo.findAll(); }

    public List<Material> buscar(String termo) {
        if (termo == null || termo.isBlank()) return repo.findAll();
        String t = termo.toLowerCase();
        return repo.findAll().stream()
                .filter(m -> m.getTitulo().toLowerCase().contains(t)
                          || m.getAutor().toLowerCase().contains(t))
                .collect(Collectors.toList());
    }

    public Optional<Material> buscarPorId(String id) { return repo.findById(id); }
    public Material salvar(Material m) { return repo.save(m); }
    public long contarTodos() { return repo.count(); }

    public void deletar(String id) {
        empRepo.deleteAll(empRepo.findByMaterial_Id(id));
        resRepo.deleteAll(resRepo.findByMaterial_Id(id));
        repo.deleteById(id);
    }
}
