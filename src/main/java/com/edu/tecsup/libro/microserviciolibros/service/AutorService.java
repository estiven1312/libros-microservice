package com.edu.tecsup.libro.microserviciolibros.service;

import com.edu.tecsup.libro.microserviciolibros.domain.Autor;
import com.edu.tecsup.libro.microserviciolibros.domain.dto.AutorDTO;
import com.edu.tecsup.libro.microserviciolibros.util.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AutorService {
    @Transactional(readOnly = true)
    ResponseEntity<List<Autor>> findAllAutores();

    @Transactional(readOnly = true)
    ResponseEntity<List<Autor>> findAllAutoresByFilter(String filtro);

    @Transactional
    ResponseEntity<ResponseMessage> createAutor(AutorDTO autorDTO);


    @Transactional
    ResponseEntity<ResponseMessage> updateAutor(Long id, AutorDTO autorDTO);

    @Transactional
    ResponseEntity<ResponseMessage> deleteAutor(Long id);
}
