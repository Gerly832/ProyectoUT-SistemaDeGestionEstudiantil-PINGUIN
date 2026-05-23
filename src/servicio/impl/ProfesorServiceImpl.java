package servicio.impl;

import modelo.Profesor;
import modelo.enums.Materia;
import modelo.registros.RegistroProfesor;
import servicio.UsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProfesorServiceImpl implements UsuarioService<Profesor> {
    private final List<Profesor> profesores = new ArrayList<>();
    private final List<RegistroProfesor> historialEliminados = new ArrayList<>();

    @Override
    public void registrarUsuario(Profesor profesor) {
        if (verUsuarioPorId(profesor.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un profesor con ID " + profesor.getId());
        }
        profesores.add(profesor);
        System.out.println("Profesor registrado: " + profesor.getId());
    }

    @Override
    public List<Profesor> verUsuarios() {
        return new ArrayList<>(profesores);
    }

    @Override
    public Profesor verUsuarioPorId(String id) {
        return profesores.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void eliminarUsuario(String... ids) {
        List<Profesor> eliminadosAhora = new ArrayList<>();
        for (String id : ids) {
            id = id.trim();
            Profesor p = verUsuarioPorId(id);
            if (p == null) {
                System.out.println("Profesor no encontrado: " + id);
                continue;
            }
            // Si tiene grupos asignados, no se permite eliminar (o los desasignamos)
            if (!p.getGrupos().isEmpty()) {
                System.out.println("No se puede eliminar al profesor " + id + " porque tiene grupos asignados.");
                continue;
            }
            profesores.remove(p);
            eliminadosAhora.add(p);
        }
        if (!eliminadosAhora.isEmpty()) {
            historialEliminados.add(new RegistroProfesor(eliminadosAhora));
        }
        System.out.println("Profesores eliminados: " + eliminadosAhora.size());
    }

    @Override
    public List<String> verUsuariosEliminados() {
        List<String> result = new ArrayList<>();
        for (RegistroProfesor rp : historialEliminados) {
            StringBuilder sb = new StringBuilder();
            sb.append("Registro #").append(rp.getId()).append(" | Fecha: ").append(rp.getFechaEliminacion()).append("\n");
            for (Profesor p : rp.getEliminados()) {
                sb.append("   ").append(p.getId()).append(" - ").append(p.getNombre()).append("\n");
            }
            result.add(sb.toString());
        }
        return result;
    }

    @Override
    public List<Profesor> verUsuarioPorCurso(String grupoId) {
        return profesores.stream()
                .filter(p -> p.getGrupos().stream().anyMatch(g -> g.getGrupoId().equals(grupoId)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Profesor> verUsuariosSinCurso() {
        return profesores.stream()
                .filter(p -> p.getGrupos().isEmpty())
                .collect(Collectors.toList());
    }

    // Métodos extra para el menú
    public List<Profesor> verProfesoresPorMateria(Materia materia) {
        return profesores.stream()
                .filter(p -> p.getEspecialidad() == materia)
                .collect(Collectors.toList());
    }

}
