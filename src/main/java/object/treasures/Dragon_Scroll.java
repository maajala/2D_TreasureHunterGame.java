package object.treasures;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Dragon_Scroll extends SuperObject {
    public Dragon_Scroll(){

        name ="Dragon Scroll"; // for chest object
        worth = 25.5;
        type = type_consumable;
        try{
            BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/dragonScroll.png")));
            image = scaleImage(originalImage, 0.07, 0.065); // Scale to half of its original size as an example
            description="["+name+"]\nA Treasure";
            //image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/dragonScroll.png")));
        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
        collision = true;
    }
}
