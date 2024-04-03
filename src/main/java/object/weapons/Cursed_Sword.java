package object.weapons;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Cursed_Sword extends SuperObject {
    public Cursed_Sword(){
        collision = true;
        name ="Cursed Sword";
        type = type_sword;
        attack =3;
        try{
            BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/weapons/cursedSword.png")));
            image = scaleImage(originalImage, 3.5, 3.5); // Scale to half of its original size as an example
            description="["+name+"]\nCurse The Enemy";

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
