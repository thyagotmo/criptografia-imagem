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
 */
public class Codificacao {

    public boolean codificarImagem(String origem,String destino,String texto) {
            
        
        File file = new File(origem);

        
       

        try {
            BufferedImage imagem = ImageIO.read(file);

            int widht = imagem.getWidth(); //linha (varia��o X)
            int height = imagem.getHeight(); // coluna (varia��o Y)

            
            int quantidadeLetras = texto.length();
            int vezes=0;
            for(int ifor =1;vezes<quantidadeLetras;ifor+=113){

                
                int i = (ifor/widht);
                int j =  ifor - (widht *i);
                
                


                int rgbAnterior = imagem.getRGB(j - 1, i);
                int rgbAlvo = imagem.getRGB(j, i);
                int rgbPosterior = imagem.getRGB(j + 1, i);

                Color corAnterior = new Color(rgbAnterior);
                Color corAlvo = new Color(rgbAlvo);
                Color corPosterior = new Color(rgbPosterior);


                //Dados do alvo
                int red ;
                int green = corAlvo.getGreen();
                int blue = corAlvo.getBlue();
                int alpha = corAlvo.getAlpha();
                
                //media dos vermelhos 
                int mediaVermelhos = (corAnterior.getRed() + corPosterior.getRed()) / 2;

               
                if (mediaVermelhos > 128) {
                    red = mediaVermelhos - (texto.charAt(vezes) - 65);

                } else {
                    red = mediaVermelhos + (texto.charAt(vezes) - 65);
                }


            
                Color corAlterada = new Color(red, green, blue, alpha);
                imagem.setRGB(j, i, corAlterada.getRGB());
            vezes++;
            }
            ImageIO.write(imagem, "BMP", new File(destino));
            return true;}

         catch (IOException e) {
            e.printStackTrace();return false;
        }
    }
}
