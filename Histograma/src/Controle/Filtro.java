package Controle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Filtro {


    public static BufferedImage criarImagememBranco(){
        int width = 300;
        int height = 200;

        BufferedImage imagem =  new BufferedImage(width, height,BufferedImage.TYPE_INT_BGR);


        Graphics2D graphic = imagem.createGraphics();
    
        graphic.setColor(Color.RED);
        graphic.fillRect(0, 0, width, height);
        graphic.dispose();

    return imagem;
    }



    public static BufferedImage escalaCinza(BufferedImage imagem){
        BufferedImage imagemCinza = imagem;

        for(int i = 0; i < imagem.getWidth(); i++){
            for(int j = 0; j < imagem.getHeight(); j++){
                    Color cor = new Color(imagem.getRGB(i, j));
                    
                int vermelho = cor.getRed();
                int verde = cor.getGreen();
                int azul = cor.getBlue();

                int cinza = (vermelho + verde + azul) / 3;

                Color corCinza = new Color(cinza, cinza, cinza);

                imagemCinza.setRGB(i, j, corCinza.getRGB());

            }

        }

        return imagemCinza;
    }

    public static BufferedImage inverterCores(BufferedImage imagem){
        BufferedImage imagemInvertida = imagem;

        for(int i = 0; i < imagem.getWidth(); i++){
            for(int j = 0; j < imagem.getHeight(); j++){
                    Color cor = new Color(imagem.getRGB(i, j));
                    
                int vermelho = 255 - cor.getRed();
                int verde = 255 - cor.getGreen();
                int azul = 255- cor.getBlue();

                Color corCinza = new Color(vermelho, verde, azul);

                imagemInvertida.setRGB(i, j, corCinza.getRGB());

            }

        }

        return imagemInvertida;
    }


    public static BufferedImage Binarizacao(BufferedImage imagem){
        BufferedImage imagemCinza = imagem;

        for(int i = 0; i < imagem.getWidth(); i++){
            for(int j = 0; j < imagem.getHeight(); j++){
                    Color cor = new Color(imagem.getRGB(i, j));
                    
                int vermelho = cor.getRed();
                int verde = cor.getGreen();
                int azul = cor.getBlue();

                int cinza = (vermelho + verde + azul) / 3;

               
                int novaCor;
                if(cinza > 126){
                    novaCor = 255;
                }else{
                    novaCor = 0;
                }
                
                Color novoPixel = new Color(novaCor, novaCor, novaCor);
                imagemCinza.setRGB(i, j, novoPixel.getRGB());
            }
        }
        return imagemCinza;
    }

    public static int[] histogramaRGB(BufferedImage imagem) {
    int width = imagem.getWidth();
    int height = imagem.getHeight();
    int[] histograma = new int[3]; 

        for (int i = 0; i < histograma.length; i++) {
        histograma[i] = 0;
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color pixelColor = new Color(imagem.getRGB(i, j));
                
                histograma[0] += pixelColor.getRed();
                histograma[1] += pixelColor.getGreen();
                histograma[2] += pixelColor.getBlue();
        }

        return histograma;
        }
    return histogramaRGB(imagem);
    }   
}



