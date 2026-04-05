package org.example.objects;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.DisponibilidadException;
import org.example.exceptions.PrestamoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class InventarioPrestamo {
    private List<Aprendiz> aprendices = new ArrayList<>();
    private List<Equipo> equipos = new ArrayList<>();
    private List<Prestamo> prestamos = new ArrayList<>();

    public InventarioPrestamo() {
    }

    public void registrarAprendiz(Aprendiz aprendiz) {
        aprendices.add(aprendiz);
    }

    public void registrarEquipo(Equipo equipo) {
        equipos.add(equipo);
    }

    public List<String> listarEquipos() {
        return equipos.stream().map(Equipo::toString).toList();
    }

    public List<String> listarAprendices() {
        return aprendices.stream().map(Aprendiz::toString).toList();
    }

    public Equipo buscarEquipoCodigo(String codigo) throws NotFoundException {
        return equipos.stream().filter(equipo ->  equipo.getCodigo().equals(codigo)).findFirst().orElseThrow(() -> new NotFoundException(("¡El equipo no se encuentra!")));
    }

    public void prestarEquipo(Equipo equipo, Aprendiz aprendiz) throws DisponibilidadException {
        if(!equipo.isDisponible()) {
            throw new DisponibilidadException("El equipo " + equipo.getNombre() + " no esta disponible!");
        }
        equipo.setDisponible(false);
        prestamos.add(new Prestamo(ThreadLocalRandom.current().nextLong(1, 9000), aprendiz, equipo, LocalDate.now()));
    }

    public Aprendiz buscarAprendiz(String documento) throws NotFoundException {
        return aprendices.stream().filter(aprendiz -> aprendiz.getDocumento().equals(documento)).findFirst().orElseThrow(() -> new NotFoundException("¡El aprendiz no se encuentra!"));
    }

    public void devolverEquipo(Long prestamoId) throws DisponibilidadException, PrestamoException {
        Prestamo prestamo = prestamos.stream().filter(pre -> Objects.equals(pre.getId(), prestamoId)).findFirst().orElseThrow(() -> new PrestamoException(("¡No se encontro el prestamo!")));
        if(!prestamo.isActivo()) {
            throw new PrestamoException("El prestamo ya esta inactivo");
        }
        prestamo.getEquipo().setDisponible(true);
        prestamo.devolucion(LocalDate.now());
    }

    public List<String> listarPrestamosActivos() {
        return prestamos.stream().filter(Prestamo::isActivo).map(Prestamo::toString).toList();
    }
}
