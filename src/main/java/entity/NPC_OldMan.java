package entity;

import com.example.game2d.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class NPC_OldMan extends Entity{

    public int actionCounter;
    public NPC_OldMan(GamePanel gp) throws IOException {
        super(gp);

        direction = "left";
        speed = 1;
        getImage();
    }
    // to get image of player
    public void getImage() throws IOException {
        try {// snap of every image of the player
            up1 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/NPC/oldman_up_1.png"))));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/NPC/oldman_up_2.png")));


            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/NPC/oldman_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/NPC/oldman_down_2.png")));

            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/NPC/oldman_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/NPC/oldman_left_2.png")));

            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/NPC/oldman_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/NPC/oldman_right_2.png")));


        }
        catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error loading player images", e);

        }

    }
    public void setAction()
    {
        // setting an interval for the npc to make it more logical
        actionCounter++;
        if(actionCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i <=25)
                direction = "up";
            else if(i >25 && i<=50)
                direction = "down";
            else if(i>50 && i<=75)
                direction = "left";
           else  if(i>75 && i<= 100)
                direction = "right";

            actionCounter =0;
        }

    }
}
