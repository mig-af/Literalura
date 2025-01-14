package com.alura.literalura.libro;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosBusquedaLibro(
    int count,
    String next,
    String previous,
    @JsonAlias("results")DatosLibro[] resultado
) {

}
