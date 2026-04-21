package com.biblioteca.repository;

import com.biblioteca.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    long countByStatus(String status);
    Optional<Usuario> findByEmail(String email);
}
