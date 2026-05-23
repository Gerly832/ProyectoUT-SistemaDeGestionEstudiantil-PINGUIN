package utils;

import modelo.enums.Materia;

import java.text.Normalizer;

public class ReguexUtils {

    public static final String  EMAIL_DOMAIN = "@colegio.edu.co";
    public static boolean esDocumentoValido(String documento) {
        return documento != null && documento.matches("\\d+");
    }

    public static boolean esCorreoValido(String correo) {
        if (correo == null) return false;
        return correo.endsWith(EMAIL_DOMAIN) && correo.length() > EMAIL_DOMAIN.length();
    }

    public static String generarIdEstudiante(String documento) {
        return "EST-" + documento;
    }

    public static String generarIdProfesor(String documento) {
        return "PROF-" + documento;
    }

    public static Materia obtenerMateriaPorCodigo(String codigo) {
        Materia m = Materia.fromCodigo(codigo.toUpperCase());
        if (m == null) {
            throw new IllegalArgumentException("Código de materia inválido: " + codigo);
        }
        return m;
    }

    public static String generarCorreo(String nombreCompleto) {

        String sinAcentos = Normalizer.normalize(nombreCompleto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", ""); // elimina tildes

        String partes = sinAcentos.toLowerCase().trim().replaceAll("\\s+", " "); // espacios múltiples a uno
        String[] palabras = partes.split(" ");

        StringBuilder sb = new StringBuilder();

        for (var i = 0; i < palabras.length; i++) {
            sb.append(palabras[i]);
            if (i < palabras.length - 1) {
                sb.append(".");
            }
        }
        return sb.toString() + EMAIL_DOMAIN;
    }
}
