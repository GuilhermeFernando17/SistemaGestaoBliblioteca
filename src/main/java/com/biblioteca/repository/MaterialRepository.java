package com.biblioteca.repository;

import com.biblioteca.model.Material;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface MaterialRepository extends MongoRepository<Material, String> {
    Optional<Material> findByIsbn(String isbn);
}
