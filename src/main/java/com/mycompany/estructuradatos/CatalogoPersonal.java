
package com.mycompany.estructuradatos;


import javax.swing.JOptionPane;

public class CatalogoPersonal {

    static class NodoPersonal {

        String cedula;
        String nombre;
        String apellido1;
        String apellido2;
        String direccion;
        String telefono;
        boolean activo;

        NodoPersonal siguiente;
        NodoPersonal anterior;

        public NodoPersonal(String cedula, String nombre, String apellido1, String apellido2, String direccion, String telefono) {
            this.cedula = cedula;
            this.nombre = nombre;
            this.apellido1 = apellido1;
            this.apellido2 = apellido2;
            this.direccion = direccion;
            this.telefono = telefono;
            this.activo = true;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    static class ListaDoble {

        NodoPersonal inicio;

        public void agregar() {

            String cedula = JOptionPane.showInputDialog("Cédula:");
            if (cedula == null) return;

            if (existe(inicio, cedula)) {
                JOptionPane.showMessageDialog(null, "La persona ya existe");
                return;
            }

            String nombre = JOptionPane.showInputDialog("Nombre:");
            String apellido1 = JOptionPane.showInputDialog("Primer apellido:");
            String apellido2 = JOptionPane.showInputDialog("Segundo apellido:");
            String direccion = JOptionPane.showInputDialog("Dirección:");
            String telefono = JOptionPane.showInputDialog("Teléfono:");

            NodoPersonal nuevo = new NodoPersonal(cedula, nombre, apellido1, apellido2, direccion, telefono);

            inicio = agregarRec(inicio, nuevo);

            JOptionPane.showMessageDialog(null, "Persona agregada correctamente");
        }

        private NodoPersonal agregarRec(NodoPersonal actual, NodoPersonal nuevo) {
            if (actual == null) {
                return nuevo;
            }
            actual.siguiente = agregarRec(actual.siguiente, nuevo);
            actual.siguiente.anterior = actual;
            return actual;
        }

        private boolean existe(NodoPersonal actual, String cedula) {
            if (actual == null) return false;

            if (actual.cedula.equalsIgnoreCase(cedula)) {
                return true;
            }

            return existe(actual.siguiente, cedula);
        }

        public void editar() {

            String cedula = JOptionPane.showInputDialog("Cédula a editar:");
            if (cedula == null) return;

            if (!existe(inicio, cedula)) {
                JOptionPane.showMessageDialog(null, "La persona no existe");
                return;
            }

            editarRec(inicio, cedula);
        }

        private void editarRec(NodoPersonal actual, String cedula) {
            if (actual == null) return;

            if (actual.cedula.equalsIgnoreCase(cedula)) {

                actual.telefono = JOptionPane.showInputDialog("Nuevo teléfono:");
                actual.direccion = JOptionPane.showInputDialog("Nueva dirección:");

                JOptionPane.showMessageDialog(null, "Persona editada correctamente");
                return;
            }

            editarRec(actual.siguiente, cedula);
        }

        public void inactivar() {

            String cedula = JOptionPane.showInputDialog("Cédula a inactivar:");
            if (cedula == null) return;

            if (!existe(inicio, cedula)) {
                JOptionPane.showMessageDialog(null, "La persona no existe");
                return;
            }

            inactivarRec(inicio, cedula);
        }

        private void inactivarRec(NodoPersonal actual, String cedula) {
            if (actual == null) return;

            if (actual.cedula.equalsIgnoreCase(cedula)) {

                if (!actual.activo) {
                    JOptionPane.showMessageDialog(null, "Ya está inactivo");
                    return;
                }

                actual.activo = false;

                JOptionPane.showMessageDialog(null, "Persona inactivada");
                return;
            }

            inactivarRec(actual.siguiente, cedula);
        }

        public NodoPersonal buscarPersona(String cedula) {
            return buscarRec(inicio, cedula);
        }

        private NodoPersonal buscarRec(NodoPersonal actual, String cedula) {
            if (actual == null) return null;

            if (actual.cedula.equalsIgnoreCase(cedula)) {
                return actual;
            }

            return buscarRec(actual.siguiente, cedula);
        }

        public void mostrar() {

            NodoPersonal actual = inicio;

            if (actual == null) {
                JOptionPane.showMessageDialog(null, "No hay personal registrado");
                return;
            }

            String lista = "======= PERSONAL =======\n\n";

            while (actual != null) {

                String estado = actual.activo ? "Activo" : "Inactivo";

                lista += "Cédula: " + actual.cedula
                        + "\nNombre: " + actual.nombre + " " + actual.apellido1 + " " + actual.apellido2
                        + "\nDirección: " + actual.direccion
                        + "\nTeléfono: " + actual.telefono
                        + "\nEstado: " + estado
                        + "\n\n---------------------\n\n";

                actual = actual.siguiente;
            }

            JOptionPane.showMessageDialog(null, lista);
        }
    }
}