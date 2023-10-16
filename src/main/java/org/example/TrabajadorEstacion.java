package org.example;

public class TrabajadorEstacion implements Runnable {
    private int estacion;
    private Fabrica fabrica;

    public TrabajadorEstacion(int estacion, Fabrica fabrica) {
        this.estacion = estacion;
        this.fabrica = fabrica;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Componente componente = fabrica.producirComponente(estacion);
            try {
                Thread.sleep(componente.getId() * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fabrica.ensamblarComponente(componente);
        }
    }
}