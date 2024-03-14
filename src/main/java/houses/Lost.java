package houses;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Lost extends SuperObject {
    public Lost() {
        name = "Lost Items";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/blue.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
