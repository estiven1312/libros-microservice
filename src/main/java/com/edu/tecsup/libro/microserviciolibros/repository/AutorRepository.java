package com.edu.tecsup.libro.microserviciolibros.repository;

import com.edu.tecsup.libro.microserviciolibros.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a JOIN a.libros l WHERE l.id = :idLibro")
    Set<Autor> findByIdLibro(@Param("idLibro") Long idLibro);
    @Query("SELECT a FROM Autor a WHERE a.nombre LIKE %:nombre% OR  a.apellidoMaterno LIKE %:nombre% OR  a.apellidoPaterno LIKE %:nombre% ")
    List<Autor> findByFiltroNombre(@Param("nombre") String nombre);
    @Query("SELECT a FROM Autor a WHERE a.id IN :idAutores")
    Set<Autor> findAutoresByIds(@Param("idAutores") Set<Long> idAutores);
}
