package modelo.registros;

import modelo.Profesor;
import modelo.abstracciones.Registro;

import java.util.List;

public class RegistroProfesor extends Registro<Profesor> {
    public RegistroProfesor(List<Profesor> eliminados) {
        super(eliminados);
    }
}
