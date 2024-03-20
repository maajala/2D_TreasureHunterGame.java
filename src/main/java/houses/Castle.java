package houses;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Castle extends SuperObject {
    public Castle() {
        name = "Castle";
        collision = true;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/houses/castle.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
