package org.example;

public class Main {
    public static void main(String[] args) {
        DistribucionBolasGUI gui = new DistribucionBolasGUI();
        Fabrica fabrica = new Fabrica(gui);
        fabrica.iniciarProduccion();
    }
}