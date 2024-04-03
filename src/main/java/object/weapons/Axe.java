package object.weapons;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Axe extends SuperObject {

    public Axe(){
        name ="Axe";//weapon name
        type = type_axe;
        collision = true;
        attack =2;
        try{
            BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/weapons/Axe.png")));
            image = scaleImage(originalImage, 3.5, 3.5); // Scale to half of its original size as an example
            description="["+name+"]\nCuts Sharp!";
        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
    }
}
