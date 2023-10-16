package org.example;

public class TrabajadorLineaEnsamble implements Runnable {
    private Fabrica fabrica;

    public TrabajadorLineaEnsamble(Fabrica fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Componente componente = fabrica.tomarComponente();
                Thread.sleep(componente.getId() * 100);
                fabrica.terminarComponente(componente);
                System.out.println("Ensamblador ensambló componente " + componente.getId() + " de estación " + componente.getEstacion());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}