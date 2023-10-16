package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Fabrica {
    private Componente[] buffer;
    private int[] contadoresEstaciones;
    private DistribucionBolasGUI gui;
    private int bufferIndex;
    private ExecutorService executorService;

    public Fabrica(DistribucionBolasGUI gui) {
        buffer = new Componente[10];
        contadoresEstaciones = new int[10];
        this.gui = gui;
        bufferIndex = 0;
        executorService = Executors.newFixedThreadPool(10); // Crear un pool de 10 hilos
    }

    public Componente producirComponente(int estacion) {
        int componenteId = Utilidades.numAzar(10);
        Componente componente = new Componente(componenteId, estacion);
        synchronized (this) {
            buffer[bufferIndex] = componente;
            bufferIndex = (bufferIndex + 1) % buffer.length;
        }
        return componente;
    }

    public void ensamblarComponente(Componente componente) {
        synchronized (this) {
            int contenedor = componente.getId() % 10;
            contadoresEstaciones[contenedor]++;
        }
        actualizarGUI();
    }

    private void actualizarGUI() {
        // Llama al método actualizarHistograma() en la instancia de DistribucionBolasGUI
        gui.actualizarHistograma(contadoresEstaciones);
    }

    public synchronized Componente tomarComponente() throws InterruptedException {
        while (buffer[bufferIndex] == null) {
            wait();
        }
        Componente componente = buffer[bufferIndex];
        buffer[bufferIndex] = null;
        bufferIndex = (bufferIndex + 1) % buffer.length;
        return componente;
    }

    public synchronized void terminarComponente(Componente componente) {
        notifyAll();
    }

    public void iniciarProduccion() {
        for (int i = 0; i < 10; i++) {
            Runnable trabajadorEstacion = new TrabajadorEstacion(i, this);
            executorService.execute(trabajadorEstacion);
        }

        Runnable trabajadorLinea = new TrabajadorLineaEnsamble(this);
        executorService.execute(trabajadorLinea);

        // Esperar a que todos los trabajadores terminen
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            Thread.yield();
        }
    }
}