package com.biblioteca.repository;

import com.biblioteca.model.Emprestimo;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface EmprestimoRepository extends MongoRepository<Emprestimo, String> {
    List<Emprestimo> findByStatus(String status);
    long countByStatus(String status);
    List<Emprestimo> findByUsuario_Id(String usuarioId);
    List<Emprestimo> findByMaterial_Id(String materialId);
    List<Emprestimo> findTop10ByOrderByDataEmprestimoDesc();
}
