package object.weapons;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class fireAxe extends SuperObject {

    public fireAxe(){
        name ="Fire Axe";//weapon name
        type = type_axe;//type
        collision = true;
        worth = 36.5;
        attack =2;
        try{
            BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/weapons/fireAxe.png")));
            image = scaleImage(originalImage, 3.5, 3.5); // Scale to half of its original size as an example
            description="["+name+"]\nNot For Babies!";
        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
    }
}
