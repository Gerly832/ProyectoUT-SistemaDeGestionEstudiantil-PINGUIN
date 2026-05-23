package modelo.enums;

public enum Materia {
    MATEMATICA("MAT", "matematica"),
    LENGUA("LEN", "lengua"),
    INGLES("IN", "ingles"),
    SOCIALES("SOC", "sociales"),
    ETICA("ETI", "etica");

    private final String codigo;
    private final String nombreCompleto;

    Materia(String codigo, String nombreCompleto) {
        this.codigo = codigo;
        this.nombreCompleto = nombreCompleto;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public static Materia fromCodigo(String codigo) {
        for (Materia m : values()) {
            if (m.codigo.equalsIgnoreCase(codigo)) {
                return m;
            }
        }
        return null;
    }
}
