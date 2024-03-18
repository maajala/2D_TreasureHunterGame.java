package lost_Items;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class moneyBag extends SuperObject {

    public moneyBag() {

        collision = true;
        name = "Money Bag"; // for door object
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/lost/moneyBag.png")));
        } catch (IOException e) {
            e.printStackTrace(); //catches error if found
        }

    }
}
