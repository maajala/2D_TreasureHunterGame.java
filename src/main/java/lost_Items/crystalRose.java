package lost_Items;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class crystalRose extends SuperObject {

    public crystalRose() {

        collision = true;
        name = "Crystal Rose"; // for door object
        type = type_consumable;
        try {
            BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/lost/crystalRose.png")));
            image = scaleImage(originalImage,3.5,3.5);
            description="["+name+"]\nLost Item";
//            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/lost/crystalRose.png")));
        } catch (IOException e) {
            e.printStackTrace(); //catches error if found
        }

    }
}
