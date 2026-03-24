package com.mycompany.estructuradatos;

import javax.swing.JOptionPane;

public class Usuarios {

    static class Nodo {

        private String nombre;
        private String usuario;
        private String contraseña;
        private boolean activo;
        private Nodo abajo;

        public Nodo(String nombre, String usuario, String contraseña, boolean activo, Nodo abajo) {
            this.nombre = nombre;
            this.usuario = usuario;
            this.contraseña = contraseña;
            this.abajo = abajo;
            this.activo = activo;
        }

        public String getNombreCompleto() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public String getContraseña() {
            return contraseña;
        }

        public void setContraseña(String contraseña) {
            this.contraseña = contraseña;
        }

        public Nodo getAbajo() {
            return abajo;
        }

        public void setAbajo(Nodo abajo) {
            this.abajo = abajo;
        }

        public boolean isActivo() {
            return activo;
        }

        public void setActivo(boolean activo) {
            this.activo = activo;
        }
    }

    static class Pila {

        private Nodo cima;

        public Pila() {
            cima = null;
        }

        public void apilar(String nombre, String usuario, String contraseña, boolean activo) {
            Nodo nuevo = new Nodo(nombre, usuario, contraseña, activo, cima);
            cima = nuevo;
        }   
        
            // registro antes de iniciar
        public void registro() {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre completo:");
            if (nombre == null) {
                return;
            }
            String usuario = JOptionPane.showInputDialog("Ingrese un usuario(NIckname):");
            if (usuario == null) {
                return;
            }
            String contraseña = JOptionPane.showInputDialog("Ingrese una contraseña:");
            if (contraseña == null) {
                return;
            }

            apilar(nombre, usuario, contraseña, true);
        }
        //LOGIN 
        public boolean login() {

            String user = JOptionPane.showInputDialog("Usuario:");
            if (user == null) {
                return true;
            }

            String password = JOptionPane.showInputDialog("Contraseña:");
            if (password == null) {
                return true;
            }

            Nodo actual = cima;

            while (actual != null) {

                if (actual.getUsuario().equals(user)
                        && actual.getContraseña().equals(password)
                        && actual.isActivo()) {

                    JOptionPane.showMessageDialog(null, "Bienvenido " + user);
                    return true;
                }

                actual = actual.getAbajo();
            }

            JOptionPane.showMessageDialog(null,
                    "Acceso Denegado");

            return false;
        }
        // Agregar usuarios
        public void agregar() {

            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre completo del usuario:");
            if (nombre == null) {
                return;
            }

            String usuario = JOptionPane.showInputDialog(null, "Ingrese un usuario(Nickname):");
            if (usuario == null) {
                return;
            }

            String contraseña = JOptionPane.showInputDialog(null, "Ingrese una contraseña:");
            if (contraseña == null) {
                return;
            }

            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea mantener activo el usuario?");
            if (opcion == JOptionPane.CLOSED_OPTION) {
                return;
            }

            boolean activo = (opcion == JOptionPane.YES_OPTION);

            apilar(nombre, usuario, contraseña, activo);

            JOptionPane.showMessageDialog(null, "Usuario agregado correctamente");

        }
            // inactivar usuarios
        public void inactivarUsuario() {
            String inactivar = JOptionPane.showInputDialog(null, "Ingrese el usuario que quiere desactivar:");
            if (inactivar == null) {
                return;
            }
            Nodo actual = cima;

            while (actual != null) {
                if (actual.usuario.equalsIgnoreCase(inactivar)) {
                    if (!actual.activo) {
                        JOptionPane.showMessageDialog(null, "El usuario introducido ya esta inactivo");
                        return;
                    }
                    actual.activo = false;
                    JOptionPane.showMessageDialog(null, "Usuario desactivado");
                    return;
                }
                actual = actual.abajo;
            }
            JOptionPane.showMessageDialog(null, "Usuario introducido no encontrado");
        }

       //Mostrar usuarios
        public void mostrar() {

            Nodo actual = cima;

            if (actual == null) {
                JOptionPane.showMessageDialog(null, "No hay usuarios registrados");
                return;
            }

            String texto = "======= USUARIOS =======\n\n";

            while (actual != null) {

                String estado;

                if (actual.activo) {
                    estado = "Activo";
                } else {
                    estado = "Inactivo";
                }

                texto += "-----------------\n"
                        + "Nombre: " + actual.nombre + "\n"
                        + "Usuario: " + actual.usuario + "\n"
                        + "Estado: " + estado + "\n"
                        + "-----------------\n\n";

                actual = actual.getAbajo();
            }

            JOptionPane.showMessageDialog(null, texto);
        }
        public Nodo buscarUsuario(String nombreUsuario){
            Nodo actual=cima;
            
            while(actual!=null){
                if(actual.getUsuario().equalsIgnoreCase(nombreUsuario)){
                    return actual;
                }
                actual=actual.getAbajo();
            }
            return null;
        }
    }
}

