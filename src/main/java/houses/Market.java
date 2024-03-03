package houses;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Market extends SuperObject {
    public static int count;

    public Market(){
        count++;//counter for how many markets will be created
        name = "Market"+count;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/market.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
