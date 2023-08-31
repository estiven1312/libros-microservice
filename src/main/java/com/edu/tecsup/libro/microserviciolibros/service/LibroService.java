package com.edu.tecsup.libro.microserviciolibros.service;

import com.edu.tecsup.libro.microserviciolibros.domain.Libro;
import com.edu.tecsup.libro.microserviciolibros.domain.dto.LibroDTO;
import com.edu.tecsup.libro.microserviciolibros.util.ResponseMessage;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LibroService {
    ResponseEntity<ResponseMessage> createLibro(LibroDTO libroDTO);


    ResponseEntity<ResponseMessage> updateLibro(Long id, LibroDTO libroDTO);

    ResponseEntity<ResponseMessage> deleteLibro(Long idLibro);

    ResponseEntity<List<Libro>> findAll();

    ResponseEntity<List<Libro>> findAllByTitulo(String nombre);
}
