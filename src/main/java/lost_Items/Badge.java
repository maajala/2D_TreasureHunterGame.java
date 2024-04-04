package lost_Items;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Badge extends SuperObject {

    public Badge() {

        collision = true;
        name = "Badge"; // for door object
        try {
            BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/lost/badge.png")));
            image = scaleImage(originalImage,1.5,1.5);
        } catch (IOException e) {
            e.printStackTrace(); //catches error if found
        }

    }
}
