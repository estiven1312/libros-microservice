package com.edu.tecsup.libro.microserviciolibros.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="libro")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String titulo;
    private String isbn;
    private Long numeroHojas;
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private Set<Autor> autores;
    @PreRemove
    private void removeBookAssociations() {
        for (Autor autor: autores) {
            autor.getLibros().remove(this);
        }
    }

    //This method is to remove an author

    public void removeAuthor(Autor author){
        this.autores.remove(author);
        author.getLibros().remove(this);
    }
}
