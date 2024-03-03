package object.treasures;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Paladin_Shield extends SuperObject {
    public Paladin_Shield(){

        name ="Paladin Shield"; // for chest object
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/shieldB.png")));
        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
    }
}
