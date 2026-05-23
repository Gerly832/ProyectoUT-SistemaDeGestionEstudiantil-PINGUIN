package servicio;

import java.util.List;

public interface UsuarioService<T> {
    void registrarUsuario(T usuario);
    List<T> verUsuarios();
    T verUsuarioPorId(String id);
    void eliminarUsuario(String... ids);
    List<String> verUsuariosEliminados();
    List<T> verUsuarioPorCurso(String grupoId);
    List<T> verUsuariosSinCurso();
}
