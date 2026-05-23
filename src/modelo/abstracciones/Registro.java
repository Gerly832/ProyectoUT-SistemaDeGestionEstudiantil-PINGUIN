package modelo.abstracciones;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Registro<T> {
    private static final AtomicInteger contador = new AtomicInteger(0);
    private final int id;
    private final List<T> eliminados;
    private final String fechaEliminacion;

    public Registro(List<T> eliminados) {
        this.id = contador.incrementAndGet();
        this.eliminados = new ArrayList<>(eliminados);
        this.fechaEliminacion = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd|MM|yy| - |HH:mm"));
    }

    public int getId() {
        return id;
    }

    public List<T> getEliminados() {
        return eliminados;
    }

    public String getFechaEliminacion() {
        return fechaEliminacion;
    }
}