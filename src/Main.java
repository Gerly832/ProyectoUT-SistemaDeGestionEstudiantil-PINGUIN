import implementacion.GestionUsuarios;

import java.util.Scanner;

public static void main(String[] args) { // Arreglo cadena de texto

    GestionUsuarios gestionUsuarios = new GestionUsuarios();

        // ------------------- MENÚ -------------------
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n*** SISTEMA DE GESTIÓN ESTUDIANTIL - PINGUIN ***\n");
            System.out.println("--- MENÚ DE OPCIONES ---");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Iniciar Sesión");
            System.out.println("0. Salir");
            System.out.println("------------------------\n");
            System.out.print("Seleccione una opción: \n");

            // Validar que sea un número
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, ingrese un número válido: ");
                scanner.next();
            }

            opcion = scanner.nextInt();

            // Lógica del menú
            switch (opcion) {
                case 1:
                    System.out.println("\nOpción: Registrar Usuario\n");

                    // LÓGICA PARA REALIZAR EL REGISTRO DE USUARIOS

                    gestionUsuarios.registrar();
                    gestionUsuarios.listar();
                    break;


            }
        } while (opcion != 0);

        scanner.close();
            }


// ------------ FINALIZACIÓN DE MENÚ -------------