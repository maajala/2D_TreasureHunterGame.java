package lost_Items;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class crystalRose extends SuperObject {

    public crystalRose() {

        collision = true;
        name = "Crystal Rose"; // for door object
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/lost/crystalRose.png")));
        } catch (IOException e) {
            e.printStackTrace(); //catches error if found
        }

    }
}
