package houses;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Trap extends SuperObject {
    public Trap() {
        name = "Trap";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/houses/burned.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
