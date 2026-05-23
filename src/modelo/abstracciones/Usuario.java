package modelo.abstracciones;

public abstract class Usuario {
    protected String correoInstitucional;
    protected String documento;
    protected String nombre;

    public Usuario(String correoInstitucional, String documento, String nombre) {
        this.correoInstitucional = correoInstitucional;
        this.documento = documento;
        this.nombre = nombre;
    }

    public abstract String getId();

    public String getCorreoInstitucional() { return correoInstitucional; }
    public String getDocumento() { return documento; }
    public String getNombre() { return nombre; }

    public void setCorreoInstitucional(String correoInstitucional) { this.correoInstitucional = correoInstitucional; }
    public void setDocumento(String documento) { this.documento = documento; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
