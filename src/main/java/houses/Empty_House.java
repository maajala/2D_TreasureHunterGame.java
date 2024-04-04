package houses;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Empty_House extends SuperObject {
    public Empty_House() {
        name = "Empty House";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/houses/emptyHouse.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
