package com.alura.literalura.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.alura.literalura.libro.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{


    @Query("SELECT l.titulo FROM Libro l WHERE l.autor.nombre = :autorNombre")
    List<String> buscarLibroPorAutor(String autorNombre);
    
    Boolean existsByTitulo(String titulo);

    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> buscarLibroPorIdioma(String idioma);
   

}
