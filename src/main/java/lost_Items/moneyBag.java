package lost_Items;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class moneyBag extends SuperObject {

    public moneyBag() {

        collision = true;
        name = "Money Bag"; // for door object
        type = type_consumable;
        try {
            BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/lost/moneyBag.png")));
            image = scaleImage(originalImage,3.5,3.5);
            description="["+name+"]\nLost Item";
//            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/lost/moneyBag.png")));
        } catch (IOException e) {
            e.printStackTrace(); //catches error if found
        }

    }
}
