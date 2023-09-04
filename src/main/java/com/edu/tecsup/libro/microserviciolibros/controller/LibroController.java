package com.edu.tecsup.libro.microserviciolibros.controller;

import com.edu.tecsup.libro.microserviciolibros.domain.Autor;
import com.edu.tecsup.libro.microserviciolibros.domain.Libro;
import com.edu.tecsup.libro.microserviciolibros.domain.dto.AutorDTO;
import com.edu.tecsup.libro.microserviciolibros.domain.dto.LibroDTO;
import com.edu.tecsup.libro.microserviciolibros.service.LibroService;
import com.edu.tecsup.libro.microserviciolibros.util.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/libro")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<Libro>> listar(){
        return libroService.findAll();
    }
    @GetMapping(value = "/findBy")
    public ResponseEntity<List<Libro>> listar(@RequestParam(value = "nombre") String nombre){
        return libroService.findAllByTitulo(nombre);
    }
    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseMessage> delete(@RequestParam(value = "id") Long id){
        return libroService.deleteLibro(id);
    }
    @PutMapping(value = "/update")
    public ResponseEntity<ResponseMessage> update(@RequestParam(value = "id") Long id, LibroDTO libroDTO){
        return libroService.updateLibro(id, libroDTO);
    }
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseMessage> update(LibroDTO libroDTO){
        return libroService.createLibro(libroDTO);
    }
}
