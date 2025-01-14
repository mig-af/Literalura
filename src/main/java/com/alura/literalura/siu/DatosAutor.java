package com.alura.literalura.siu;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAutor(
    @JsonAlias("name") String nombre,
    @JsonAlias("birth_year") Integer fechaNacimiento,
    @JsonAlias("death_year") Integer fechaFallecimiento

) {

}
