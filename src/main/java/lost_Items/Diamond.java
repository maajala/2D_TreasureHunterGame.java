package lost_Items;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Diamond extends SuperObject {

    public Diamond() {

        collision = true;
        name = "Diamond"; // for door object
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/lost/Diamond.png")));
        } catch (IOException e) {
            e.printStackTrace(); //catches error if found
        }

    }
}
