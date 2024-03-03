package object.treasures;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class WoodenS extends SuperObject {
    public WoodenS(){

        name ="TreasureW"; // for chest object
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/shieldW.png")));
        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
    }
}
