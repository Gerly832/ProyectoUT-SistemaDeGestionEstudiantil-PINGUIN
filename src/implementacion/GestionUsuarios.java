package implementacion;

import interfaces.IGestionUsuarios;

import modelo.Usuario;

import modelo.Estudiante;
import modelo.Profesor;
import java.util.ArrayList;
import java.util.Scanner;


// SE GESTIONAN LOS PROCESOS CON LOS USUARIOS
public class GestionUsuarios implements IGestionUsuarios {

    private ArrayList<Usuario> mostrarUsuarios = new ArrayList<>();
    private ArrayList<Usuario> mostrarUsuariosBorrados = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);


    public void registrarUsuario() {

        System.out.println("--- REGISTRAR NUEVO USUARIO ---");
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

        // Limpia la información anterior
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
            Usuario nuevoProfesor = new Usuario(identificacion, nombreCompleto, correoIngresado, claveIngresada, esProfesor);
            mostrarUsuarios.add(nuevoProfesor);
            System.out.println("\n¡Profesor registrado con éxito!");

        } else if (esEstudiante) {
            // No es profesor, pero su correo termina en @ut.edu.co
            Usuario nuevoEstudiante = new Usuario(identificacion, nombreCompleto, correoIngresado, claveIngresada, esProfesor);
            mostrarUsuarios.add(nuevoEstudiante);
            System.out.println("\n¡Estudiante registrado con éxito!");

        } else {
            // No cumplió ninguna de las dos condiciones
            System.out.println("Error: El registro ha sido rechazado.");
            System.out.println("El documento no se encuentra autorizado O el correo no pertenece a la UT.");
        }
    }

    @Override
    public void mostrarUsuarios() {
        System.out.println("--- LISTADO DE USUARIOS REGISTRADOS ---");

        if (mostrarUsuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados en el sistema.");
        } else {
            for (Usuario u : mostrarUsuarios) {
                System.out.println("DNI: " + u.dni + " | Nombre: " + u.nombre + " | Correo: " + u.correo + " | Contraseña: " + u.contrasena + " | Profesor: " + u.profesor);
            }
        }
    }

    @Override
    public void borrarUsuarios() {
        System.out.println("--- BORRAR USUARIO ---");
        if (mostrarUsuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados en el sistema.");
        } else {

            System.out.println("\nPor favor digite el número de documento del usuario: ");
            int identification = scanner.nextInt();

            Usuario usuarioEncontrado = null;

            for (Usuario u : mostrarUsuarios) {
                if (u.dni == identification) {
                    usuarioEncontrado = u;
                    break;
                }
            }
            if (usuarioEncontrado != null) {

                System.out.println("\nEl usuario que desea eliminar es:" + " | DNI: " + usuarioEncontrado.dni + " | Nombre: " + usuarioEncontrado.nombre + " | Profesor: " + usuarioEncontrado.profesor);
                System.out.println("Esta seguro? S/N");

                // Limpia la información anterior -- De lo contrario se presenta un salto de línea y se omite la respuesta en la confirmación
                scanner.nextLine();

                String validacion = scanner.nextLine();

                if (validacion.equalsIgnoreCase("S")) {

                    Usuario usuarioBorrado = new Usuario(usuarioEncontrado.dni, usuarioEncontrado.nombre, usuarioEncontrado.correo, usuarioEncontrado.contrasena, usuarioEncontrado.profesor);
                    mostrarUsuariosBorrados.add(usuarioBorrado);

                    mostrarUsuarios.remove(usuarioEncontrado);
                    System.out.println("\n ** Usuario eliminado exitosamente. **");
                }
                else {
                    System.out.println("-- Proceso cancelado --");
                }
            } else {
                System.out.println("No se encontro ningún usuario con ese documento");
            }
        }

    }
    @Override
    public void mostrarUsuariosBorrados() {
        System.out.println("--- HISTORIAL DE USUARIOS ELIMINADOS ---");

        if (mostrarUsuariosBorrados.isEmpty()) {
            System.out.println("No hay usuarios registrados en el sistema.");
        } else {
            for (Usuario u : mostrarUsuariosBorrados) {
                System.out.println("DNI: " + u.dni + " | Nombre: " + u.nombre + " | Correo: " + u.correo + " | Contraseña: " + u.contrasena + " | Profesor: " + u.profesor);
            }
        }

    }

    public ArrayList<Usuario> getmostrarUsuariosBorrados() {
        return mostrarUsuariosBorrados;
    }

}

