package com.biblioteca.repository;

import com.biblioteca.model.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ReservaRepository extends MongoRepository<Reserva, String> {
    long countByStatus(String status);
    List<Reserva> findByUsuario_Id(String usuarioId);
    List<Reserva> findByMaterial_Id(String materialId);
}
