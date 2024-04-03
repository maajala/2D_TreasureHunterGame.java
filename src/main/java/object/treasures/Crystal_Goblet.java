package object.treasures;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Crystal_Goblet extends SuperObject {
    public Crystal_Goblet(){

        name ="Crystal Goblet"; // for chest object
        worth = 45.5;// worth of a treasure
        type = type_consumable;
        try{
            BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/crystal.png")));
            image = scaleImage(originalImage, 3.5, 3.5); // Scale to half of its original size as an example
           // image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/crystal.png")));
            description="["+name+"]\nA Treasure";
        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
        collision = true;
    }
}
