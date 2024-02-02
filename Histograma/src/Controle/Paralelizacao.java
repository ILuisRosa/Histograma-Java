package Controle;


import Fronteira.Leitura;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class Paralelizacao implements Runnable {
    private File imageFile;
    private long startTime;
    
    public Paralelizacao(File imageFile, long startTime) {
        this.imageFile = imageFile;
        this.startTime = startTime;
    }
    
    @Override
    public void run() {
        BufferedImage imagem = Leitura.leitor(imageFile.getAbsolutePath());
        
        int[] vermelho = calculaHistograma(imagem, 0);
        int[] verde = calculaHistograma(imagem, 1);
        int[] azul = calculaHistograma(imagem, 2);
        
        salvarResultado(imageFile.getName(), vermelho, verde, azul, imagem);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Processamento concluído para o arquivo: " + imageFile.getName() + " - Tempo total: " + totalTime + "ms");
    }

    private int[] calculaHistograma(BufferedImage imagem, int aux) {
        int[] histograma = new int[256];
        int width = imagem.getWidth();
        int height = imagem.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color corPixel = new Color(imagem.getRGB(i, j));
                int valorAux;
                switch (aux) {
                    case 0:
                        valorAux = corPixel.getRed();
                        break;
                    case 1:
                        valorAux = corPixel.getGreen();
                        break;
                    case 2:
                        valorAux = corPixel.getBlue();
                        break;
                    default:
                        valorAux = 0;
                        break;
                }
                histograma[valorAux]++;
            }
        }

        return histograma;
    }

    private void salvarResultado(String arquivo, int[] vermelho, int[] verde, int[] azul, BufferedImage imagem) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Histograma.txt", true))) {
            writer.write("Arquivo: " + arquivo);
            writer.newLine();

            
            writer.write("Vermelho:");
            writer.newLine();
            for (int i = 0; i < 256; i++) {
                if (vermelho[i] != 0) {
                    double intensidade = (double) vermelho[i] / (double) (imagem.getWidth() * imagem.getHeight()) * 10.0;
                    writer.write("Valor " + i + ": " + String.format("%.2f", intensidade));
                    writer.newLine();
                }
            }
            writer.newLine();

            
            writer.write("Verde:");
            writer.newLine();
            for (int i = 0; i < 256; i++) {
                if (verde[i] != 0) {
                    double intensidade = (double) verde[i] / (double) (imagem.getWidth() * imagem.getHeight()) * 10.0;
                    writer.write("Valor " + i + ": " + String.format("%.2f", intensidade));
                    writer.newLine();
                }
            }
            writer.newLine();

            
            writer.write("Azul:");
            writer.newLine();
            for (int i = 0; i < 256; i++) {
                if (azul[i] != 0) {
                    double intensidade = (double) azul[i] / (double) (imagem.getWidth() * imagem.getHeight()) * 10.0;
                    writer.write("Valor " + i + ": " + String.format("%.2f", intensidade));
                    writer.newLine();
                }
            }
            writer.newLine();

            writer.flush();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



