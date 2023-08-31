package com.edu.tecsup.libro.microserviciolibros.domain.dto;

import com.edu.tecsup.libro.microserviciolibros.domain.Autor;
import com.edu.tecsup.libro.microserviciolibros.domain.Categoria;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Set;

public @Data class LibroDTO {
    private Long id;
    private String titulo;
    private String isbn;
    private Long numeroHojas;
    private Long categoriaId;
    private Set<Long> autoresId;
}
