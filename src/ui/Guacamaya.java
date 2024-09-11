package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;

    public static void main(String[] args) {
        inicializarGlobales();
        menu();
    }

    public static void inicializarGlobales() {
        reader = new Scanner(System.in);
    }

    public static void menu() {
        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: " + calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: " + calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: " + calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las " + precios.length + " referencias vendidas en el dia, " + consultarReferenciasSobreLimite(limite) + " superaron el limite minimo de ventas de " + limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;
                default:
                    System.out.println("Opcion invalida, intente de nuevo.");
                    break;
            }

        } while (!salir);

    }

    public static void establecerCantidadVendida() {
        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];
    }


/**
 * Descripcion: Solicita al usuario el precio y la cantidad de cada referencia de producto vendida y los almacena en los arreglos globales.
 * pre: El Scanner reader debe estar inicializado y los arreglos precios y unidades deben estar inicializados.
 * pos: Los arreglos precios y unidades quedan llenos con los datos ingresados por el usuario.
 */

     public static void solicitarDatos() {
        for (int i = 0; i < precios.length; i++) {
            System.out.println("Digite el precio de la referencia " + (i + 1) + ": ");
            precios[i] = reader.nextDouble();
            System.out.println("Digite la cantidad vendida de la referencia " + (i + 1) + ": ");
            unidades[i] = reader.nextInt();
        }
    }



/**
 * Descripcion: Calcula el total de unidades vendidas sumando las cantidades en el arreglo unidades.
 * pre: El arreglo unidades debe estar inicializado y lleno con las cantidades de cada referencia.
 * pos: Se retorna la suma total de las unidades vendidas.
 * @return int El total de unidades vendidas.
 */

    public static int calcularTotalUnidadesVendidas() {
        int total = 0;
        for (int cantidad : unidades) {
            total += cantidad;
        }
        return total;
    }

    public static double calcularPrecioPromedio() {
        double sumaPrecios = 0;
        for (double precio : precios) {
            sumaPrecios += precio;
        }
        return sumaPrecios / precios.length;
    }

/**
 * Descripcion: Calcula las ventas totales multiplicando los precios por las cantidades de cada referencia.
 * pre: Los arreglos precios y unidades deben estar inicializados y llenos con datos correspondientes.
 * pos: Se retorna el total de dinero recaudado por las ventas del dia.
 * @return double El total de ventas (dinero recaudado).
 */

     
    public static double calcularVentasTotales() {
        double totalVentas = 0;
        for (int i = 0; i < precios.length; i++) {
            totalVentas += precios[i] * unidades[i];
        }
        return totalVentas;
    }

    public static int consultarReferenciasSobreLimite(double limite) {
        int contador = 0;
        for (int cantidad : unidades) {
            if (cantidad > limite) {
                contador++;
            }
        }
        return contador;
    }
}   