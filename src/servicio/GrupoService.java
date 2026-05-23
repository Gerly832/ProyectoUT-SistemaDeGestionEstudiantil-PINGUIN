package servicio;

import modelo.enums.Materia;

import java.util.List;

public interface GrupoService {
    void registrarGrupo(Materia materiaCodigo, String profesorId, int cantidadEstudiantes);
    List<String> verGrupos();
    void eliminarGrupo(String... grupoIds);
    List<String> verGruposEliminados();
}
