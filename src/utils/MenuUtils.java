package utils;

import modelo.Estudiante;
import modelo.Profesor;
import modelo.enums.Materia;
import servicio.impl.EstudianteServiceImpl;
import servicio.impl.GrupoServiceImpl;
import servicio.impl.ProfesorServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuUtils {
    private static final Scanner sc = new Scanner(System.in);

    // ---------- MENÚ PRINCIPAL ----------
    public static void menuInicial(GrupoServiceImpl grupoService,
                                   EstudianteServiceImpl estudianteService,
                                   ProfesorServiceImpl profesorService) {
        while (true) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Administrar Grupos");
            System.out.println("2. Administrar Usuarios");
            System.out.println("3. Salir");
            System.out.print("Seleccione: ");
            String op = sc.nextLine().trim();
            switch (op) {
                case "1": menuGrupos(grupoService, estudianteService, profesorService); break;
                case "2": menuUsuarios(estudianteService, profesorService); break;
                case "3": System.out.println("¡Hasta luego!"); return;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    // ---------- MENÚ GRUPOS ----------
    private static void menuGrupos(GrupoServiceImpl grupoService,
                                   EstudianteServiceImpl estudianteService,
                                   ProfesorServiceImpl profesorService) {
        while (true) {
            System.out.println("\n--- MENÚ GRUPOS ---");
            System.out.println("1. Registrar grupo");
            System.out.println("2. Ver grupos");
            System.out.println("3. Eliminar grupo");
            System.out.println("4. Ver grupos eliminados");
            System.out.println("5. Volver");
            System.out.print("Seleccione: ");
            String op = sc.nextLine().trim();
            try {
                switch (op) {
                    case "1": registrarGrupo(grupoService, estudianteService, profesorService); break;
                    case "2": verGrupos(grupoService); break;
                    case "3": eliminarGrupo(grupoService); break;
                    case "4": verGruposEliminados(grupoService); break;
                    case "5": return;
                    default: System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void registrarGrupo(GrupoServiceImpl grupoService,
                                       EstudianteServiceImpl estudianteService,
                                       ProfesorServiceImpl profesorService) {
        System.out.println("Materias disponibles:");
        for (Materia m : Materia.values()) {
            System.out.println(m.getCodigo() + " - " + m.getNombreCompleto());
        }
        System.out.print("Ingrese código de la materia: ");
        String cod = sc.nextLine().trim();
        Materia materia = ReguexUtils.obtenerMateriaPorCodigo(cod);

        List<Profesor> profes = profesorService.verProfesoresPorMateria(materia);
        if (profes.isEmpty()) {
            System.out.println("No hay profesores con especialidad " + materia.getNombreCompleto());
            return;
        }
        System.out.println("Profesores disponibles:");
        profes.forEach(p -> System.out.println(p.getId() + " - " + p.getNombre()));
        System.out.print("Ingrese ID del profesor: ");
        String idProf = sc.nextLine().trim();

        List<Estudiante> sinCurso = estudianteService.verUsuariosSinCurso();
        System.out.println("Estudiantes sin curso disponibles: " + sinCurso.size());
        System.out.print("Cantidad de estudiantes para el grupo: ");
        int cant = Integer.parseInt(sc.nextLine().trim());

        grupoService.registrarGrupo(materia, idProf, cant);
    }

    private static void verGrupos(GrupoServiceImpl grupoService) {
        List<String> lista = grupoService.verGrupos();
        if (lista.isEmpty()) {
            System.out.println("No hay grupos registrados.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private static void eliminarGrupo(GrupoServiceImpl grupoService) {
        verGrupos(grupoService);
        System.out.print("Ingrese ID(s) del grupo a eliminar (separados por coma): ");
        String entrada = sc.nextLine().trim();
        if (entrada.isEmpty()) return;
        String[] ids = entrada.split(",");
        grupoService.eliminarGrupo(ids);
    }

    private static void verGruposEliminados(GrupoServiceImpl grupoService) {
        List<String> lista = grupoService.verGruposEliminados();
        if (lista.isEmpty()) {
            System.out.println("No hay registros de eliminación.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    // ---------- MENÚ USUARIOS ----------
    private static void menuUsuarios(EstudianteServiceImpl estudianteService,
                                     ProfesorServiceImpl profesorService) {
        while (true) {
            System.out.println("\n--- MENÚ USUARIOS ---");
            System.out.println("1. Administrar Estudiantes");
            System.out.println("2. Administrar Profesores");
            System.out.println("3. Volver");
            System.out.print("Seleccione: ");
            String op = sc.nextLine().trim();
            switch (op) {
                case "1": menuEstudiantes(estudianteService); break;
                case "2": menuProfesores(profesorService); break;
                case "3": return;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    // ---------- MENÚ ESTUDIANTES ----------
    private static void menuEstudiantes(EstudianteServiceImpl service) {
        while (true) {
            System.out.println("\n--- MENÚ ESTUDIANTES ---");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Ver estudiantes");
            System.out.println("3. Ver estudiantes matriculados en un curso");
            System.out.println("4. Ver estudiantes sin matricular");
            System.out.println("5. Eliminar estudiante");
            System.out.println("6. Ver estudiantes eliminados");
            System.out.println("7. Volver");
            System.out.print("Seleccione: ");
            String op = sc.nextLine().trim();
            try {
                switch (op) {
                    case "1": registrarEstudiante(service); break;
                    case "2": verEstudiantes(service); break;
                    case "3": verEstudiantesPorCurso(service); break;
                    case "4": verEstudiantesSinCurso(service); break;
                    case "5": eliminarEstudiante(service); break;
                    case "6": verEstudiantesEliminados(service); break;
                    case "7": return;
                    default: System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void registrarEstudiante(EstudianteServiceImpl service) {
        System.out.print("Ingrese documento (solo dígitos): ");
        String doc = sc.nextLine().trim();
        while (!ReguexUtils.esDocumentoValido(doc)) {
            System.out.print("Documento inválido. Solo dígitos: ");
            doc = sc.nextLine().trim();
        }
        String id = ReguexUtils.generarIdEstudiante(doc);
        System.out.println("ID generado: " + id);

        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Edad: ");
        int edad = Integer.parseInt(sc.nextLine().trim());

        String correo = ReguexUtils.generarCorreo(nombre);
        Estudiante e = new Estudiante(correo, doc, nombre, edad);
        service.registrarUsuario(e);
    }

    private static void verEstudiantes(EstudianteServiceImpl service) {
        List<Estudiante> lista = service.verUsuarios();
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            lista.forEach(e -> System.out.println(e.getId() + " - " + e.getNombre() +
                    (e.isTieneCurso() ? " [Curso: " + e.getGrupoAsignado().getGrupoId() + "]" : " [Sin curso]")));
        }
    }

    private static void verEstudiantesPorCurso(EstudianteServiceImpl service) {
        System.out.print("Ingrese ID del grupo: ");
        String grupoId = sc.nextLine().trim();
        List<Estudiante> lista = service.verUsuarioPorCurso(grupoId);
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes en ese curso.");
        } else {
            lista.forEach(e -> System.out.println(e.getId() + " - " + e.getNombre()));
        }
    }

    private static void verEstudiantesSinCurso(EstudianteServiceImpl service) {
        List<Estudiante> lista = service.verUsuariosSinCurso();
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes sin curso.");
        } else {
            System.out.println("Estudiantes sin curso (" + lista.size() + "):");
            lista.forEach(e -> System.out.println(e.getId() + " - " + e.getNombre()));
        }
    }

    private static void eliminarEstudiante(EstudianteServiceImpl service) {
        verEstudiantes(service);
        System.out.print("Ingrese ID(s) a eliminar (separados por coma): ");
        String entrada = sc.nextLine().trim();
        if (entrada.isEmpty()) return;
        String[] ids = entrada.split(",");
        service.eliminarUsuario(ids);
    }

    private static void verEstudiantesEliminados(EstudianteServiceImpl service) {
        List<String> lista = service.verUsuariosEliminados();
        if (lista.isEmpty()) {
            System.out.println("No hay registros de eliminación.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    // ---------- MENÚ PROFESORES ----------
    private static void menuProfesores(ProfesorServiceImpl service) {
        while (true) {
            System.out.println("\n--- MENÚ PROFESORES ---");
            System.out.println("1. Registrar profesor");
            System.out.println("2. Ver profesores");
            System.out.println("3. Ver profesores por materia");
            System.out.println("4. Ver profesores sin curso");
            System.out.println("5. Eliminar profesor");
            System.out.println("6. Ver profesores eliminados");
            System.out.println("7. Volver");
            System.out.print("Seleccione: ");
            String op = sc.nextLine().trim();
            try {
                switch (op) {
                    case "1": registrarProfesor(service); break;
                    case "2": verProfesores(service); break;
                    case "3": verProfesoresPorMateria(service); break;
                    case "4": verProfesoresSinCurso(service); break;
                    case "5": eliminarProfesor(service); break;
                    case "6": verProfesoresEliminados(service); break;
                    case "7": return;
                    default: System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void registrarProfesor(ProfesorServiceImpl service) {
        System.out.print("Ingrese documento (solo dígitos): ");
        String doc = sc.nextLine().trim();
        while (!ReguexUtils.esDocumentoValido(doc)) {
            System.out.print("Documento inválido. Solo dígitos: ");
            doc = sc.nextLine().trim();
        }
        String id = ReguexUtils.generarIdProfesor(doc);
        System.out.println("ID generado: " + id);

        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine().trim();

        String correo = ReguexUtils.generarCorreo(nombre);
        System.out.println("Correo generado: " + correo);

        System.out.println("Materias disponibles:");
        for (Materia m : Materia.values()) {
            System.out.println(m.getCodigo() + " - " + m.getNombreCompleto());
        }
        System.out.print("Código de especialidad: ");
        String cod = sc.nextLine().trim();
        Materia especialidad = ReguexUtils.obtenerMateriaPorCodigo(cod);

        Profesor p = new Profesor(correo, doc, nombre, especialidad);
        service.registrarUsuario(p);
    }

    private static void verProfesores(ProfesorServiceImpl service) {
        List<Profesor> lista = service.verUsuarios();
        if (lista.isEmpty()) {
            System.out.println("No hay profesores registrados.");
        } else {
            lista.forEach(p -> System.out.println(p.getId() + " - " + p.getNombre() +
                    " (" + p.getEspecialidad().getNombreCompleto() + ")"));
        }
    }

    private static void verProfesoresPorMateria(ProfesorServiceImpl service) {
        System.out.print("Ingrese código de materia: ");
        String cod = sc.nextLine().trim();
        Materia materia = ReguexUtils.obtenerMateriaPorCodigo(cod);
        List<Profesor> lista = service.verProfesoresPorMateria(materia);
        if (lista.isEmpty()) {
            System.out.println("No hay profesores con esa especialidad.");
        } else {
            lista.forEach(p -> System.out.println(p.getId() + " - " + p.getNombre()));
        }
    }

    private static void verProfesoresSinCurso(ProfesorServiceImpl service) {
        List<Profesor> lista = service.verUsuariosSinCurso();
        if (lista.isEmpty()) {
            System.out.println("Todos los profesores tienen al menos un curso.");
        } else {
            lista.forEach(p -> System.out.println(p.getId() + " - " + p.getNombre()));
        }
    }

    private static void eliminarProfesor(ProfesorServiceImpl service) {
        verProfesores(service);
        System.out.print("Ingrese ID(s) a eliminar (separados por coma): ");
        String entrada = sc.nextLine().trim();
        if (entrada.isEmpty()) return;
        String[] ids = entrada.split(",");
        service.eliminarUsuario(ids);
    }

    private static void verProfesoresEliminados(ProfesorServiceImpl service) {
        List<String> lista = service.verUsuariosEliminados();
        if (lista.isEmpty()) {
            System.out.println("No hay registros de eliminación.");
        } else {
            lista.forEach(System.out::println);
        }
    }
}
