package entity;

import collision.CollisionChecker;
import com.example.game2d.GamePanel;
import com.example.game2d.KeyHandler2;
import com.example.game2d.Keyhandler;
import com.example.game2d.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class Player2 extends Entity{

    //we can delete it we have super in each constructor from Entity
    GamePanel gp;// use game panel created
    Keyhandler keyH2;

    public int screenX;// where we draw player on screen x-axis
    public int screenY; // and y-axis

    // Player Tools
    public int keyCountB=0;
    public double walletB=0;
    public int maxLife2;//starting health
    public int life2;//varying health
    public int powerB;
    public int hasWeapons;
    CollisionChecker collisionChecker;
    public boolean collisionWithPlayer1 = false;
    public boolean isTurn =false;

    Dice dice = new Dice();//calling the dice method
    public int steps = 0;//steps to move according to dice



    // constructor for player one
    public Player2 (GamePanel gp, Keyhandler keyH2, CollisionChecker collisionChecker) {
        super(gp);
        this.gp = gp;//we can delete
        this.keyH2 = keyH2;
        this.collisionChecker = collisionChecker;

        //Player Screen Position
        screenX = gp.player1.worldX *11; // sub by half to be in the center
        screenY = gp.player1.worldY;// half-way point of the screen


        //Solid Area Variables for player
        solidArea = new Rectangle();
        solidArea.x = 8;// changed from 0 to 8 after cut of collision
        solidArea.y = 16;// changed from 0 to 16 after cut // it is optional
        solidArea.width = 35;//changed from tileSize(48) to 32 as the desired cut
        solidArea.height= 35;;
        solidAreaDefaultX = solidArea.x;// default values helpful to retrieve
        solidAreaDefaultY = solidArea.y;// the default values when changing them later


        setDefaultValues();
        try {
            getPlayerImage();
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception or handle it in a way that makes sense for your application.
        }
    }
    public void setDefaultValues() {
        // same what we did in GamePanel for player position
        //worldX = gp.tileSize * 23;// starting point , player position in world map // was 23
        //worldY = gp.tileSize * 21;// was 21
        worldX = gp.player1.worldX *11;// this is for 10x10 map
        worldY = gp.player1.worldY;
        speed = 4;
        direction = "down"; // can be chosen any direction as default
        maxLife2=6;//max life to be reached
        life2 = maxLife2;// current life to be used
        // powerB
        powerB = 5;
    }


    // to get image of player // previous getPlayerImage
    public void getPlayerImage2() throws IOException{
        try {// snap of every image of the player
            up1 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_1.png"))));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_3.png")));
            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_4.png")));

            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_3.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_1.png")));
            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_4.png")));

            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_4.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_3.png")));
            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_2.png")));


            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_3.png")));
            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_4.png")));


        }
        catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error loading player images", e);

        }
    }


    // Another Fast Way to Draw or Call PLayers Image
    public void getPlayerImage() throws IOException{
        up1 = setup("player_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("player_up_2",gp.tileSize,gp.tileSize);
        up3 = setup("player_up_3",gp.tileSize,gp.tileSize);
        up4 = setup("player_up_4",gp.tileSize,gp.tileSize);

        down1=setup("player_down_3",gp.tileSize,gp.tileSize);
        down2=setup("player_down_2",gp.tileSize,gp.tileSize);
        down3=setup("player_down_1",gp.tileSize,gp.tileSize);
        down4=setup("player_down_4",gp.tileSize,gp.tileSize);

        right1=setup("player_right_1",gp.tileSize,gp.tileSize);
        right2=setup("player_right_2",gp.tileSize,gp.tileSize);
        right3=setup("player_right_3",gp.tileSize,gp.tileSize);
        right4=setup("player_right_4",gp.tileSize,gp.tileSize);

        left1=setup("player_left_1",gp.tileSize,gp.tileSize);
        left2=setup("player_left_4",gp.tileSize,gp.tileSize);
        left3=setup("player_left_3",gp.tileSize,gp.tileSize);
        left4=setup("player_left_2",gp.tileSize,gp.tileSize);
    }
    // Buffered IMage to scale it as tiles into game tile size screen
    public BufferedImage setup(String imageName , int width , int height){
        //instantiate Utility Tool class to scale
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/"+imageName+".png")));
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    //will be used for battle system
    public void getPlayerAttackImage2(){

        up1 = setup("player_up_1",gp.tileSize,gp.tileSize*2);
        up2 = setup("player_up_2",gp.tileSize,gp.tileSize*2);
        up3 = setup("player_up_3",gp.tileSize,gp.tileSize*2);
        up4 = setup("player_up_4",gp.tileSize,gp.tileSize*2);

        down1=setup("player_down_3",gp.tileSize,gp.tileSize*2);
        down2=setup("player_down_2",gp.tileSize,gp.tileSize*2);
        down3=setup("player_down_1",gp.tileSize,gp.tileSize*2);
        down4=setup("player_down_4",gp.tileSize,gp.tileSize*2);

        right1=setup("player_right_1",gp.tileSize*2,gp.tileSize);
        right2=setup("player_right_2",gp.tileSize*2,gp.tileSize);
        right3=setup("player_right_3",gp.tileSize*2,gp.tileSize);
        right4=setup("player_right_4",gp.tileSize*2,gp.tileSize);

        left1=setup("player_left_1",gp.tileSize*2,gp.tileSize);
        left2=setup("player_left_4",gp.tileSize*2,gp.tileSize);
        left3=setup("player_left_3",gp.tileSize*2,gp.tileSize);
        left4=setup("player_left_2",gp.tileSize*2,gp.tileSize);
    }

    // Update player position based on key input
    public void update() {


        collisionOn = false;

        // Potential new position
        int newWorldX = worldX;
        int newWorldY = worldY;

        if (keyH2.upPressed) {
            newWorldY -= speed;
            direction = "up";
        } else if (keyH2.downPressed) {
            newWorldY += speed;
            direction = "down";
        } else if (keyH2.leftPressed) {
            newWorldX -= speed;
            direction = "left";
        } else if (keyH2.rightPressed) {
            newWorldX += speed;
            direction = "right";
        }

        // Collision checking
        collisionChecker.checkTile(this);

        // If there is no collision, update the world position
        if (!collisionOn) {
            int objectIndex = collisionChecker.checkObject(this,true);
            if(objectIndex == 999)
            {
                    worldX = newWorldX;
                    worldY = newWorldY;

            }else {
                // Handle object collision event, e.g., pick up a key
                pickUpObject(objectIndex);
            }
        }

        // Then check collision with the other player using their current position and your new potential position
        collisionWithPlayer1 = collisionChecker.checkPlayerCollision(this, gp.player1);

        // If no collision with the environment or the other player, update position
        if (!collisionWithPlayer1) {

        }
        else {
            if(keyH2.enterPressed){
                attacking();
                gp.player1.life1 -=1 ;

                if(gp.player1.life1 == 0){
                    gp.ui.showMessage("Player2 won The Battle ");
                    takeMoneyFromOpponent(gp.player1);
                }
            }
            System.out.println("Player 2 hits PLayer 1 !");
            // Handle collision (e.g., stop movement, play sound, etc.)
        }
        screenX = worldX;
        screenY = worldY;

        // Update sprite animations
        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = (spriteNum % 4) + 1; // Cycle through sprite numbers 1 to 4
            spriteCounter = 0;
        }
        if (isTurn && (keyH2.upPressed || keyH2.downPressed || keyH2.leftPressed || keyH2.rightPressed)) {
            steps--; // Decrease the number of steps as the player moves
            if (steps <= 0) {
                isTurn = false; // End the player's turn when steps run out
            }
    }
    }

    public void takeMoneyFromOpponent(Player opponent) {

        if(this.powerB < opponent.powerA){
            gp.ui.showMessage("Power A less Than Power B");
            return;
        }
        // Calculate the fraction of money to take based on the given formula
        double moneyTaken = (this.powerB - opponent.powerA) / (double)(this.powerB + opponent.powerA) * opponent.walletA;
        // Subtract that money from the opponent's wallet
        opponent.walletA -= moneyTaken;
        // Ensure the opponent's wallet does not go negative
        if (opponent.walletA < 0) {
            opponent.walletA = 0;
        }
        // Add the taken money to player B's wallet
        this.walletB += moneyTaken;

        // Will add additional logic to handle what happens if the opponent runs out of money
    }

    //Working on another version of the update
    public void update2() {
        if (isTurn && steps > 0) {
            collisionOn = false;
            int newWorldX = worldX;
            int newWorldY = worldY;
            boolean moved = false;

            if (keyH2.upPressed) {
                newWorldY -= speed;
                direction = "up";
                moved = true;
            } else if (keyH2.downPressed) {
                newWorldY += speed;
                direction = "down";
                moved = true;
            } else if (keyH2.leftPressed) {
                newWorldX -= speed;
                direction = "left";
                moved = true;
            } else if (keyH2.rightPressed) {
                newWorldX += speed;
                direction = "right";
                moved = true;
            }

            if (moved) {
                collisionChecker.checkTile(this);
                if (!collisionOn) {
                    int objectIndex = collisionChecker.checkObject(this, true);
                    if (objectIndex == 999) {
                        worldX = newWorldX;
                        worldY = newWorldY;
                        steps -= gp.tileSize; // or decrement by a constant if one step isn't equal to tileSize
                    } else {
                        pickUpObject(objectIndex);
                    }
                }
            }

            if (steps <= 0) {
                isTurn = false; // End of the player's turn
            }
        }
    }


    //Prepare for other turn to switch
    public void prepareTurn() {
        if (!isTurn) { // Check if it's this player's turn
            int diceResult = dice.roll() ; // Roll the dice to determine steps
            steps = diceResult * 15; // Set the number of steps the player can take this turn
            System.out.println("The steps here are: "+steps/15+" for player 2");
            isTurn = true; // Mark it as the player's turn
            // Optionally, add any additional logic needed at the start of a turn
        }
    }

    //attacking in action using this method
    public void attacking(){
        spriteCounter++;

        if(spriteCounter <= 100){
            spriteNum =1;
        }
        if(spriteCounter >100 && spriteCounter <= 200){
            spriteNum =2;
        }
        if(spriteCounter >200 && spriteCounter <= 300){
            spriteNum =3;
        }
        if(spriteCounter >300 && spriteCounter <= 400){
            spriteNum =4;
        }
        if(spriteCounter >400){
            spriteNum =1;
            spriteCounter=0;
            attacking = false;
        }

    }

    //INTERACT WITH OBJECTS ON MAP
    public void pickUpObject(int i){
        //Object Being Collected
        if(i !=999)// any index not used in the object array
        {
            String objectName = gp.obj[i].name; // use this String to check our object type
            switch (objectName)
            {
                case "Key"://COLLECTING KEY TO OPEN DOORS
                    keyCountB++;
                    gp.obj[i]=null;
                    gp.ui.showMessage("Player2 GOT A KEY!");
                    break;
                case "Door":// OPEN DOOR ACCORDING TO THE KEYS WE HAVE
                    if(keyCountB>0)
                    {
                        gp.obj[i] = null;
                        keyCountB--;
                        gp.ui.showMessage("Player2 OPENED A DOOR");
                    }else
                    {
                        gp.ui.showMessage("Player2 Needs a Key to Open Door");
                    }

                    break;
                case "Diamond Ring":// Worth 40.25$
                    walletB +=40.25;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Player2 has "+walletB+" Dollar(s) now!!");
                    break;
                case "Dragon Scroll":// Worth 25.5$
                    walletB +=25.5;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Player2 has "+walletB+" Dollar(s) now!!");
                    break;
                case "Crystal Goblet":// Worth 45.5$
                    walletB +=45.5;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Player2 has "+walletB+" Dollar(s) now!!");
                    break;
                case "Jewel Sword"://Price: 27.5$
                    if(walletB >=27.5) {
                        walletB -=27.5;
                        gp.obj[i] = null;
                        hasWeapons++;
                        powerB++;
                        gp.ui.showMessage("Player2 equipped a Jewel Sword now");
                    }
                    break;
                case "Golden Goblet":// Worth 30.5$
                    walletB +=30.5;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Player2 has "+walletB+" Dollar(s) now!!");
                    break;
                case "Paladin Shield": // Price: 15.5$
                    if(walletB >=15.5) {
                        walletB -=15.5;
                        gp.obj[i] = null;
                        powerB++;
                        gp.ui.showMessage("Player2 equipped a Paladin Shield now");
                    }
                    break;
                case "Wooden Bow":// Price: 12.25$
                    if(walletB >=12.25) {
                        walletB -=12.5;
                        gp.obj[i] = null;
                        powerB++;
                        gp.ui.showMessage("Player2 equipped a Wooden Bow now");}

                    break;
                case "Castle":
                    if(keyCountB==3){
                        gp.ui.gameFinished = true;
                        keyCountB -=3;
                        gp.obj[i]=null;
                        this.winnerPlayer = true;
                        gp.ui.showMessage("Player2 WON !");
                    }else {
                        gp.ui.showMessage("Need  3 keys to WIN");
                    }
                    break;
                case "Axe"://worth 40$
                    if(walletB>=40)
                    {   walletB -=40;
                        gp.obj[i] = null;
                        hasWeapons++;
                        powerB++;
                        gp.ui.showMessage("Player2 got a Axe!");
                    }
                    break;
                case "Fire Axe"://45$
                    if(walletB>=45){
                        walletB -= 45;
                        gp.obj[i] = null;
                        hasWeapons++;
                        powerB+=3;
                        gp.ui.showMessage("Player2 got a Fire Axe!");
                    }
                    break;
                case "Cursed Sword"://worth 55.5
                if(walletB>=55.5){
                    walletB -=55.5;//pay for it
                    gp.obj[i] = null;
                    hasWeapons++;
                    powerB+=2;
                    gp.ui.showMessage("Player2 got a Cursed Sword!");
                }
                    break;
                case "Normal Sword"://25.5$
                if(walletB>=25.5){
                    walletB -=25.5;
                    gp.obj[i] = null;
                    hasWeapons++;
                    powerB++;
                    gp.ui.showMessage("Player2 got a Sword!");
                    }
                    break;
                case "Trap":
                    gp.obj[i] = null;
                    this.life2 --;
                    gp.ui.showMessage("Player2 Went Into A TRAP");
                    break;
            }
        }

    }

//    public void interactNPC(int i){//Not Using for now
//        if(i !=999){
//            System.out.println("Player 2 Hits An NPC");
//        }
//    }
//
//    public void interactMonster(int i){//Not using for now
//        if(i != 999)
//            System.out.println("Player 2 Touches a Monster");
//    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(attacking == false){
                    image = spriteNum == 1 ? up1 : spriteNum == 2 ? up2 : spriteNum == 3 ? up3 : up4;
                }
                if(attacking == true){
                    if(spriteNum == 1){ image = attack_up1;}
                    if(spriteNum == 2){ image = attack_up2;}
                }
