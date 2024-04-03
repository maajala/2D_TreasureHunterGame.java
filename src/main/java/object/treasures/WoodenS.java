package object.treasures;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class WoodenS extends SuperObject {
    public WoodenS(){

        name ="Wooden Shield"; // for chest object
        type = type_shield;
        defence =1;
        try{
            BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/shieldW.png")));
            image = scaleImage(originalImage, 3.5, 3.5); // Scale to half of its original size as an example
            description="["+name+"]\nUsed in Battles";
          //  image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/shieldW.png")));
        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
        collision = true;
    }
}
