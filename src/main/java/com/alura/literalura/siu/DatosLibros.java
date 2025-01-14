package com.alura.literalura.siu;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
    //Una lista de libros del tipo DatosLibro
    @JsonAlias("results")List<DatosLibro> resultado
    ) {

}
