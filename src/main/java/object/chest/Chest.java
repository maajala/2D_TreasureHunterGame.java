package object.chest;

import com.example.game2d.GamePanel;
import lost_Items.Badge;
import lost_Items.Diamond;
import lost_Items.crystalRose;
import lost_Items.moneyBag;
import object.SuperObject;
import object.treasures.*;
import object.weapons.Cursed_Sword;
import object.weapons.NormalSword;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Chest extends SuperObject {

    public ArrayList<SuperObject> inventory = new ArrayList<>();
    public ArrayList<SuperObject> inventory2 = new ArrayList<>();
    public static Random random = new Random(); // Static if you want a single instance shared among all Chests
    public int choose = random.nextInt(1)+1; // Assuming you want a value of 0 or 1
    public int version;

    public Chest(){

        name = "Chest";
                //what is inside the chest
//        if(version ==0){
            inventory.add(new moneyBag());
            inventory.add(new Diamond());
            inventory.add(new Crystal_Goblet());
            inventory.add(new crystalRose());
            inventory.add(new Golden_Goblet());
//        }
//        if(version == 1){
//            inventory.add(new moneyBag());
//            inventory.add(new moneyBag());
//            inventory.add(new NormalSword());
//            inventory.add(new Paladin_Shield());
//            inventory.add(new Golden_Goblet());
//        }


        try{
            BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
            image = scaleImage(originalImage,3.5,3.5);
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest_opened.png")));


        }catch (IOException e){
            e.printStackTrace(); //catches error if found
        }
        collision = true;
    }
}
