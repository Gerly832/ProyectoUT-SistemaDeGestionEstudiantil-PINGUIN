import implementacion.GestionUsuarios;

import java.util.Scanner;

public class Main {
    public static void main( String[] args) { // Arreglo cadena de texto

        GestionUsuarios gestionUsuarios = new GestionUsuarios();

            // ------------------- MENÚ -------------------
            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\n*** SISTEMA DE GESTIÓN ESTUDIANTIL - PINGUIN ***\n");
                System.out.println("--- MENÚ DE OPCIONES ---");
                System.out.println("1. Registrar Usuario");
                System.out.println("2. Listar usuarios registrados");
                System.out.println("3. Borrar usuario registrado");
                System.out.println("4. Listar usuarios borrados");
                System.out.println("5. Salir");
                System.out.println("------------------------\n");
                System.out.print("Seleccione una opción: \n");

                // Validar que sea un número
                while (!scanner.hasNextInt()) {
                    System.out.print("Por favor, ingrese un número válido ( Rango: 1-5 ): ");
                    scanner.next();
                }

                opcion = scanner.nextInt();

                // Lógica del menú
                switch (opcion) {
                    case 1:
                        System.out.println("\nOpción: Registrar Usuario\n");

                        // Gestionar el registro de usuarios y validación

                        gestionUsuarios.registrarUsuario();
                        break;

                    case 2:
                        System.out.println("\nOpción: Listar usuarios registrados\n");

                        // Listar usuarios registrados
                        gestionUsuarios.listarUsuarios();
                        break;

                    case 3:
                        System.out.println("\nOpción: Borrar usuario registrado\n");

                        gestionUsuarios.borrarUsuarios();
                        break;

                    case 4:
                        System.out.println("\nOpción: Listar usuarios borrados\n");

                        gestionUsuarios.listarBorrados();
                        break;

                }
            } while (opcion != 5);

            scanner.close();
    }
}

// ------------ FINALIZACIÓN DE MENÚ -------------