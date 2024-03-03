package object.treasures;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Wooden_Bow extends SuperObject {
    public Wooden_Bow(){
        name ="Wooden Bow";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/woodenBow.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
