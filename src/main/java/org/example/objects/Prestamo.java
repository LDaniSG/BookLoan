package org.example.objects;

import java.time.LocalDate;

public class Prestamo {
    private Long id;
    private Aprendiz aprendiz;
    private Equipo equipo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private boolean activo = true;

    public Prestamo() {
    }

    public Prestamo(Long id, Aprendiz aprendiz, Equipo equipo, LocalDate fechaPrestamo) {
        this.id = id;
        this.aprendiz = aprendiz;
        this.equipo = equipo;
        this.fechaPrestamo = fechaPrestamo;
    }

    public void devolucion(LocalDate fechaDevolucion) {
        this.activo = false;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aprendiz getAprendiz() {
        return aprendiz;
    }

    public void setAprendiz(Aprendiz aprendiz) {
        this.aprendiz = aprendiz;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return """
                Id: %s
                Aprendiz: %s
                Equipo: %s
                Fecha prestamo: %s
                Fecha devolucion: %s
                Activo: %s
                """.formatted(id.toString(), aprendiz.getDocumento() + '-' + aprendiz.getNombre(), equipo.getCodigo() + '-' + equipo.getNombre(), fechaPrestamo.toString(), (fechaDevolucion != null ? fechaDevolucion.toString() : "N/A"), (activo ? "Si" : "No"));
    }
}
