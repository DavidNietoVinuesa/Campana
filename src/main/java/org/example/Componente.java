package org.example;

public class Componente {
    private int id;
    private int estacion;

    public Componente(int id, int estacion) {
        this.id = id;
        this.estacion = estacion;
    }

    public int getId() {
        return id;
    }

    public int getEstacion() {
        return estacion;
    }
}