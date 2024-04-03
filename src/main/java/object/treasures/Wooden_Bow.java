package object.treasures;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Wooden_Bow extends SuperObject {
    public Wooden_Bow(){

        name ="Wooden Bow";
        type = type_bow;
        attack =1;
        worth = 14.25;
        try {
            BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/woodenBow.png")));
            image = scaleImage(originalImage, 0.05, 0.05); // Scale to half of its original size as an example
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/woodenBow.png")));
            description="["+name+"]\nUsed in Battles";
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
