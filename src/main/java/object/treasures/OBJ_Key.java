package object.treasures;

import com.example.game2d.GamePanel;
import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Key extends SuperObject {
    // creat a constructor for this class

    public OBJ_Key (){


        name ="Key"; // for key object
        type = type_consumable;
        try{
            BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
            image = scaleImage(originalImage, 3.5, 3.5); // Scale to half of its original size as an example
            description="["+name+"]\nUsed to Open Castle Door";
        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
        collision =true;
    }
}
