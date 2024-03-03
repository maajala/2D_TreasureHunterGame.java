package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Door extends SuperObject{

    public OBJ_Door(){

        name ="Door"; // for door object
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
    }
}
