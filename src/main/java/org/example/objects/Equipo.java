package org.example.objects;

import org.example.exceptions.ValidationException;

public class Equipo {
    private String codigo;
    private String nombre;
    private Categoria categoria;
    private boolean disponible = true;

    public Equipo() {
    }

    public Equipo(String codigo, String nombre, Categoria categoria) throws ValidationException {
        isValidNombre(nombre);
        isValidCodigo(codigo);
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    private void isValidCodigo(String codigo) throws ValidationException {
        if(codigo == null || codigo.isBlank()) {
            throw new ValidationException(("¡Ingresa un codigo!"));
        }
    }

    private void isValidNombre(String nombre) throws ValidationException {
        if(nombre == null || nombre.isBlank()) {
            throw new ValidationException(("¡Ingresa un nombre!"));
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) throws ValidationException {
        isValidCodigo(codigo);
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws ValidationException {
        isValidNombre(nombre);
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        return """
                Codigo: %s
                Nombre: %s
                Categoria: %s
                Disponible: %s
                """.formatted(codigo, nombre, categoria.toString(), (disponible ? "Si" : "No"));
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
