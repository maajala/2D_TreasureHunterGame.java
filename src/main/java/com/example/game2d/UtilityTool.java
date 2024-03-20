package com.example.game2d;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {
    public BufferedImage scaleImage(BufferedImage original, int width, int height){

        BufferedImage scaledImage = new BufferedImage(width,height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();//used to draw into this bufferedImage
        g2.drawImage(original,0,0,width,height,null);
        g2.dispose();

        return scaledImage;

    }
}
