package org.example;

public class Utilidades {
    public static int numAzar(int max) {
        return (int) (Math.random() * max + 1);
    }

    public static void esperarTiempoAzar(int max) {
        try {
            Thread.sleep((int) (Math.random() * max + 1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}