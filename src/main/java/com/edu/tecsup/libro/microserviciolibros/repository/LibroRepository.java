package com.edu.tecsup.libro.microserviciolibros.repository;

import com.edu.tecsup.libro.microserviciolibros.domain.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l JOIN l.autores a WHERE a.id = :idAutor")
    List<Libro> findLibrosByIdAutor(@Param("idAutor") Long idAutor);

    @Query("SELECT l FROM Libro l WHERE l.categoria.id = : idCategoria")
    List<Libro> findLibrosByIdCategoria(@Param("idCategoria") Long idCategoria);
    @Query("SELECT l FROM Libro l WHERE l.titulo LIKE %:nombre%")
    List<Libro> findByNombre(@Param("nombre") String nombre);

}
