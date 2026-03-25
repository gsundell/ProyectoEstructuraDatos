package com.mycompany.estructuradatos;

import javax.swing.JOptionPane;

public class Ventas {

    static class Venta {

        int id;
        CatalogoServicios.NodoServicio servicio;
        Usuarios.Nodo usuario;
        double monto;

        public Venta(int id, CatalogoServicios.NodoServicio servicio, Usuarios.Nodo usuario) {
            this.id = id;
            this.servicio = servicio;
            this.usuario = usuario;
            this.monto = servicio.costo;
        }
    }

    static class NodoVenta {

        Venta dato;
        NodoVenta izq, der;

        public NodoVenta(Venta dato) {
            this.dato = dato;
        }
    }

    static class ArbolVentas {

        NodoVenta raiz;

        public NodoVenta insertar(NodoVenta raiz, Venta v) {
            if (raiz == null) {
                return new NodoVenta(v);
            }
            if (v.id < raiz.dato.id) {
                raiz.izq = insertar(raiz.izq, v);
            } else if (v.id > raiz.dato.id) {
                raiz.der = insertar(raiz.der, v);
            }
            return raiz;
        }

        public void comprar(CatalogoServicios.Cola servicios, Usuarios.Pila usuarios) {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID de la venta:"));

            String desc = JOptionPane.showInputDialog("Servicio: ");
            CatalogoServicios.NodoServicio servicio = servicios.buscarServicio(desc);

            if (servicio == null) {
                JOptionPane.showMessageDialog(null, "Servicio no existe");
                return;
            }
            if (!servicio.activo) {
                JOptionPane.showMessageDialog(null, "Servicio no activo");
                return;
            }
            String user = JOptionPane.showInputDialog("Usuario");
            Usuarios.Nodo usuario = usuarios.buscarUsuario(user);

            if (usuario == null) {
                JOptionPane.showMessageDialog(null, "Usuario no existe");
                return;
            }
            if (!usuario.isActivo()) {
                JOptionPane.showMessageDialog(null, "Usuario inactivo");
                return;
            }
            Venta v = new Venta(id, servicio, usuario);
            raiz = insertar(raiz, v);
            JOptionPane.showMessageDialog(null, "Venta registrada");
        }

        public void mostrar(NodoVenta r, StringBuilder texto) {
            if (r != null) {
                mostrar(r.izq, texto);

                texto.append("ID: ").append(r.dato.id)
                        .append("\nServicio: ").append(r.dato.servicio.descripcion)
                        .append("\nUsuario: ").append(r.dato.usuario.getUsuario())
                        .append("\nMonto: ₡").append(r.dato.monto)
                        .append("\n-------------------\n");
                mostrar(r.der, texto);
            }
        }

        public void mostrarVentas() {
            if (raiz == null) {
                JOptionPane.showMessageDialog(null, "No hay ventas");
                return;
            }
            StringBuilder texto = new StringBuilder("===Ventas===");
            mostrar(raiz, texto);
            JOptionPane.showMessageDialog(null, texto.toString());
        }

        public NodoVenta buscar(NodoVenta r, int id) {
            if (r == null || r.dato.id == id) {
                return r;
            }

            if (id < r.dato.id) {
                return buscar(r.izq, id);
            }
            return buscar(r.der, id);
        }

        public void modificar() {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID a modificar:"));

            NodoVenta nodo = buscar(raiz, id);

            if (nodo == null) {
                JOptionPane.showMessageDialog(null, "No existe");
                return;
            }
            String nuevo = JOptionPane.showInputDialog("Nuevo servicio:");
            nodo.dato.servicio.descripcion = nuevo;

            JOptionPane.showMessageDialog(null, "Modificado");
            
        }

        public NodoVenta eliminar(NodoVenta r, int id) {
            if (r == null) return null;
            if (id < r.dato.id) {
                r.izq = eliminar(r.izq, id);
            } else if (id > r.dato.id) {
                r.der = eliminar(r.der, id);
} else {
       
        if (r.izq == null) return r.der;
        if (r.der == null) return r.izq;
                NodoVenta min = minimo(r.der);
                r.dato = min.dato;
                r.der = eliminar(r.der, min.dato.id);
            }
            return r;

        }
public void cancelar() {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID a cancelar:"));
            raiz = eliminar(raiz, id);
            JOptionPane.showMessageDialog(null, "Venta eliminada");
}
        public NodoVenta minimo(NodoVenta r) {
            while (r.izq != null) {
                r = r.izq;
            }
            return r;
        }

        
        }
    }

