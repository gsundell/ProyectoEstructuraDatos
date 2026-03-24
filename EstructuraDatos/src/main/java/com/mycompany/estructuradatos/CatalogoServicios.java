package com.mycompany.estructuradatos;

import javax.swing.JOptionPane;

public class CatalogoServicios {

    static class NodoServicio {

        String descripcion;
        double costo;
        int personasCargo;
        boolean activo;

        NodoServicio siguiente;

        public NodoServicio(String descripcion, double costo, int personasCargo) {
            this.descripcion = descripcion;
            this.costo = costo;
            this.personasCargo = personasCargo;
            this.activo = true;
            siguiente = null;
        }
    }

    static class Cola {

        NodoServicio frente;
        NodoServicio fin;

        // agregar servicio
        public void enqueue(String descripcion, double costo, int personas) {

            if (existe(frente, descripcion)) {
                JOptionPane.showMessageDialog(null, "El servicio ya existe");
                return;
            }

            NodoServicio nuevo = new NodoServicio(descripcion, costo, personas);

            if (frente == null) {
                frente = nuevo;
                fin = nuevo;
            } else {
                fin.siguiente = nuevo;
                fin = nuevo;
            }

            JOptionPane.showMessageDialog(null, "Servicio agregado correctamente");
        }

        // Verificación con recursividad
        private boolean existe(NodoServicio actual, String descripcion) {

            if (actual == null) {
                return false;
            }

            if (actual.descripcion.equalsIgnoreCase(descripcion)) {
                return true;
            }

            return existe(actual.siguiente, descripcion);
        }

        // Editar el servicio
        public void editar(String descripcion) {

            if (!existe(frente, descripcion)) {
                JOptionPane.showMessageDialog(null, "Sin ediciones");
                return;
            }

            editarRecursivo(frente, descripcion);
        }

        private void editarRecursivo(NodoServicio actual, String descripcion) {

            if (actual == null) {
                return;
            }

            if (actual.descripcion.equalsIgnoreCase(descripcion)) {

                actual.costo = Double.parseDouble(
                        JOptionPane.showInputDialog("Nuevo costo del servicio"));

                actual.personasCargo = Integer.parseInt(
                        JOptionPane.showInputDialog("Nueva cantidad de personas"));

                JOptionPane.showMessageDialog(null, "Servicio editado correctamente");
                return;
            }

            editarRecursivo(actual.siguiente, descripcion);
        }
public NodoServicio buscarServicio(String descripcion){
    return buscarRec(frente, descripcion);
}
private NodoServicio buscarRec(NodoServicio actual, String descripcion){
    if (actual==null) return null;
    
    if (actual.descripcion.equalsIgnoreCase(descripcion)){
       return actual;
    }
    return buscarRec(actual.siguiente, descripcion);
}
        // Inactivar el servicio
        public void inactivar(String descripcion) {

            if (!existe(frente, descripcion)) {
                JOptionPane.showMessageDialog(null, "El servicio no existe");
                return;
            }

            inactivarRecursivo(frente, descripcion);
        }

        private void inactivarRecursivo(NodoServicio actual, String descripcion) {

            if (actual == null) {
                return;
            }

            if (actual.descripcion.equalsIgnoreCase(descripcion)) {

                if (!actual.activo) {
                    JOptionPane.showMessageDialog(null, "El servicio ya está inactivo");
                    return;
                }

                actual.activo = false;

                JOptionPane.showMessageDialog(null, "Servicio inactivado correctamente");
                return;
            }

            inactivarRecursivo(actual.siguiente, descripcion);
        }

        // Mostrar servicios
        public void mostrar() {

            NodoServicio actual = frente;

            if (actual == null) {
                JOptionPane.showMessageDialog(null, "No hay servicios registrados");
                return;
            }

            String lista = "======= SERVICIOS =======\n\n";

            while (actual != null) {

                String estado;

                if (actual.activo) {
                    estado = "Activo";
                } else {
                    estado = "Inactivo";
                }

                lista = lista
                        + "Servicio: " + actual.descripcion
                        + "\nCosto: " + actual.costo
                        + "\nPersonas a cargo: " + actual.personasCargo
                        + "\nEstado: " + estado
                        + "\n\n------------------------\n\n";

                actual = actual.siguiente;
            }

            JOptionPane.showMessageDialog(null, lista);
        }

        // Carga de servicios para iniciar con unos
        public void cargarServicios() {

            enqueue("Mantenimiento de Computadora", 15000, 2);
            enqueue("Instalación de Sistema Operativo", 20000, 1);
            enqueue("Limpieza Interna", 10000, 1);

        }
    }
}
