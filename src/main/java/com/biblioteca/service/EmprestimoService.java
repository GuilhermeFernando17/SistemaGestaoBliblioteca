package com.biblioteca.service;

import com.biblioteca.model.Emprestimo;
import com.biblioteca.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {
    @Autowired private EmprestimoRepository repo;

    public List<Emprestimo> listarTodos() { return repo.findAll(); }
    public List<Emprestimo> listarPorStatus(String status) { return repo.findByStatus(status); }

    public List<Emprestimo> buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) return repo.findAll();
        String n = nome.toLowerCase();
        return repo.findAll().stream()
                .filter(e -> e.getUsuario().getNome().toLowerCase().contains(n))
                .collect(Collectors.toList());
    }

    public Optional<Emprestimo> buscarPorId(String id) { return repo.findById(id); }
    public Emprestimo salvar(Emprestimo e) { return repo.save(e); }
    public void deletar(String id) { repo.deleteById(id); }

    public long contarAtivos()     { return repo.countByStatus("Ativo"); }
    public long contarAtrasados()  { return repo.countByStatus("Atrasado"); }
    public long contarDevolvidos() { return repo.countByStatus("Devolvido"); }

    public List<Emprestimo> listarRecentes() { return repo.findTop10ByOrderByDataEmprestimoDesc(); }

    public void atualizarAtrasados() {
        LocalDate hoje = LocalDate.now();
        repo.findByStatus("Ativo").forEach(e -> {
            if (e.getDataDevolucao() != null && hoje.isAfter(e.getDataDevolucao())) {
                e.setStatus("Atrasado");
                repo.save(e);
            }
        });
    }
}
