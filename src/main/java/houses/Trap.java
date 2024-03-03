package houses;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Trap extends SuperObject {
    public Trap() {
        name = "Trap House";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/trap.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
