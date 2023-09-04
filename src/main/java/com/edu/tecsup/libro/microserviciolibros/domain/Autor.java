package com.edu.tecsup.libro.microserviciolibros.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "autor")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @JsonIgnore
    private String dni;
    @JsonIgnore
    @ManyToMany(mappedBy = "autores")
    private Set<Libro> libros;
    @PreRemove
    private void removeBookAssociations() {
        for (Libro book: this.libros) {
            book.getAutores().remove(this);
        }
    }
}
