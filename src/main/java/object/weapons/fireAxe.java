package object.weapons;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class fireAxe extends SuperObject {

    public fireAxe(){
        name ="Fire Axe";//weapon name
        collision = true;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/weapons/fireAxe.png")));
        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
    }
}
