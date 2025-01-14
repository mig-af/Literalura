package com.alura.literalura.libro;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;




@Entity
@Table(name="libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    //@Transient
    @OneToOne(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Autor autor;

    private String idioma;
    private int numeroDescargas;


    public Libro(){}

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
       
        this.idioma = datosLibro.idioma().get(0);
        this.numeroDescargas = datosLibro.numeroDescargas();
    }


    public String getTitulo() {
        return titulo;
    }
    public Autor getAutor() {
        return autor;
    }
    public String getIdioma() {
        return idioma;
    }
    public int getNumeroDescargas() {
        return numeroDescargas;
    }

    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    public void setNumeroDescargas(int numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
    
}
