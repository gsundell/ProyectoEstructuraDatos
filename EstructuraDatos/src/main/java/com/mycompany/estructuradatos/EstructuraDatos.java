package com.mycompany.estructuradatos;

import javax.swing.JOptionPane;

public class EstructuraDatos {

    public static void main(String[] args) {
        Usuarios.Pila usuarios = new Usuarios.Pila();
        Usuarios.Pila pila = new Usuarios.Pila();

        JOptionPane.showMessageDialog(null, "Para acceder a computadores MYSOFT, debe registrarse primero");
        pila.registro();

        boolean acceso = false;

        while (!acceso) {
            JOptionPane.showMessageDialog(null, "Inicie sesión para continuar");
            acceso = pila.login();
        }

        String opcion;

        do {
            opcion = JOptionPane.showInputDialog(
                    "=== Bienvenido a Computadores MYSOFT ===\n"
                    + "1.Agregar Usuario\n"
                    + "2.Inactivar Usuario\n"
                    + "3.Mostrar Usuarios\n"
                    + "4.Salir\n ");
            switch (opcion) {

                case "1":
                    usuarios.agregar();
                    break;
                case "2":
                    usuarios.inactivarUsuario();
                    break;
                case "3":
                    usuarios.mostrar();
                    ;
                    break;
                case "4":
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción Inválida");

            }

        } while (!opcion.equals("4"));
    }
}
