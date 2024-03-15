package monster;

import com.example.game2d.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

//Create Slime
public class MON_GreenSlime extends Entity {

    public int actionCounter;
    public MON_GreenSlime(GamePanel gp) throws IOException {
        super(gp);
        // each npc needs life and speed with name
        name = "Slime Green";// name for identity
        speed = 1;//Speed for movement
        maxLife = 4;//Life For the Monster
        direction = "left";

        solidArea.x =3;// here it should be less than npc logically
        solidArea.y =18;// the cut for solid area with x and y
        solidArea.width = 42;//width of monster
        solidArea.height=30;//height of the monster
        //as mentioned we need default x and y to reset
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }


    public void getImage() throws IOException
    {
        try {// snap of every image of the player
            up1 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/objects/greenSlime_down_1.png"))));

            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/greenSlime_down_2.png")));


            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/greenSlime_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/greenSlime_down_2.png")));

            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/greenSlime_down_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/greenSlime_down_2.png")));

            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/greenSlime_down_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/greenSlime_down_2.png")));


        }
        catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error loading player images", e);

        }

    }

    //Setting Monster Behaviour
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
