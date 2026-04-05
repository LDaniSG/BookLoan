package org.example.objects;

import org.example.exceptions.ValidationException;

public class Aprendiz {
    private String documento;
    private String nombre;
    private String ficha;
    private String celular;

    public Aprendiz() {
    }

    public Aprendiz(String documento, String nombre, String ficha, String celular) throws ValidationException {
        validDocumento(documento);
        this.documento = documento;
        this.nombre = nombre;
        this.ficha = ficha;
        this.celular = celular;
    }

    @Override
    public String toString() {
        return """
                Documento: %s
                Nombre: %s
                Ficha: %s
                Celular: %s
                """.formatted(this.documento, this.nombre, this.ficha, this.celular);
    }

    private void validDocumento(String documento) throws ValidationException {
        if(documento == null || documento.isBlank()) {
            throw new ValidationException("¡Ingresa un documento valido!");
        }
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) throws ValidationException {
        validDocumento(documento);
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFicha() {
        return ficha;
    }

    public void setFicha(String ficha) {
        this.ficha = ficha;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        if(celular.length() == 10) {
            this.celular = celular;
        }
    }
}
