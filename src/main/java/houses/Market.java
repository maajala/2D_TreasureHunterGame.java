package houses;

import object.SuperObject;
import object.treasures.Jewel_Sword;
import object.treasures.Paladin_Shield;
import object.treasures.WoodenS;
import object.treasures.Wooden_Bow;
import object.weapons.Axe;
import object.weapons.Cursed_Sword;
import object.weapons.NormalSword;
import object.weapons.fireAxe;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Market extends SuperObject {
    public static int count;
    public ArrayList<SuperObject> marketItems = new ArrayList<>();

    public Market(){
        collision = true;
        name = "Market";
        //ADD WEAPONS and SHIELDS TO THE MARKET
        marketItems.add(new fireAxe());
        marketItems.add(new Paladin_Shield());
        marketItems.add(new Cursed_Sword());
        marketItems.add(new NormalSword());
        marketItems.add(new WoodenS());
        marketItems.add(new Axe());
        marketItems.add(new Jewel_Sword());
        marketItems.add(new Wooden_Bow());

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/houses/market.png")));
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
