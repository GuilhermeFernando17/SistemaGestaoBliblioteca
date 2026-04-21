package com.biblioteca.service;

import com.biblioteca.model.Reserva;
import com.biblioteca.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservaService {
    @Autowired private ReservaRepository repo;

    public List<Reserva> listarTodas() { return repo.findAll(); }

    public List<Reserva> buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) return repo.findAll();
        String n = nome.toLowerCase();
        return repo.findAll().stream()
                .filter(r -> r.getUsuario().getNome().toLowerCase().contains(n))
                .collect(Collectors.toList());
    }

    public Optional<Reserva> buscarPorId(String id) { return repo.findById(id); }
    public Reserva salvar(Reserva r) { return repo.save(r); }
    public void deletar(String id) { repo.deleteById(id); }
    public long contarPendentes() { return repo.countByStatus("Pendente"); }
}
