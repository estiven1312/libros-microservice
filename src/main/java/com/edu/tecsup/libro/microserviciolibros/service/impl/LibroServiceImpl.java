package com.edu.tecsup.libro.microserviciolibros.service.impl;

import com.edu.tecsup.libro.microserviciolibros.domain.Libro;
import com.edu.tecsup.libro.microserviciolibros.domain.dto.LibroDTO;
import com.edu.tecsup.libro.microserviciolibros.repository.AutorRepository;
import com.edu.tecsup.libro.microserviciolibros.repository.CategoriaRepository;
import com.edu.tecsup.libro.microserviciolibros.repository.LibroRepository;
import com.edu.tecsup.libro.microserviciolibros.service.LibroService;
import com.edu.tecsup.libro.microserviciolibros.util.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class LibroServiceImpl implements LibroService {
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;


    public LibroServiceImpl(LibroRepository libroRepository, AutorRepository autorRepository, CategoriaRepository categoriaRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public ResponseEntity<ResponseMessage> createLibro(LibroDTO libroDTO){
        try {
            Libro libro = new Libro();
            return setParamsToLibro(libroDTO, libro);
        } catch (Exception ex){
            StringBuilder sb = new StringBuilder();
            sb.append("Error al crear");
            ResponseMessage responseMessage = new ResponseMessage(sb.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
        }
    }
    @Override
    public ResponseEntity<ResponseMessage> updateLibro(Long id, LibroDTO libroDTO){
        try {
            Libro libro = libroRepository.findById(id).orElse(null);
            return setParamsToLibro(libroDTO, libro);
        } catch (Exception ex){
            StringBuilder sb = new StringBuilder();
            sb.append("Error al crear");
            ResponseMessage responseMessage = new ResponseMessage(sb.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
        }
    }
    @Override
    public ResponseEntity<ResponseMessage> deleteLibro(Long idLibro){
        try {
            Libro libro = libroRepository.findById(idLibro).orElse(null);
            assert libro != null;
            libroRepository.delete(libro);
            StringBuilder sb = new StringBuilder();
            sb.append("Eliminado libro con Id:");
            ResponseMessage responseMessage = new ResponseMessage(sb.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
        } catch (Exception ex){
            StringBuilder sb = new StringBuilder();
            sb.append("Error al eliminar");
            ResponseMessage responseMessage = new ResponseMessage(sb.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
        }
    }
    @Override
    public ResponseEntity<List<Libro>> findAll(){
        try {
            return new ResponseEntity<>(libroRepository.findAll(), HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity<List<Libro>> findAllByTitulo(String nombre){
        try {
            return new ResponseEntity<>(libroRepository.findByNombre(nombre), HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private ResponseEntity<ResponseMessage> setParamsToLibro(LibroDTO libroDTO, Libro libro) {
        libro.setIsbn(libroDTO.getIsbn());
        libro.setTitulo(libroDTO.getTitulo());
        libro.setNumeroHojas(libroDTO.getNumeroHojas());
        libro.setCategoria(categoriaRepository.findById(libroDTO.getId()).orElse(null));
        libro.setAutores(autorRepository.findAutoresByIds(libroDTO.getAutoresId()));
        libro = libroRepository.save(libro);
        StringBuilder sb = new StringBuilder();
        sb.append("Se ha creado un autor con el id: ").append(libro.getId());
        ResponseMessage responseMessage = new ResponseMessage(sb.toString(), HttpStatus.OK);
        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }
}
