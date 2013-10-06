/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Thy
 */
public class Decodificacao {

    public static void main(String[] args) {

        File file = new File("E:\\2.bmp");

        try {
            BufferedImage imagem = ImageIO.read(file);

            int widht = imagem.getWidth(); //linha (varia��o X)
            int height = imagem.getHeight(); // coluna (varia��o Y)


            for (int i = 0; i < height; i++) {

                for (int j = 1; j < widht - 1; j++) {

                    int rgbAnterior = imagem.getRGB(j - 1, i);
                    int rgbPixel = imagem.getRGB(j, i);
                    int rgbPosterior = imagem.getRGB(j + 1, i);

                    Color corAnterior = new Color(rgbAnterior);
                    Color corPixel = new Color(rgbPixel);
                    Color corPosterior = new Color(rgbPosterior);


                    //media dos verdes 
                    int mediaVerdes = (corAnterior.getGreen() + corPosterior.getGreen()) / 2;

                    //media dos azul 
                    int mediaAzul = (corAnterior.getBlue() + corPosterior.getBlue()) / 2;

                    int valorCompararVerde;
                    int valorCompararAzul;

                    if (mediaVerdes > 128) {
                        valorCompararVerde = mediaVerdes - 20;
                    } else {
                        valorCompararVerde = mediaVerdes + 20;
                    }

                    if (mediaAzul > 128) {
                        valorCompararAzul = mediaAzul - 20;
                    } else {
                        valorCompararAzul = mediaAzul + 20;
                    }



                    if (corPixel.getGreen() == valorCompararVerde && corPixel.getBlue() == valorCompararAzul) {

                        //media dos verdes 
                        int mediaRed = (corAnterior.getRed() + corPosterior.getRed()) / 2;

                        int valorRedComMedia = corPixel.getRed();
                        
                        int numero;

                        if (mediaRed > 128) {
                            numero = mediaRed - valorRedComMedia;

                        } else {
                            numero = valorRedComMedia - mediaRed;

                        }
                        numero += 65;

                        char letra = (char) numero;

                        System.out.print(letra);

                    }

                }


            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
