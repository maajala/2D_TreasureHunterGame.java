package object.treasures;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Golden_Goblet extends SuperObject {
    public Golden_Goblet(){

        name = "Golden Goblet";
        worth = 30.25;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/treasures/goldenGoblet.png")));
        }catch (IOException e){
            e.printStackTrace(); //catches error if found

        }
        collision = true;
    }
}
