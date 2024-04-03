package object.treasures;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Golden_Goblet extends SuperObject {
    public Golden_Goblet(){

        name = "Golden Goblet";
        worth = 30.25;
        type = type_consumable;
        try {
            BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/goldenGoblet.png")));
            image = scaleImage(originalImage, 0.034, 0.034); // Scale to half of its original size as an example
            description="["+name+"]\nA Treasure";
           // image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/goldenGoblet.png")));
        }catch (IOException e){
            e.printStackTrace(); //catches error if found

        }
        collision = true;
    }
}
