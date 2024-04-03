package object.treasures;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Paladin_Shield extends SuperObject {
    public Paladin_Shield(){

        collision = true;
        type = type_shield;// type of object
        worth = 25.5;//price
        defence =2; // defence power
        name ="Paladin Shield"; // for chest object
        try{
            BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/shieldB.png")));
            image = scaleImage(originalImage, 3.5, 3.5); // Scale to half of its original size as an example
            description="["+name+"]\nUsed in Battles";
            //image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/shieldB.png")));
        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
    }
}
