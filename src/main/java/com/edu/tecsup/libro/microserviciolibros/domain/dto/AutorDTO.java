package com.edu.tecsup.libro.microserviciolibros.domain.dto;

import com.edu.tecsup.libro.microserviciolibros.domain.Libro;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

public @Data class AutorDTO {
    private Long id;
    private String nombre;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String dni;

}
