package com.mycompany.estructuradatos;

public class Usuarios {

    static class Nodo {
        
    
    private String nombreCompleto;
    private String usuario;
    private String contraseña;
    private boolean activo;
    private Nodo abajo;

    public Nodo(String nombreCompleto, String usuario, String contraseña, Nodo abajo, boolean activo) {
        this.nombreCompleto = nombreCompleto;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.abajo = abajo;
        this.activo = activo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
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

    public Pila getAbajo() {
        return abajo;
    }

    public void setAbajo(Pila abajo) {
        this.abajo = abajo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

   static class Pila {

        private Nodo cima;

        public Pila() {
            cima = null;
        }

        public void apilar(String nombreCompleto, String usuario, String contraseña, boolean activo) {
            Nodo nuevo = new Nodo(nombreCompleto, usuario, contraseña, activo, cima);
            cima = nuevo;
        }
    }
}
