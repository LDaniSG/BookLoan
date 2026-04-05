package org.example;

import org.example.exceptions.DisponibilidadException;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.PrestamoException;
import org.example.exceptions.ValidationException;
import org.example.objects.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final InventarioPrestamo inventarioPrestamo  = new InventarioPrestamo();
    private static final List<String> categorias = Arrays.stream(Categoria.values()).map(Enum::toString).toList();

    public static void main(String[] args) {
        int option = 0;
        do {
            System.out.println("""
                    ---Opciones---
                    1. Registrar aprendiz.
                    2. Registrar equipo.
                    3. Listar equipos.
                    4. Prestar equipos.
                    5. Listar prestamos activos.
                    6. Devolver equipo.
                    7. Salir.
                    
                    Selecciona una opción:
                    """);
            option = scanner.nextInt();
            options(option);
        } while(option != 7);

    }

    private static void options(int option) {
        scanner.nextLine();
        switch(option) {
            case 1: {
                registrarAprendiz();
                break;
            }
            case 2: {
                registrarEquipo();
                break;
            }
            case 3: {
                listarEquipos();
                break;
            }
            case 4: {
                prestarEquipos();
                break;
            }
            case 5: {
                listarPrestamosActivos();
                break;
            }
            case 6: {
                devolverEquipo();
                break;
            }
            case 7: {
                System.out.println("¡Sesión finalizada!");
                break;
            }
            default: {
                System.out.println("¡La opción seleccionada no existe!");
            }
        }
    }

    private static void registrarAprendiz() {
        boolean isNoRegister = true;
        Aprendiz aprendiz = null;
        do {
            System.out.print("Ingresa el documento:");
            String documento = scanner.nextLine();
            System.out.print("Ingresa el nombre:");
            String nombre = scanner.nextLine();
            System.out.print("Ingresa la ficha:");
            String ficha = scanner.nextLine();
            System.out.print("Ingresa el celular:");
            String celular = scanner.nextLine();
            try {
                aprendiz = new Aprendiz(documento, nombre, ficha, celular);
                isNoRegister = false;
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        } while(isNoRegister);
        inventarioPrestamo.registrarAprendiz(aprendiz);
        System.out.println("¡Aprendiz registrado!");
    }

    private static void registrarEquipo() {
        boolean isNoRegister = true;
        Equipo equipo = null;
        do{
            System.out.print("Ingresa el codigo:");
            String codigo = scanner.nextLine();
            System.out.print("Ingresa el nombre:");
            String nombre = scanner.nextLine();
            boolean isInvalid = true;
            Categoria categoriaEnum = null;
            do{
                System.out.print("Ingresa la categoria:");
                String categoria = scanner.nextLine();
                try {
                    categoriaEnum = Categoria.valueOf(categoria.toUpperCase());
                    isInvalid = false;
                } catch (IllegalArgumentException e) {
                    System.out.println("Las categorias disponibles son: " + String.join(", " , categorias));
                }
            } while(isInvalid);
            try {
                equipo = new Equipo(codigo, nombre, categoriaEnum);
                isNoRegister = false;
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        } while(isNoRegister);
        inventarioPrestamo.registrarEquipo(equipo);
        System.out.println("¡Equipo registrado!");

    }

    private static void listarEquipos() {
        System.out.println("---Equipos---");
        System.out.println(String.join("\n-", inventarioPrestamo.listarEquipos()));
    }

    private static void prestarEquipos() {
        System.out.println("---Aprendices---");
        System.out.println(String.join("\n-", inventarioPrestamo.listarAprendices()));
        boolean isNoExist = true;
        Aprendiz aprendiz = null;
        do {
            System.out.print("Ingresa el documento del aprendiz:");
            String documentoAprendiz = scanner.nextLine();
            try {
                aprendiz = inventarioPrestamo.buscarAprendiz(documentoAprendiz);
                isNoExist = false;
            } catch (NotFoundException e) {
                System.out.println(e.getMessage());
            }
        }while(isNoExist);
        System.out.println("---Equipos---");
        System.out.println(String.join("\n-", inventarioPrestamo.listarEquipos()));
        Equipo equipo = null;
        isNoExist = true;
        do {
            System.out.print("Ingresa el codigo del equipo:");
            String equipoCodigo = scanner.nextLine();
            try {
                equipo = inventarioPrestamo.buscarEquipoCodigo(equipoCodigo);
                isNoExist = false;
            } catch (NotFoundException e) {
                System.out.println(e.getMessage());
            }
        }while(isNoExist);
        try {
            inventarioPrestamo.prestarEquipo(equipo, aprendiz);
            System.out.println("¡Prestamo realizado!");
        } catch (DisponibilidadException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarPrestamosActivos() {
        System.out.println("---Prestamos---");
        System.out.println(String.join("\n-", inventarioPrestamo.listarPrestamosActivos()));
    }

    private static void devolverEquipo() {
        System.out.print("Ingresa el codigo:");
        Long id = scanner.nextLong();
        scanner.nextLine();
        try {
            inventarioPrestamo.devolverEquipo(id);
            System.out.print("¡Devolución completa!");
        } catch (DisponibilidadException | PrestamoException e) {
            System.out.println(e.toString());
        }
    }
}