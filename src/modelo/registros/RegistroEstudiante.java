package modelo.registros;

import modelo.Estudiante;
import modelo.abstracciones.Registro;

import java.util.List;

public class RegistroEstudiante extends Registro<Estudiante> {

    public RegistroEstudiante(List<Estudiante> eliminados) {
        super(eliminados);
    }
}
