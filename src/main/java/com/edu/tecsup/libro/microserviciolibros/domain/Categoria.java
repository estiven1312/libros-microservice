package com.edu.tecsup.libro.microserviciolibros.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categoria")
public @Data class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private Set<Libro> libros;
}
