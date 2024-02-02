package Fronteira;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;;



public class Leitura {


    public static BufferedImage leitor(String path){
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo de imagem " + path);
            return null;
        }
    }
    
}
