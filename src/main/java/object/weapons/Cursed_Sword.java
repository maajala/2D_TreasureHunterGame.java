package object.weapons;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Cursed_Sword extends SuperObject {
    public Cursed_Sword(){
        collision = true;
        name ="Cursed Sword";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/weapons/cursedSword.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
