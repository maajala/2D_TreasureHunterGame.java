package object.treasures;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Diamond_Ring extends SuperObject {
    public Diamond_Ring(){


        name ="Diamond Ring"; // for chest object
        worth = 40.25;
        type = type_consumable;
        try{
            BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/diamondRing.png")));
            image = scaleImage(originalImage, 0.12, 0.115); // Scale to half of its original size as an example
            description="["+name+"]\nA Treasure";
          //  image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/diamondRing.png")));
        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
        collision = true;
    }
}
