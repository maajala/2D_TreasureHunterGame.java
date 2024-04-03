package object.treasures;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Jewel_Sword extends SuperObject {
    public Jewel_Sword(){


        name ="Jewel Sword"; // for chest object
        type = type_sword;
        worth = 27.5;//worth of money
        attack =2;
        try{
            BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/jewelSword.png")));
            image = scaleImage(originalImage, 0.06, 0.06); // Scale to half of its original size as an example
            description="["+name+"]\nUsed in Battles";
            //image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/jewelSword.png")));
        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
        collision = true;
    }
}
