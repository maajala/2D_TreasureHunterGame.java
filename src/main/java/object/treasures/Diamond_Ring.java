package object.treasures;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Diamond_Ring extends SuperObject {
    public Diamond_Ring(){

        name ="Diamond Ring"; // for chest object
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/diamondRing.png")));
        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
    }
}
