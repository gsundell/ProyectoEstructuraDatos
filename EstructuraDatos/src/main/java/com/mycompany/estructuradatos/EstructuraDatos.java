package com.mycompany.estructuradatos;

import javax.swing.JOptionPane;

public class EstructuraDatos {

    public static void main(String[] args) {
        Usuarios.Pila usuarios = new Usuarios.Pila();
        CatalogoServicios.Cola servicios = new CatalogoServicios.Cola();
        CatalogoPersonal.ListaDoble personal = new CatalogoPersonal.ListaDoble();
        Ventas.ArbolVentas ventas = new Ventas.ArbolVentas();
        Caja caja = new Caja(); 

        servicios.cargarServicios();

        JOptionPane.showMessageDialog(null, "Para acceder a computadores MYSOFT, debe registrarse primero");
        usuarios.registro();

        boolean acceso = false;

        while (!acceso) {
            JOptionPane.showMessageDialog(null, "Inicie sesión para continuar");
            acceso = usuarios.login();
        }

        while (true) {

            String[] botones = {"Usuarios", "Servicios", "Personal", "Ventas", "Caja", "Salir"};

            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "=== Bienvenido a Computadores MYSOFT ===",
                    "Menu Principal",
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
                menuPersonal(personal);
            }
            if (opcion == 3) {
                menuVentas(ventas, servicios, usuarios);
            }
            if (opcion == 4) { 
                menuCaja(caja);
            }
            if (opcion == 5 || opcion == -1) {
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
                    "=== Menu Usuarios ===",
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
                    "=== Catalogo de Servicios ===",
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

    public static void menuPersonal(CatalogoPersonal.ListaDoble personal) {

        while (true) {

            String[] botones = {
                "Agregar Persona",
                "Editar Persona",
                "Inactivar Persona",
                "Mostrar Personal",
                "Volver"
            };

            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "=== Catalogo de Personal ===",
                    "Personal",
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
                    personal.agregar();
                    break;

                case 1:
                    personal.editar();
                    break;

                case 2:
                    personal.inactivar();
                    break;

                case 3:
                    personal.mostrar();
                    break;
            }
        }
    }

    
    public static void menuCaja(Caja caja) {

        while (true) {

            String[] botones = {
                "Agregar Ingreso",
                "Mostrar Ingresos",
                "Total del Dia",
                "Volver"
            };

            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "=== Modulo Caja ===",
                    "Caja",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    botones,
                    botones[0]
            );

            if (opcion == -1 || opcion == 3) {
                return;
            }

            switch (opcion) {

                case 0:
                    String fecha = JOptionPane.showInputDialog("Ingrese la fecha");

                    double monto = Double.parseDouble(
                            JOptionPane.showInputDialog("Ingrese el monto"));

                    caja.agregarIngreso(fecha, monto);

                    JOptionPane.showMessageDialog(null, "Ingreso agregado correctamente");
                    break;

                case 1:
                    caja.mostrarIngresos();
                    break;

                case 2:
                    double total = caja.totalIngresos();
                    JOptionPane.showMessageDialog(null, "Total del dia: " + total);
                    break;
            }
        }
    }
}
