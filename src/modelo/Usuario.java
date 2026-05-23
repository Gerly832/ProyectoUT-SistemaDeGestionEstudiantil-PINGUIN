package modelo;


import interfaces.ListarUsuarios;

// DEFINE COMO ES UN USUARIO INDIVIDUALMENTE
public class Usuario {

// ATRIBUTOS
    public Integer dni;
    public String nombre;
    public String correo;

// CONSTRUCTOR
    public Usuario(Integer dni, String nombre, String correo) {
        this.dni = dni;
        this.nombre = nombre;
        this.correo = correo;
    }

}
