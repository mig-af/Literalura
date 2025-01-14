package com.alura.literalura.principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.alura.literalura.libro.Autor;
import com.alura.literalura.libro.DatosAutorDTO;
import com.alura.literalura.libro.DatosBusquedaLibro;
import com.alura.literalura.libro.DatosLibro;
import com.alura.literalura.libro.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

public class Principal {


    //1 Buscar libro por titulo
    //2 listar libros registrados
    //3 listar autores registrados
    //4 Listar autores de un determinado ao
    //listar libros por idioma
    private ConsumoAPI api;
    String url = "https://gutendex.com/books/?search=";

    private ConvierteDatos convierte = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    //private DatosLibro datosLibro;
    
    
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;


    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void menu(){
        
        var num = 1;
        while(num != 0){
           
            System.out.println(colorsito("rojo")+"             L I T E R A L  U R A                     "+colorsito("fin"));
            System.out.print(Opciones.opciones());
        
        
        var opcion = teclado.nextInt();
        teclado.nextLine();
           
        
        switch (opcion) {
            
            case 1:
                buscarLibro();
                break;
            case 2:
                verLibrosGuardados();
                break;

            case 3:
                listarAutoresRegistrados();
                break;

            case 4:
                listAuthorForYear();
                break;
            case 5:
                listarLibrosPorIdioma();
                break;

            case 0:
                System.out.println(colorsito("rojo")+"  Adios  "+colorsito("fin"));
                System.out.println("");
                num = 0;
                break;
            default:
            System.out.println("Opcion no valida");
                break;
        }
        }
        teclado.close();
        


        
    }

    private void buscarLibro(){
        api = new ConsumoAPI();
        System.out.println("Escribe nombre de un libro: ");
        var busqueda = teclado.nextLine();
        
        System.out.println("BUSCANDO......: "+busqueda);
        var json = api.getData(url+busqueda.replace(" ", "+"));
        
        DatosBusquedaLibro datos = convierte.obtenerDatos(json, DatosBusquedaLibro.class);
        //data.resultado().forEach(e -> System.out.println(e.titulo() + " " + e.autores() + " " + e.idiomas()));
        if(datos.count() > 0){
            
            DatosLibro datosLibro =  datos.resultado()[0];
            if(existeLibro(datosLibro) == true){
                System.out.println("El libro ya se encuentra registrado");
                
            }else{
                mostrarDatosLibroBuscado(datosLibro);
                guardarDatosEnDB(datosLibro);
            }
            

        }else{
            System.out.println("No se encontraron resultados");
        }
        
    }

    public void verLibrosGuardados(){
        var libros =libroRepository.findAll();
        libros.stream().forEach(e -> System.out.println(
            colorsito("verde")+"------------------ LIBRO --------------------------"+colorsito("fin")+
            "\nTitulo: " + e.getTitulo() +
            "\nAutor: " + e.getAutor().getNombre() +
            "\nIdioma: " + e.getIdioma() +
            "\nNumero de descargas: "+ e.getNumeroDescargas()+
            "\n--------------------------------------------"
        ));

    }

