package houses;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Market extends SuperObject {
    public static int count;

    public Market(){
        name = "Market";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/orange.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
