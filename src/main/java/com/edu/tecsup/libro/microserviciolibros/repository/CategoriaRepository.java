package com.edu.tecsup.libro.microserviciolibros.repository;

import com.edu.tecsup.libro.microserviciolibros.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
