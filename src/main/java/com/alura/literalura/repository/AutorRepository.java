package com.alura.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alura.literalura.libro.Autor;
import com.alura.literalura.libro.DatosAutor;


public interface AutorRepository extends JpaRepository<Autor , Long> {
 

    // @Query("SELECT DISTINCT a from Autor a")
    // //@Query("SELECT a FROM Autor a")
    // List<DatosAutor> findAutores();


    @Query("SELECT DISTINCT a.nombre FROM Autor a")
    List<String> obtenerNombresDeAutores();

    @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
    List<Autor> buscarAutorPorNombre(String nombre);

    // @Query("SELECT DISTINCT a FROM Autor a WHERE a.fechaFallecimiento > :year AND a.fechaNacimiento < :year")
    // List<Autor> buscarAutorVivoPorYear(int year);

    @Query("SELECT DISTINCT a.nombre FROM Autor a WHERE a.fechaFallecimiento > :year AND a.fechaNacimiento < :year")
    List<String> obtenerAutoresVivosPorYear(int year);
    
}
