/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package criptografia.imagem.impl;

import criptografia.imagem.gui.TelaCriptografia;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Thy
 */
public class Codificacao {

    public boolean codificarImagem(String origem,String destino,String texto) {
            
        
        File file = new File(origem);

        
       

        try {
            BufferedImage imagem = ImageIO.read(file);

            int widht = imagem.getWidth(); //linha (varia��o X)
            int height = imagem.getHeight(); // coluna (varia��o Y)

            int quantidadePixels = widht * height;
            int quantidadeLetras = texto.length();

            List<Integer> numerosAleatorios = new ArrayList();

            Random r = new Random();
            do {
                int novoNumero = r.nextInt(quantidadePixels);
                int i = novoNumero / widht;
                int j = novoNumero - i * widht;

                if (j != 0 && j != widht) {
                    if (!numerosAleatorios.contains(novoNumero)) {
                        numerosAleatorios.add(novoNumero);
                    }
                }

            } while (numerosAleatorios.size() != quantidadeLetras);

            Collections.sort(numerosAleatorios);

            for (int ifor = 0; ifor < numerosAleatorios.size(); ifor++) {

                Integer numero = numerosAleatorios.get(ifor);

                int i = numero / widht;
                int j = numero - i * widht;


                int rgbAnterior = imagem.getRGB(j - 1, i);
                int rgbAlvo = imagem.getRGB(j, i);
                int rgbPosterior = imagem.getRGB(j + 1, i);

                Color corAnterior = new Color(rgbAnterior);
                Color corAlvo = new Color(rgbAlvo);
                Color corPosterior = new Color(rgbPosterior);


                //Dados do alvo
                int red = corAlvo.getRed();
                int green = corAlvo.getGreen();
                int blue = corAlvo.getBlue();
                int alpha = corAlvo.getAlpha();


                //media dos vermelhos 
                int mediaVermelhos = (corAnterior.getRed() + corPosterior.getRed()) / 2;

                //media dos verdes 
                int mediaVerdes = (corAnterior.getGreen() + corPosterior.getGreen()) / 2;

                //media dos azul 
                int mediaAzul = (corAnterior.getBlue() + corPosterior.getBlue()) / 2;

                if (mediaVermelhos > 128) {
                    red = mediaVermelhos - (texto.charAt(ifor) - 65);

                } else {
                    red = mediaVermelhos + (texto.charAt(ifor) - 65);
                }


                if (mediaVerdes > 128) {
                    green = mediaVerdes - 20;

                } else {
                    green = mediaVerdes + 20;
                }

                if (mediaAzul > 128) {
                    blue = mediaAzul - 20;

                } else {
                    blue = mediaAzul + 20;
                }

                Color corAlterada = new Color(red, green, blue, alpha);
                imagem.setRGB(j, i, corAlterada.getRGB());
            }

            ImageIO.write(imagem, "BMP", new File(destino));
            return true;

        } catch (IOException e) {
            e.printStackTrace();return false;
        }
    }
}
