package object;

import com.example.game2d.GamePanel;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Heart extends SuperObject{
    public Heart() {
        collision = true;
        name = "Heart";
        try {
            BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_blank.png")));
            image = scaleImage(originalImage, 3.5, 3.5); // Scale to half of its original size as an example

            originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_half.png")));
            image2 = scaleImage(originalImage, 3.5,3.5 ); // Scale to half of its original size as an example

            originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_full.png")));
            image3 = scaleImage(originalImage, 3.5, 3.5); // Scale to half of its original size as an example
        } catch (IOException e) {
            e.printStackTrace(); // Catches error if found
        }
    }



}
