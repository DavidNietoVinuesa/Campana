package org.example;

import javax.swing.*;
import java.awt.*;

public class DistribucionBolasGUI extends JFrame {
    private int[] contenedores;
    private JPanel histogramPanel;

    public DistribucionBolasGUI() {
        contenedores = new int[10]; // Número predeterminado de contenedores
        setTitle("Distribución de Bolas");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        histogramPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int contenedorWidth = getWidth() / contenedores.length;
                int maxHeight = getHeight();
                for (int i = 0; i < contenedores.length; i++) {
                    int height = contenedores[i] * maxHeight / 10;
                    g.fillRect(i * contenedorWidth, maxHeight - height, contenedorWidth, height);
                }
            }
        };

        add(histogramPanel);
        setVisible(true);
    }

    public void actualizarHistograma(int[] nuevaDistribucion) {
        contenedores = nuevaDistribucion;
        histogramPanel.repaint();
    }
}
