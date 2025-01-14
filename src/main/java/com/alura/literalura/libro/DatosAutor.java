package com.alura.literalura.libro;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;


public record DatosAutor(

    @JsonAlias("name") String nombre,
    @JsonAlias("birth_year") int fechaNacimiento,
    @JsonAlias("death_year") int fechaFallecimiento,
    List<Libro> libros
    
)
{

}
