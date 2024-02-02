package Fronteira;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Janela {
    private BufferedImage imagem;


    public Janela(BufferedImage imagem) {
        this.imagem = imagem;
    }


    public void exibir(){
        JFrame jf = new JFrame("Imagem");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                   super.paintComponent(g);
                   g.drawImage(imagem, 0, 0, null);
            }
        };

        panel.setPreferredSize(new Dimension(imagem.getWidth(), imagem.getHeight()));
        jf.getContentPane().add(panel);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

    
}
