import modelo.Estudiante;
import modelo.Profesor;
import modelo.enums.Materia;
import servicio.impl.EstudianteServiceImpl;
import servicio.impl.GrupoServiceImpl;
import servicio.impl.ProfesorServiceImpl;
import utils.MenuUtils;
import utils.ReguexUtils;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Queue<Estudiante> colaSinCurso = new LinkedList<>();

        EstudianteServiceImpl estudianteService = new EstudianteServiceImpl(colaSinCurso);
        ProfesorServiceImpl profesorService = new ProfesorServiceImpl();
        GrupoServiceImpl grupoService = new GrupoServiceImpl(colaSinCurso, profesorService, estudianteService);

        // Cargar datos por defecto
        cargarDatosIniciales(estudianteService, profesorService);

        MenuUtils.menuInicial(grupoService, estudianteService, profesorService);
    }

    private static void cargarDatosIniciales(EstudianteServiceImpl estService,
                                             ProfesorServiceImpl profService) {
        try {
            // Estudiantes: se puede usar el generador de correo o escribirlo manualmente
            estService.registrarUsuario(new Estudiante(
                    ReguexUtils.generarCorreo("Ana Gómez"), "12345678", "Ana Gómez", 15));
            estService.registrarUsuario(new Estudiante(
                    ReguexUtils.generarCorreo("Carlos Ruiz"), "23456789", "Carlos Ruiz", 16));
            estService.registrarUsuario(new Estudiante(
                    ReguexUtils.generarCorreo("Luisa Mora"), "34567890", "Luisa Mora", 14));
            estService.registrarUsuario(new Estudiante(
                    ReguexUtils.generarCorreo("Pedro Salazar"), "45678901", "Pedro Salazar", 15));

            // Profesores
            profService.registrarUsuario(new Profesor(
                    ReguexUtils.generarCorreo("Ricardo Vélez"), "10012345", "Ricardo Vélez", Materia.MATEMATICA));
            profService.registrarUsuario(new Profesor(
                    ReguexUtils.generarCorreo("Sofía Herrera"), "10023456", "Sofía Herrera", Materia.LENGUA));
            profService.registrarUsuario(new Profesor(
                    ReguexUtils.generarCorreo("John Smith"), "10034567", "John Smith", Materia.INGLES));

            System.out.println("Datos iniciales cargados con éxito.");
        } catch (Exception e) {
            System.err.println("Error al cargar datos iniciales: " + e.getMessage());
        }
    }
}
