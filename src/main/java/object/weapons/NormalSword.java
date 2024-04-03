package object.weapons;

import com.example.game2d.GamePanel;
import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class NormalSword extends SuperObject {

    public NormalSword(){
        name ="Normal Sword";//weapon name
        type = type_sword;
        attack =1;
        collision = true;
        try{
            BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/weapons/sword_normal.png")));
            image = scaleImage(originalImage, 3.5, 3.5); // Scale to half of its original size as an example
            description="["+name+"]\nNormal ";
        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
    }
}
