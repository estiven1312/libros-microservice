package com.edu.tecsup.libro.microserviciolibros.controller;

import com.edu.tecsup.libro.microserviciolibros.domain.Autor;
import com.edu.tecsup.libro.microserviciolibros.domain.dto.AutorDTO;
import com.edu.tecsup.libro.microserviciolibros.service.AutorService;
import com.edu.tecsup.libro.microserviciolibros.util.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/author")
public class AutorController {
    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }
    @GetMapping(value = "/findAll")
    public ResponseEntity<List<Autor>> listar(){
        return autorService.findAllAutores();
    }
    @GetMapping(value = "/findBy")
    public ResponseEntity<List<Autor>> listar(@RequestParam(value = "nombre") String nombre){
        return autorService.findAllAutoresByFilter(nombre);
    }
    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseMessage> delete(@RequestParam(value = "id") Long id){
        return autorService.deleteAutor(id);
    }
    @PutMapping(value = "/update")
    public ResponseEntity<ResponseMessage> update(@RequestParam(value = "id") Long id, AutorDTO autorDTO){
        return autorService.updateAutor(id, autorDTO);
    }
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseMessage> update(AutorDTO autorDTO){
        return autorService.createAutor(autorDTO);
    }
}
