package servicio.impl;

import modelo.Estudiante;
import modelo.Grupo;
import modelo.Profesor;
import modelo.enums.Materia;
import modelo.registros.RegistroGrupo;
import servicio.GrupoService;
import servicio.UsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class GrupoServiceImpl implements GrupoService {
    private final List<Grupo> grupos = new ArrayList<>();
    private final List<RegistroGrupo> historialEliminados = new ArrayList<>();
    private final Queue<Estudiante> colaEstudiantesSinCurso;
    private final UsuarioService<Profesor> profesorService;
    private final UsuarioService<Estudiante> estudianteService;

    public GrupoServiceImpl(Queue<Estudiante> colaSinCurso,
                            UsuarioService<Profesor> profesorService,
                            UsuarioService<Estudiante> estudianteService) {
        this.colaEstudiantesSinCurso = colaSinCurso;
        this.profesorService = profesorService;
        this.estudianteService = estudianteService;
    }

    @Override
    public void registrarGrupo(Materia materia, String profesorId, int cantidadEstudiantes) {
        // Generar ID del grupo
        int maxNum = grupos.stream()
                .filter(g -> g.getMateria() == materia)
                .map(g -> Integer.parseInt(g.getGrupoId().split("-")[1]))
                .max(Integer::compareTo)
                .orElse(0);
        String grupoId = materia.getCodigo() + "-" + String.format("%02d", maxNum + 1);

        // Buscar profesor
        Profesor profesor = profesorService.verUsuarioPorId(profesorId);
        if (profesor == null || profesor.getEspecialidad() != materia) {
            throw new IllegalArgumentException("Profesor no válido para esta materia.");
        }

        Grupo grupo = new Grupo(grupoId, materia, profesor);
        profesor.getGrupos().add(grupo);

        // Asignar estudiantes desde la cola
        if (cantidadEstudiantes > colaEstudiantesSinCurso.size()) {
            throw new IllegalArgumentException("No hay suficientes estudiantes sin curso. Disponibles: " +
                    colaEstudiantesSinCurso.size());
        }

        for (int i = 0; i < cantidadEstudiantes; i++) {
            Estudiante e = colaEstudiantesSinCurso.poll();
            grupo.agregarEstudiante(e);
        }

        grupos.add(grupo);
        System.out.println("Grupo " + grupoId + " creado exitosamente.");
    }

    @Override
    public List<String> verGrupos() {
        return grupos.stream().map(Grupo::toString).collect(Collectors.toList());
    }

    @Override
    public void eliminarGrupo(String... grupoIds) {
        List<Grupo> eliminadosAhora = new ArrayList<>();
        for (String id : grupoIds) {
            id = id.trim();
            String finalId = id;
            Grupo grupo = grupos.stream()
                    .filter(g -> g.getGrupoId().equals(finalId))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado: " + finalId));
            // Devolver estudiantes a la cola
            for (Estudiante e : grupo.getEstudiantes()) {
                e.setTieneCurso(false);
                e.setGrupoAsignado(null);
                colaEstudiantesSinCurso.add(e);
            }
            // Quitar grupo del profesor
            if (grupo.getProfesorAsignado() != null) {
                grupo.getProfesorAsignado().getGrupos().remove(grupo);
            }
            eliminadosAhora.add(grupo);
        }
        grupos.removeAll(eliminadosAhora);
        if (!eliminadosAhora.isEmpty()) {
            historialEliminados.add(new RegistroGrupo(eliminadosAhora));
        }
        System.out.println("Grupos eliminados: " + eliminadosAhora.size());
    }

    @Override
    public List<String> verGruposEliminados() {
        List<String> result = new ArrayList<>();
        for (RegistroGrupo rg : historialEliminados) {
            StringBuilder sb = new StringBuilder();
            sb.append("Registro #").append(rg.getId()).append(" | Fecha: ").append(rg.getFechaEliminacion()).append("\n");
            for (Grupo g : rg.getEliminados()) {
                sb.append("   ").append(g.getGrupoId()).append(" - ").append(g.getMateria().getNombreCompleto()).append("\n");
            }
            result.add(sb.toString());
        }
        return result;
    }

    // Método auxiliar para la interfaz de menú
    public List<Grupo> getGrupos() {
        return grupos;
    }
}
