package object.weapons;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class NormalSword extends SuperObject {

    public NormalSword(){
        name ="Sword";//weapon name
        collision = true;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/weapons/sword_normal.png")));
        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
    }
}
