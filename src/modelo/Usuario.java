package modelo;

// DEFINE COMO ES UN USUARIO INDIVIDUALMENTE
public class Usuario {

// ATRIBUTOS
    public Integer dni;
    public String nombre;
    public String correo;
    public String contrasena;
    public Boolean profesor;

// CONSTRUCTOR
    public Usuario(Integer dni, String nombre, String correo, String contrasena, boolean esProfesor) {
        this.dni = dni;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.profesor = esProfesor;
    }

}
