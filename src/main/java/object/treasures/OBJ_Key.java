package object.treasures;

import com.example.game2d.GamePanel;
import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Key extends SuperObject {
    // creat a constructor for this class

    public OBJ_Key (){


        name ="Key"; // for key object
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
        collision =true;
    }
}
