package com.alura.literalura.siu;

import java.util.List;

public class Libro {
    
    
    private String titulo;
    private List<Autor> autores;
    private String idioma;
    private Integer cantidadDescargas;

    public Libro(){}
    
    public Libro(DatosLibro libro){
        this.titulo = libro.titulo();
        
    }


    public String getTitulo() {
        return titulo;
    }
    public List<Autor> getAutores() {
        return autores;
    }
    public String getIdioma() {
        return idioma;
    }
    public Integer getCantidadDescargas() {
        return cantidadDescargas;
    }



    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    public void setCantidadDescargas(Integer cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }


}
