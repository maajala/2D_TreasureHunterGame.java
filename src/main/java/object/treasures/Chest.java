package object.treasures;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Chest extends SuperObject {
    public Chest(){

        name ="Chest"; // for chest object
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
        collision = true;
    }
}
