package com.edu.tecsup.libro.microserviciolibros.service.impl;

import com.edu.tecsup.libro.microserviciolibros.domain.Autor;
import com.edu.tecsup.libro.microserviciolibros.domain.dto.AutorDTO;
import com.edu.tecsup.libro.microserviciolibros.repository.AutorRepository;
import com.edu.tecsup.libro.microserviciolibros.service.AutorService;
import com.edu.tecsup.libro.microserviciolibros.util.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutorServiceImpl implements AutorService {
    private final AutorRepository autorRepository;

    public AutorServiceImpl(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Autor>> findAllAutores(){
        try{
            return new ResponseEntity<>(autorRepository.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Autor>> findAllAutoresByFilter(String filtro){
        try{
            return new ResponseEntity<>(autorRepository.findByFiltroNombre(filtro), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage> createAutor(AutorDTO autorDTO){
        try {
            Autor autor = new Autor();
            return setParametersToAutor(autorDTO, autor);
        } catch (Exception ex){
            ResponseMessage responseMessage = new ResponseMessage("Error al guardar", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
        }
    }
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage> updateAutor(Long id, AutorDTO autorDTO){
        try {
            Autor autor = autorRepository.findById(id).orElse(null);
            assert autor != null;
            return setParametersToAutor(autorDTO, autor);
        } catch (Exception ex){
            ResponseMessage responseMessage = new ResponseMessage("Error al guardar", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
        }
    }


    @Override
    @Transactional
    public ResponseEntity<ResponseMessage> deleteAutor(Long id){
        try {
            Autor autor = autorRepository.findById(id).orElse(null);
            autorRepository.delete(autor);
            StringBuilder sb = new StringBuilder();
            sb.append("Se ha eliminado un autor con el id: ").append(id);
            ResponseMessage responseMessage = new ResponseMessage(sb.toString(), HttpStatus.OK);
            return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
        } catch (Exception ex){
            ResponseMessage responseMessage = new ResponseMessage("Error al eliminar", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
        }
    }

    private ResponseEntity<ResponseMessage> setParametersToAutor(AutorDTO autorDTO, Autor autor) {
        autor.setDni(autorDTO.getDni());
        autor.setNombre(autorDTO.getNombre());
        autor.setApellidoMaterno(autorDTO.getApellidoMaterno());
        autor.setApellidoPaterno(autorDTO.getApellidoPaterno());
        autor = autorRepository.save(autor);
        StringBuilder sb = new StringBuilder();
        sb.append("Se ha creado un autor con el id: ").append(autor.getId());
        ResponseMessage responseMessage = new ResponseMessage(sb.toString(), HttpStatus.OK);
        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }

}
