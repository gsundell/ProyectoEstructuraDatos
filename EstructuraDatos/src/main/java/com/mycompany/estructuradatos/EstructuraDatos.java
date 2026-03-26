package com.mycompany.estructuradatos;

import javax.swing.JOptionPane;

public class EstructuraDatos {

    public static void main(String[] args) {
        Usuarios.Pila usuarios = new Usuarios.Pila();
        CatalogoServicios.Cola servicios = new CatalogoServicios.Cola();
        Ventas.ArbolVentas ventas = new Ventas.ArbolVentas();
        servicios.cargarServicios();

        //REGISTRO Y LOGIN
        JOptionPane.showMessageDialog(null, "Para acceder a computadores MYSOFT, debe registrarse primero");
        usuarios.registro();

        boolean acceso = false;

        while (!acceso) {
            JOptionPane.showMessageDialog(null, "Inicie sesión para continuar");
            acceso = usuarios.login();
        }
        //AQUI PASAMOS A MENU CON BOTONES 
        while (true) {

            String[] botones = {"Usuarios", "Servicios", "Ventas", "Salir"};

            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "=== Bienvenido a Computadores MYSOFT ===",
                    "Menú Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    botones,
                    botones[0]
            );

            if (opcion == 0) {
                menuUsuarios(usuarios);
            }
            if (opcion == 1) {
                CatalogoServicios(servicios);
            }
            if (opcion == 2) {
                menuVentas(ventas, servicios, usuarios);
            }
            if (opcion == 3 || opcion == -1) {
                System.exit(0);
            }
        }

    }

    public static void menuVentas(Ventas.ArbolVentas ventas, CatalogoServicios.Cola servicios, Usuarios.Pila usuarios) {
        while (true) {
            String[] botones = {
                "Comprar",
                "Modificar",
                "Cancelar",
                "Mostrar",
                "Volver"
            };
            int op = JOptionPane.showOptionDialog(
                    null,
                    "=== Ventas ===",
                    "Ventas",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    botones,
                    botones[0]
            );

            if (op == 0) {
                ventas.comprar(servicios, usuarios);
            }
            if (op == 1) {
                ventas.modificar();
            }
            if (op == 2) {
                ventas.cancelar();
            }
            if (op == 3) {
                ventas.mostrarVentas();
            }
            if (op == 4 || op == -1) {
                return;
            }
        }
    }

    //MENU DE USUARIOS
    public static void menuUsuarios(Usuarios.Pila usuarios) {

        while (true) {

            String[] botones = {
                "Agregar Usuario",
                "Inactivar Usuario",
                "Mostrar Usuarios",
                "Volver"
            };

            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "=== Menú Usuarios ===",
                    "Usuarios",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    botones,
                    botones[0]
            );

            switch (opcion) {

                case 0:
                    usuarios.agregar();
                    break;

                case 1:
                    usuarios.inactivarUsuario();
                    break;

                case 2:
                    usuarios.mostrar();
                    break;

                case 3:
                    return;
            }
        }
    }

    //MENU DE CATÁLOGO DE SERVICIOS 
    public static void CatalogoServicios(CatalogoServicios.Cola servicios) {

        while (true) {

            String[] botones = {
                "Agregar Servicio",
                "Editar Servicio",
                "Inactivar Servicio",
                "Mostrar Servicios",
                "Volver"
            };

            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "=== Catálogo de Servicios ===",
                    "Servicios",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    botones,
                    botones[0]
            );

            if (opcion == -1 || opcion == 4) {
                return; 
            }

            switch (opcion) {

                case 0:

                    String descripcion = JOptionPane.showInputDialog("Descripción del servicio");

                    double costo = Double.parseDouble(
                            JOptionPane.showInputDialog("Costo del servicio"));

                    int personas = Integer.parseInt(
                            JOptionPane.showInputDialog("Personas a cargo"));

                    servicios.enqueue(descripcion, costo, personas);

                    break;

                case 1:

                    String editar = JOptionPane.showInputDialog("Servicio a editar");
                    servicios.editar(editar);

                    break;

                case 2:

                    String inactivar = JOptionPane.showInputDialog("Servicio a inactivar");
                    servicios.inactivar(inactivar);

                    break;

                case 3:

                    servicios.mostrar();

                    break;
            }
        }
    }
}
