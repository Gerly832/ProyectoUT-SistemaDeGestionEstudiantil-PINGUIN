package modelo.registros;

import modelo.Grupo;
import modelo.abstracciones.Registro;

import java.util.List;

public class RegistroGrupo extends Registro<Grupo> {
    public RegistroGrupo(List<Grupo> eliminados) {
        super(eliminados);
    }
}