//            image = spriteNum == 1 ? up1 : spriteNum == 2 ? up2 : spriteNum == 3 ? up3 : up4;
                break;
            case "down":
                if(attacking==false){
                    image = spriteNum == 1 ? down1 : spriteNum == 2 ? down2 : spriteNum == 3 ? down3 : down4;
                }
                if(attacking== true){
                    if(spriteNum == 1){ image = attack_down1;}
                    if(spriteNum == 2){ image = attack_down2;}
                }
                break;
            case "left":
                if(attacking==false){
                    image = spriteNum == 1 ? left1 : spriteNum == 2 ? left2 : spriteNum == 3 ? left3 : left4;
                }
                if(attacking== true){
                    if(spriteNum == 1){ image = attack_left1;}
                    if(spriteNum == 2){ image = attack_left2;}
                }
                break;
            case "right":
                if(attacking == false){
                    image = spriteNum == 1 ? right1 : spriteNum == 2 ? right2 : spriteNum == 3 ? right3 : right4;
                }
                if(attacking== true){
                    if(spriteNum == 1){ image = attack_right1;}
                    if(spriteNum == 2){ image = attack_right2;}
                }
                break;

        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    //Leave it for later uses if needed
//    public void draw1(Graphics2D g2){
//
//        BufferedImage image = null;
//        // we will use switch case to determine which direction the player is now
//        switch (direction){
//            //count if we pressed the left button or not
//            // for each consecutive method we update movement to level 1 and vice versa
//            case "up":
//                if(spriteNum==1){
//                    image = up1;
//                }
//                if(spriteNum==2){
//                    image = up2;
//                }
//                if(spriteNum == 3)
//                    image = up3;
//                if(spriteNum == 4)
//                    image = up4;
//                break;
//
//            case "down":
//                if(spriteNum==1){
//                    image = down1;
//
//                }
//                if(spriteNum==2){
//                    image= down2;
//                }
//                if(spriteNum==3){
//                    image = down3;
//
//                }
//                if(spriteNum==4){
//                    image= down4;
//                }
//
//                break;
//
//            case "left":
//                if(spriteNum==1){
//                    image = left1;
//                }
//                if(spriteNum==2){
//                    image= left2;
//                }
//                if(spriteNum==3){
//                    image = left3;
//                }
//                if(spriteNum==4){
//                    image= left4;
//                }
//                break;
//
//            case "right":
//                if(spriteNum==1) {
//                    image = right1;
//                }
//                if(spriteNum==2)
//                    image = right2;
//                if(spriteNum==3) {
//                    image = right3;
//                }
//                if(spriteNum==4)
//                    image = right4;
//
//                break;
//        }
//        // Now we are ready to draw the image on Screen Use .drawImage()
//        g2.drawImage(image,screenX,screenY, gp.tileSize, gp.tileSize, null);
//    }

}
