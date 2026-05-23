package servicio.impl;

import modelo.Estudiante;
import modelo.registros.RegistroEstudiante;
import servicio.UsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class EstudianteServiceImpl implements UsuarioService<Estudiante> {
    private final List<Estudiante> estudiantes = new ArrayList<>();
    private final List<RegistroEstudiante> historialEliminados = new ArrayList<>();
    private final Queue<Estudiante> colaSinCurso;

    public EstudianteServiceImpl(Queue<Estudiante> colaSinCurso) {
        this.colaSinCurso = colaSinCurso;
    }

    @Override
    public void registrarUsuario(Estudiante estudiante) {
        if (verUsuarioPorId(estudiante.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un estudiante con ID " + estudiante.getId());
        }
        estudiantes.add(estudiante);
        colaSinCurso.add(estudiante);  // por defecto sin curso
        System.out.println("Estudiante registrado: " + estudiante.getId());
    }

    @Override
    public List<Estudiante> verUsuarios() {
        return new ArrayList<>(estudiantes);
    }

    @Override
    public Estudiante verUsuarioPorId(String id) {
        return estudiantes.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void eliminarUsuario(String... ids) {
        List<Estudiante> eliminadosAhora = new ArrayList<>();
        for (String id : ids) {
            id = id.trim();
            Estudiante e = verUsuarioPorId(id);
            if (e == null) {
                System.out.println("Estudiante no encontrado: " + id);
                continue;
            }
            // Si está en un grupo, removerlo del grupo
            if (e.isTieneCurso() && e.getGrupoAsignado() != null) {
                e.getGrupoAsignado().removerEstudiante(e);
            }
            // Remover de la cola si estuviera (por si no tiene curso)
            colaSinCurso.remove(e);
            estudiantes.remove(e);
            eliminadosAhora.add(e);
        }
        if (!eliminadosAhora.isEmpty()) {
            historialEliminados.add(new RegistroEstudiante(eliminadosAhora));
        }
        System.out.println("Estudiantes eliminados: " + eliminadosAhora.size());
    }

    @Override
    public List<String> verUsuariosEliminados() {
        List<String> result = new ArrayList<>();
        for (RegistroEstudiante re : historialEliminados) {
            StringBuilder sb = new StringBuilder();
            sb.append("Registro #").append(re.getId()).append(" | Fecha: ").append(re.getFechaEliminacion()).append("\n");
            for (Estudiante e : re.getEliminados()) {
                sb.append("   ").append(e.getId()).append(" - ").append(e.getNombre()).append("\n");
            }
            result.add(sb.toString());
        }
        return result;
    }

    @Override
    public List<Estudiante> verUsuarioPorCurso(String grupoId) {
        return estudiantes.stream()
                .filter(e -> e.isTieneCurso() && e.getGrupoAsignado().getGrupoId().equals(grupoId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Estudiante> verUsuariosSinCurso() {
        return new ArrayList<>(colaSinCurso);
    }
}
