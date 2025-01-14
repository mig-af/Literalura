package com.alura.literalura.libro;
import java.util.List;



public record DatosAutorDTO(

    String nombre,
    int fechaNacimiento,
    int fechaFallecimiento,
    
    List<String> libros
) {

}
