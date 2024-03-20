package object.treasures;

import com.example.game2d.GamePanel;
import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Chest extends SuperObject {

    public Chest(){

        name ="Chest"; // for chest object
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest_opened.png")));


        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
        collision = true;
    }
}
