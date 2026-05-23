package modelo;


import modelo.abstracciones.Usuario;
import modelo.enums.Materia;

import java.util.ArrayList;
import java.util.List;

public class Profesor extends Usuario {
    private Materia especialidad;
    private List<Grupo> grupos;

    public Profesor(String correoInstitucional, String documento, String nombre, Materia especialidad) {
        super(correoInstitucional, documento, nombre);
        this.especialidad = especialidad;
        this.grupos = new ArrayList<>();
    }

    @Override
    public String getId() {
        return "PROF-" + documento;
    }

    public Materia getEspecialidad() { return especialidad; }
    public List<Grupo> getGrupos() { return grupos; }

    public void setEspecialidad(Materia especialidad) { this.especialidad = especialidad; }

    @Override
    public String toString() {
        return getId() + " - " + nombre + " (" + especialidad.getNombreCompleto() + ")";
    }
}
