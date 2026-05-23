package modelo;

import modelo.abstracciones.Usuario;

public class Estudiante extends Usuario {
    private int edad;
    private boolean tieneCurso;
    private Grupo grupoAsignado;

    public Estudiante(String correoInstitucional, String documento, String nombre, int edad) {
        super(correoInstitucional, documento, nombre);
        this.edad = edad;
        this.tieneCurso = false;
        this.grupoAsignado = null;
    }

    @Override
    public String getId() {
        return "EST-" + documento;
    }

    public int getEdad() { return edad; }
    public boolean isTieneCurso() { return tieneCurso; }
    public Grupo getGrupoAsignado() { return grupoAsignado; }

    public void setEdad(int edad) { this.edad = edad; }
    public void setTieneCurso(boolean tieneCurso) { this.tieneCurso = tieneCurso; }
    public void setGrupoAsignado(Grupo grupoAsignado) { this.grupoAsignado = grupoAsignado; }

    @Override
    public String toString() {
        return getId() + " - " + nombre + " (Doc: " + documento + ")";
    }
}
