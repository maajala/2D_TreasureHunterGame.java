package houses;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Trap extends SuperObject {
    public Trap() {
        name = "Trap";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/red.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
