/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package criptografia.imagem.impl;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Daniel
 *
 */
public class Decodificacao {

    public String decodificacao(String origem) {

        File file = new File(origem);
        String resultado = "";
        int i = -1, j = -1;
        try {
            BufferedImage imagem = ImageIO.read(file);

            int widht = imagem.getWidth(); //linha (varia��o X)
            int height = imagem.getHeight(); // coluna (varia��o Y)
            int tamanhoImagem = widht * height;

            

            for (int ifor = 1; ifor < tamanhoImagem; ifor += 113) {

                i = (ifor / widht);
                j = ifor - (widht * i);
                int rgbAnterior = imagem.getRGB(j - 1, i);
                int rgbPixel = imagem.getRGB(j, i);
                int rgbPosterior = imagem.getRGB(j + 1, i);

                Color corAnterior = new Color(rgbAnterior);
                Color corPixel = new Color(rgbPixel);
                Color corPosterior = new Color(rgbPosterior);


                //media dos vermelho 
                int mediaRed = (corAnterior.getRed() + corPosterior.getRed()) / 2;

                int valorRedComMedia = corPixel.getRed();


                int numero;

                //media dos vermelho 
                if (mediaRed > 128) {
                    numero = mediaRed - valorRedComMedia;

                } else {
                    numero = valorRedComMedia - mediaRed;

                }
                numero += 65;

                char letra = (char) numero;

                resultado += letra;

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(i + " - " + j);
        }

        return resultado;
    }
}