    public void listarAutoresRegistrados(){
        List<String> nombresAutores = autorRepository.obtenerNombresDeAutores();
        
        //var autores = autorRepository.findAutores();
        // autores.forEach(e -> System.out.println(
        //                 colorsito("verde")+"-------------------- AUTOR ------------------------"+colorsito("fin")+
        //                 "\nAutor: "+e.getNombre() + "\n"+
        //                 "Fecha de nacimiento: "+e.getFechaNacimiento() + "\n"+
        //                 "Fecha de Fallecimiento: "+e.getFechaFallecimiento() + "\n"+
        //                 "Libro: "+ libroRepository.buscarLibroPorAutor(e.getNombre()) + "\n"+
        //                 "--------------------------------------------")
        //                 );
        
        
        List<DatosAutorDTO> datosAutor = new ArrayList<>();
       
        
        for(var i = 0; i < nombresAutores.size(); i++){

            var autor = autorRepository.buscarAutorPorNombre(nombresAutores.get(i)).get(0);
            datosAutor.add(new DatosAutorDTO(
                        autor.getNombre(),
                        autor.getFechaNacimiento(), 
                        autor.getFechaFallecimiento(), 
                        libroRepository.buscarLibroPorAutor(autor.getNombre())));

        }
        datosAutor.forEach(e -> System.out.println(
            colorsito("verde")+"-------------------- AUTOR ------------------------"+colorsito("fin")+
                         "\nAutor: "+e.nombre() + "\n"+
                         "Fecha de nacimiento: "+e.fechaNacimiento() + "\n"+
                        "Fecha de Fallecimiento: "+e.fechaFallecimiento() + "\n"+
                         "Libro: "+ libroRepository.buscarLibroPorAutor(e.nombre()) + "\n"+
                         "--------------------------------------------"
        ));
        

    }

    public void listAuthorForYear(){
        System.out.println("Escribe el año: ");
        var edadAutor = teclado.nextInt();


        System.out.println("RESULTADO: ");
        
        var nombreAutoresVivos = autorRepository.obtenerAutoresVivosPorYear(edadAutor);
        nombreAutoresVivos.forEach(nombre -> {
            var autor = autorRepository.buscarAutorPorNombre(nombre).get(0);
            System.out.println( 
                "-------------------------------"+
                "\nAutor: "+autor.getNombre() +
                
                "\nFecha de nacimiento: "+autor.getFechaNacimiento() +
                "\nFecha de Fallecimiento: "+autor.getFechaFallecimiento() +
                "\nLibros: "+ libroRepository.buscarLibroPorAutor(autor.getNombre()) + 
                "\n--------------------------------------------"
            );
        });

    }

    public void listarLibrosPorIdioma(){
        System.out.println(
            """

            es: espanol
            en: ingles
            fr: frances
            pt: portugues
            
            opcion: """
        );
        var idioma = teclado.nextLine();
        List<Libro> libros = libroRepository.buscarLibroPorIdioma(idioma);
        System.out.println(" RESULTADO:  ");
        libros.forEach(l -> System.out.println(
            "-----------------------------"+
            "\nTitulo: " + l.getTitulo() +
            "\nAutor: "+ l.getAutor().getNombre()+
            "\nIdioma: "+ l.getIdioma()+
            "\nNumero Descargas: "+ l.getNumeroDescargas()+
            "\n--------------------------------------------"
        ));

    }







    private void guardarDatosEnDB(DatosLibro datosLibro){
        var libro = new Libro(datosLibro);
        var autor = new Autor(datosLibro.autor().get(0));
        autor.setLibro(libro);
        libro.setAutor(autor);
        libroRepository.save(libro);
    }

    private void mostrarDatosLibroBuscado(DatosLibro datosLibro){
        System.out.println(
            "--------------------------------------------"+
            "\nTitulo: " + datosLibro.titulo() +
            "\nAutor: " + datosLibro.autor().get(0).nombre() +
            "\nIdioma: " + datosLibro.idioma().get(0) +
            "\nNumero Descargas: " + datosLibro.numeroDescargas()+
            "\n--------------------------------------------"
        ); 
    }


    public Boolean existeLibro(DatosLibro datosLibro){
            return libroRepository.existsByTitulo(datosLibro.titulo());
    }


    public String colorsito(String color){

        String rojo = "\033[41m";
        String verde = "\033[42m";
        String amarillo = "\033[43m";
        String azul = "\033[44m";

        switch (color) {
            case "rojo":
                return rojo;
            case "verde":
                return verde;
            case "amarillo":
                return amarillo;
                
            case "azul":
                return azul; 
                
            case "fin":
                return "\033[0m";

            default:

                return "";
        }
    }
}
