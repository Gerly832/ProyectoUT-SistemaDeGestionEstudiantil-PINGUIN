package implementacion;

import interfaces.ListarUsuarios;
import interfaces.RegistrarUsuario;
import interfaces.Usuarios;

import modelo.Usuario;

import modelo.Estudiante;
import modelo.Profesor;
import java.util.ArrayList;
import java.util.Scanner;


// SE GESTIONAN LOS PROCESOS CON LOS USUARIOS
public class GestionUsuarios implements Usuarios, RegistrarUsuario, ListarUsuarios {

    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void registrar() {

        System.out.println("Digite su número de documento por favor: ");

        int identificacion = scanner.nextInt();

        // Validación de DNI
        boolean esProfesor = false;
        for (int doc : Profesor.documentosProfesores) {
            if (doc == identificacion) {
                esProfesor = true;
                break;
            }
        }

        //Limpia la información anterior
        scanner.nextLine();

        System.out.println("\nDigite su nombre completo por favor: ");
        String nombreCompleto = scanner.nextLine();


        System.out.print("\nDigite su correo electrónico: \n");
        String correoIngresado = scanner.nextLine();

        boolean esEstudiante = correoIngresado.endsWith(Estudiante.correoInstitucional[0]);

        System.out.println("\nIngrese una contraseña: ");

        String claveIngresada = scanner.nextLine();

        if (esProfesor) {
            // El DNI está en la lista de profesores
            Usuario nuevoProfesor = new Usuario(identificacion, nombreCompleto, correoIngresado, claveIngresada);
            listaUsuarios.add(nuevoProfesor);
            System.out.println("\n¡Profesor registrado con éxito!");

        } else if (esEstudiante) {
            // No es profesor, pero su correo termina en @ut.edu.co
            Usuario nuevoEstudiante = new Usuario(identificacion, nombreCompleto, correoIngresado, claveIngresada);
            listaUsuarios.add(nuevoEstudiante);
            System.out.println("\n¡Estudiante registrado con éxito!");

        } else {
            // No cumplió ninguna de las dos condiciones
            System.out.println("Error: El registro ha sido rechazado.");
            System.out.println("El documento no se encuentra autorizado O el correo no pertenece a la UT.");
        }
    }

    @Override
    public void listar() {
        System.out.println("\n--- LISTA DE USUARIOS REGISTRADOS ---");

        if (listaUsuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados en el sistema.");
        } else {
            for (Usuario u : listaUsuarios) {
                System.out.println("DNI: " + u.dni + " | Nombre: " + u.nombre + " | Correo: " + u.correo + " | Contraseña: " + u.contrasena);
            }
        }
    }


    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
}
