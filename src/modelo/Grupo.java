package modelo;

import modelo.enums.Materia;

import java.util.ArrayList;
import java.util.List;

public class Grupo {
    private String grupoId;
    private Materia materia;
    private Profesor profesorAsignado;
    private List<Estudiante> estudiantes;
    private int numeroEstudiantes;

    public Grupo(String grupoId, Materia materia, Profesor profesorAsignado) {
        this.grupoId = grupoId;
        this.materia = materia;
        this.profesorAsignado = profesorAsignado;
        this.estudiantes = new ArrayList<>();
        this.numeroEstudiantes = 0;
    }

    public String getGrupoId() { return grupoId; }
    public Materia getMateria() { return materia; }
    public Profesor getProfesorAsignado() { return profesorAsignado; }
    public List<Estudiante> getEstudiantes() { return estudiantes; }
    public int getNumeroEstudiantes() { return numeroEstudiantes; }

    public void agregarEstudiante(Estudiante e) {
        estudiantes.add(e);
        e.setTieneCurso(true);
        e.setGrupoAsignado(this);
        numeroEstudiantes++;
    }

    public void removerEstudiante(Estudiante e) {
        if (estudiantes.remove(e)) {
            e.setTieneCurso(false);
            e.setGrupoAsignado(null);
            numeroEstudiantes--;
        }
    }

    @Override
    public String toString() {
        return grupoId + " | " + materia.getNombreCompleto() + " | Prof: " +
                (profesorAsignado != null ? profesorAsignado.getNombre() : "Sin asignar") +
                " | Estudiantes: " + numeroEstudiantes;
    }

}
