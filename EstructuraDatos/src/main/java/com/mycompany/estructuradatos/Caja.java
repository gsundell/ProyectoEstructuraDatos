package com.mycompany.estructuradatos;

public class Caja {

    static class NodoIngreso {
        String fecha;
        double monto;
        NodoIngreso siguiente;

        public NodoIngreso(String fecha, double monto) {
            this.fecha = fecha;
            this.monto = monto;
            this.siguiente = null;
        }
    }

    private NodoIngreso cabeza;

    public Caja() {
        cabeza = null;
    }

    public void agregarIngreso(String fecha, double monto) {
        NodoIngreso nuevo = new NodoIngreso(fecha, monto);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoIngreso actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    public void mostrarIngresos() {
        NodoIngreso actual = cabeza;

        if (actual == null) {
            System.out.println("No hay ingresos registrados.");
            return;
        }

        while (actual != null) {
            System.out.println("Fecha: " + actual.fecha +
                               " | Monto: " + actual.monto);
            actual = actual.siguiente;
        }
    }

    public double totalIngresos() {
        double total = 0;
        NodoIngreso actual = cabeza;

        while (actual != null) {
            total += actual.monto;
            actual = actual.siguiente;
        }

        return total;
    }
}