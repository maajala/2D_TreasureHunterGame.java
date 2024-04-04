package lost_Items;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Diamond extends SuperObject {

    public Diamond() {

        collision = true;
        name = "Diamond"; // for door object
        worth =45;
        type = type_consumable;
        try {
            BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/lost/Diamond.png")));
            image = scaleImage(originalImage,0.35,0.35);
            description="["+name+"]\nLost Item";
            //image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/lost/Diamond.png")));
        } catch (IOException e) {
            e.printStackTrace(); //catches error if found
        }

    }
}
