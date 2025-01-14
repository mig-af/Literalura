package com.alura.literalura.libro;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;



@Entity 
@Table(name="autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;

    @OneToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;
    


    public Autor(){}
    public Autor(DatosAutor datosAutores) {
        if(datosAutores.nombre() == null){
            this.nombre = "Desconocido";
            this.fechaNacimiento = 0;
            this.fechaFallecimiento = 0;
        }else{
            this.nombre = datosAutores.nombre();
            this.fechaNacimiento = datosAutores.fechaNacimiento();
            this.fechaFallecimiento = datosAutores.fechaFallecimiento();
        }
        
    }

    public Long getId() {
        return id;
    }
    
    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }
    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }
    public String getNombre() {
        return nombre;
    }

    public Libro getLibro() {
        return libro;
    }




    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
