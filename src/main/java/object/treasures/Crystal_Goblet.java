package object.treasures;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Crystal_Goblet extends SuperObject {
    public Crystal_Goblet(){

        name ="Crystal Goblet"; // for chest object
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/crystal.png")));
        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
        collision = true;
    }
}
