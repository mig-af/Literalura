package com.alura.literalura.libro;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
    @JsonAlias("title")String titulo,
    @JsonAlias("authors") List<DatosAutor> autor,
    @JsonAlias("languages") List<String> idioma,
    @JsonAlias("download_count") int numeroDescargas
) {

}
