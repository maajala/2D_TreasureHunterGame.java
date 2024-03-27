package entity;

import collision.CollisionChecker;
import com.example.game2d.GamePanel;
import com.example.game2d.Keyhandler;
import com.example.game2d.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Player extends Entity{

    //we can delete it we have super in each constructor from Entity
    GamePanel gp;// use game panel created
    Keyhandler keyHand;
    public int screenX;// where we draw player on screen x-axis
    public int screenY; // and y-axis

    // Player Tools
    public int keyCountA=0;//key in pocket
    public double walletA=0;//money at moment
    public int maxLife1;//initial health
    public int life1;//varying health
    public int powerA;//initial power
    public int hasWeapons;
   CollisionChecker collisionChecker;

   public boolean isTurn = false ;
    // Inside your game loop or update method
    public boolean collisionWithPlayer2 = false;


    Dice dice = new Dice();//calling the dice method
    public int steps = 0; // Steps remaining for the player's current turn
    // constructor for player one
    public Player (GamePanel gp, Keyhandler keyH, CollisionChecker collisionChecker) {
        super(gp);
        this.gp = gp;//we can delete
        this.keyHand = keyH;
        this.collisionChecker = collisionChecker;

        //Player Screen Position
        screenX = gp.tileSize; // sub by half to be in the center
        screenY =gp.maxWorldCol/2 * gp.tileSize;;// half-way point of the screen


        //Solid Area Variables for player
        solidArea = new Rectangle();
        solidArea.x = 8;// changed from 0 to 8 after cut of collision
        solidArea.y = 16;// changed from 0 to 16 after cut // it is optional
        solidArea.width = 48;//changed from tileSize(48) to 32 as the desired cut
        solidArea.height= 48;;
        solidAreaDefaultX = solidArea.x;// default values helpful to retrieve
        solidAreaDefaultY = solidArea.y;// the default values when changing them later

        setDefaultValues();
        int player1Health = 100;
        try {
            getPlayerImage();
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception or handle it in a way that makes sense for your application.
        }

    }

    // Call this method when it's the player's turn to roll the dice

//    public Player (GamePanel gp, KeyHand2 keyH2) {
//        super(gp);
//        this.gp = gp;
//        this.keyHand2 = keyH2;
//
//        screenX = gp.screenWidth/2 -(gp.tileSize/2); // sub by half to be in the center
//        screenY = gp.screenHeight/2 - (gp.tileSize/2);// half-way point of the screen
//
//
//        solidArea = new Rectangle();
//        solidArea.x = 3;// changed from 0 to 8 after cut of collision
//        solidArea.y = 3;// changed from 0 to 16 after cut // it is optional
//        solidArea.width = 32;//changed from tileSize(48) to 32 as the desired cut
//        solidArea.height= 32;
//
//        setDefaultValues();
//
//        int player2Health=95;
//        try {
//            getPlayerImage();
//        } catch (IOException e) {
//            e.printStackTrace(); // Log the exception or handle it in a way that makes sense for your application.
//        }
//    }

    // Inside your game loop or update method

    public void setDefaultValues() {
        // same what we did in GamePanel for player position
        //worldX = gp.tileSize * 23;// starting point , player position in world map // was 23
        //worldY = gp.tileSize * 21;// was 21
        worldX = gp.tileSize;// this is for 10x10 map
        worldY = gp.maxWorldCol/2 * gp.tileSize;
        speed = 4;
        direction = "down"; // can be chosen any direction as default

        maxLife1=6;//max life to be reached
        life1 = maxLife1;// current life to be used
        //same for both players
        powerA = 5;

    }
    // Call this method when playerA wins the battle
    public void takeMoneyFromOpponent(Player2 opponent) {
        if(this.powerA < opponent.powerB){
            gp.ui.showMessage("Power A less Than Power B");
            return;
        }
        // Calculate the fraction of money to take based on the given formula
        double moneyTaken = (this.powerA - opponent.powerB) / (double)(this.powerA + opponent.powerB) * opponent.walletB;
        // Subtract that money from the opponent's wallet
        opponent.walletB -= moneyTaken;
        this.powerA -= opponent.powerB;// decrease power of winning player
        opponent.powerB=0;
        // Ensure the opponent's wallet does not go negative
        if (opponent.walletB < 0) {
            opponent.walletB = 0;
        }
        // Add the taken money to player A's wallet
        this.walletA += moneyTaken;

        // Will add additional logic to handle what happens if the opponent runs out of money

    }

    // to get image of player
//    public void getPlayerImage() throws IOException{
//        try {// snap of every image of the player
//            up1 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_1.png"))));
//            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_2.png")));
//            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_3.png")));
//            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_4.png")));
//
//            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_3.png")));
//            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_2.png")));
//            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_1.png")));
//            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_4.png")));
//
//            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_1.png")));
//            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_4.png")));
//            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_3.png")));
//            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_2.png")));
//
//
//            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_1.png")));
//            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_2.png")));
//            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_3.png")));
//            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_4.png")));
//
//
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//            throw new IOException("Error loading player images", e);
//
//        }
//
//    }


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
    public void getPlayerAttackImage1(){

        up1 = setup("boy_up_1",gp.tileSize,gp.tileSize*2);
        up2 = setup("boy_up_2",gp.tileSize,gp.tileSize*2);
        up3 = setup("boy_up_1",gp.tileSize,gp.tileSize*2);
        up4 = setup("boy_up_2",gp.tileSize,gp.tileSize*2);

        down1=setup("boy_down_1",gp.tileSize,gp.tileSize*2);
        down2=setup("boy_down_2",gp.tileSize,gp.tileSize*2);
        down3=setup("boy_down_1",gp.tileSize,gp.tileSize*2);
        down4=setup("boy_down_2",gp.tileSize,gp.tileSize*2);

        right1=setup("boy_right_1",gp.tileSize*2,gp.tileSize);
        right2=setup("boy_right_2",gp.tileSize*2,gp.tileSize);
        right3=setup("boy_right_1",gp.tileSize*2,gp.tileSize);
        right4=setup("boy_right_2",gp.tileSize*2,gp.tileSize);

        left1=setup("boy_left_1",gp.tileSize*2,gp.tileSize);
        left2=setup("boy_left_2",gp.tileSize*2,gp.tileSize);
        left3=setup("boy_left_1",gp.tileSize*2,gp.tileSize);
        left4=setup("boy_left_2",gp.tileSize*2,gp.tileSize);
    }

    // Update player position based on key input

    public void update3() {
        // Check if there are steps remaining and a movement key was first pressed.
        if (steps > 0 && (keyHand.upFirstPressed || keyHand.downFirstPressed || keyHand.leftFirstPressed || keyHand.rightFirstPressed)) {
            // Calculate potential new position based on direction.
            int newWorldX = worldX;
            int newWorldY = worldY;

            if (keyHand.upFirstPressed) {
                newWorldY -= gp.tileSize; // Move up by one tileSize
                direction = "up";
                keyHand.upFirstPressed = false; // Reset the first press indicator
            } else if (keyHand.downFirstPressed) {
                newWorldY += gp.tileSize; // Move down by one tileSize
                direction = "down";
                keyHand.downFirstPressed = false;
            } else if (keyHand.leftFirstPressed) {
                newWorldX -= gp.tileSize; // Move left by one tileSize
                direction = "left";
                keyHand.leftFirstPressed = false;
            } else if (keyHand.rightFirstPressed) {
                newWorldX += gp.tileSize; // Move right by one tileSize
                direction = "right";
                keyHand.rightFirstPressed = false;
            }

            // Perform collision checking and update world position if no collision.
            // Your existing collision check logic here, assuming it prevents the move if there's a collision.
            collisionChecker.checkTile(this);
            if (!collisionOn) {
                worldX = newWorldX;
                worldY = newWorldY;
                steps--; // Decrement steps after moving.
            }
            else{
                return;
            }
        }

        // Handle collision with another player.
        // Existing logic for handling collision with Player 2.
        collisionWithPlayer2 = collisionChecker.checkPlayerCollision(this, gp.player2);
        if (collisionWithPlayer2) {
            if (keyHand.enterPressed) {
                attacking();
                gp.player2.life2 -= 1;
            }
            System.out.println("Player 1 hits Player 2!");
        }

        // Update screen position based on world position.
        screenX = worldX;
        screenY = worldY;

        // Update sprite animations.
        updateSpriteAnimation();
    }
    private void updateSpriteAnimation() {
        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = (spriteNum % 4) + 1; // Cycle through sprite numbers 1 to 4
            spriteCounter = 0;
        }
    }

    public void update() {

            collisionOn = false;

            // Potential new position
            int newWorldX = worldX;
            int newWorldY = worldY;

            if (keyHand.upPressed) {
                newWorldY -= speed;
                direction = "up";
            } else if (keyHand.downPressed) {
                newWorldY += speed;
                direction = "down";
            } else if (keyHand.leftPressed) {
                newWorldX -= speed;
                direction = "left";
            } else if (keyHand.rightPressed) {
                newWorldX += speed;
                direction = "right";
            }

            // Collision checking
            collisionChecker.checkTile(this);

            // If there is no collision, update the world position
            if (!collisionOn) {
                int objectIndex = collisionChecker.checkObject(this, true);
                if (objectIndex == 999) {
                    worldX = newWorldX;
                    worldY = newWorldY;

                } else {
                    // Handle object collision event, e.g., pick up a key
                    pickUpObject(objectIndex);
                }
            }

        // Then check collision with the other player using their current position and your new potential position
        collisionWithPlayer2 = collisionChecker.checkPlayerCollision(this, gp.player2);

        // If no collision with the environment or the other player, update position
        if (!collisionWithPlayer2) {

        } else {
            if(keyHand.enterPressed){
                attacking();
                gp.player2.life2 -=1;
                if(gp.player2.life2 == 0){
                    gp.ui.showMessage("Player2 won The Battle ");
                    takeMoneyFromOpponent(gp.player2);
                    System.out.println("WalletB =  "+gp.player2.walletB+" WalleA = "+this.walletA);

                }
            }
            System.out.println("Player 1 hits PLayer 2 !");
            // Handle collision (e.g., stop movement, play sound, etc.)
        }
        if(powerA==0)//when player A loses he comes back to his origin
        {
            worldX = gp.tileSize;
            worldY = gp.maxWorldCol/2 * gp.tileSize;
            life1 = 6;//recga
        }

            screenX = worldX;
            screenY = worldY;

            // Update sprite animations
            spriteCounter++;
            if (spriteCounter > 12) {// interval of 12
                spriteNum = (spriteNum % 4) + 1; // Cycle through sprite numbers 1 to 4
                // The result is always in the range 0 to 3
                spriteCounter = 0;
            }
            if (isTurn && (keyHand.upPressed || keyHand.downPressed || keyHand.leftPressed || keyHand.rightPressed)) {
                steps--; // Decrease the number of steps as the player moves
                if (steps <= 0) {
                    isTurn = false; // End the player's turn when steps run out
                }
            }
        }

    //Prepare for other turn to switch
    public void prepareTurn() {
        if (!isTurn) { // Check if it's this player's turn
            int diceResult = dice.roll(); // Roll the dice to determine steps
            steps = diceResult*15; // Set the number of steps the player can take this turn
            System.out.println("The steps here are: "+steps/15+" for player 1");
            isTurn = true; // Mark it as the player's turn
            // Optionally, add any additional logic needed at the start of a turn
        }
    }

    //attacking in action using this method
    public void attacking(){
        spriteCounter++;

        if(spriteCounter <= 5){
            spriteNum =1;
        }
        if(spriteCounter >5 && spriteCounter <= 25){
            spriteNum =2;
        }
        if(spriteCounter >25 && spriteCounter <= 45){
            spriteNum =3;
        }
        if(spriteCounter >45 && spriteCounter <= 65){
            spriteNum =4;
        }
        if(spriteCounter >65){
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
                    keyCountA++;
                    gp.obj[i]=null;
                   gp.ui.showMessage("Payer A got A key!");
                    break;
                case "Door":// OPEN DOOR ACCORDING TO THE KEYS WE HAVE
                    if(keyCountA>0)
                    {
                        gp.obj[i] = null;
                        keyCountA--;
                    }
                    gp.ui.showMessage("You Opened the Door");
                    break;
                case "Diamond Ring":// Worth 40.25$
                    walletA +=40.25;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Player1 wallet has "+walletA+" $");
                    break;
                case "Dragon Scroll":// Worth 25.5$
                    walletA +=25.5;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Player1 has "+walletA+" Dollar(s) now!!");
                    break;
                case "Crystal Goblet":// Worth 45.5$
                    walletA +=45.5;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Player1 has "+walletA+" Dollar(s) now!!");
                    break;
                case "Jewel Sword"://Price: 27.5$
                    if(walletA >=27.5) {
                        walletA -=27.5;
                        gp.obj[i] = null;
                        hasWeapons++;
                        powerA++;
                        gp.ui.showMessage("Player1 equipped a Jewel Sword now");
                    }
                    break;
                case "Golden Goblet":// Worth 30.5$
                    walletA +=30.5;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Player1 has "+walletA+" Dollar(s) now!!");
                    break;
                case "Paladin Shield": // Price: 15.5$
                    if(walletA >=15.5) {
                        walletA -=15.5;
                        gp.obj[i] = null;
                        gp.ui.showMessage("Player1 equipped a Paladin Shield now");
                    }
                    break;
                case "Wooden Bow":// Price: 12.25$
                    if(walletA >=12.25) {
                        walletA -=12.5;
                        gp.obj[i] = null;
                        gp.ui.showMessage("Player1 equipped a Wooden Bow now");}

                    break;
                case "Castle":
                    if(keyCountA==3){
                        gp.ui.gameFinished = true;
                        keyCountA -=3;
                        gp.obj[i]=null;
                        this.winnerPlayer = true;
                        gp.ui.showMessage("Player1 WON !");
                    }else {
                        gp.ui.showMessage("Need 3 keys to WIN");
                    }
                    break;
                case "Axe":
                if(walletA>=40)
                {
                    walletA -=40;
                    gp.obj[i] = null;
                    hasWeapons++;
                    powerA++;
                    gp.ui.showMessage("Player1 got a Axe!");
                }
                break;
                case "Fire Axe"://45$
                    if(walletA>=45){
                        walletA -= 45;
                        gp.obj[i] = null;
                        hasWeapons++;
                        powerA+=3;
                        gp.ui.showMessage("Player1 got a Fire Axe!");
                    }
                    break;
                case "Cursed Sword"://worth 55.5
                    if(walletA>=55.5){
                        walletA -=55.5;//pay for it
                        gp.obj[i] = null;
                        hasWeapons++;
                        powerA+=2;
                        gp.ui.showMessage("Player1 got a Cursed Sword!");
                    }
                    break;
                case "Normal Sword"://25.5$
                    if(walletA>=25.5){
                        walletA -=25.5;
                        gp.obj[i] = null;
                        hasWeapons++;
                        powerA++;
                        gp.ui.showMessage("Player1 got a Sword!");
                    }
                    break;
                case "Trap":
                gp.obj[i] = null;
                this.life1 --;
                gp.ui.showMessage("Player1 Went Into A TRAP");
                break;

            }
        }

    }

    public void interactNPC(int i){
        if(i !=999){
            System.out.println("Player Hits An NPC");
        }
    }

    public void interactMonster(int i){
        if(i != 999)
            System.out.println("Player Touches a Monster");
    }

//    public void draw(Graphics2D g2){
//
//        BufferedImage image = null;
//        // we will use switch case to determine which direction the player is now
//        switch (direction){
//            //count if we pressed the left button or not
//            // for each consecutive method we update movement to level 1 and vice versa
//            case "up":
//                if(spriteNum==1){
//                    image = up1;
//                    screenY -=speed;
//                }
//                if(spriteNum==2){
//                    image = up2;
//                    screenY -=speed;
//                }
//                if(spriteNum == 3) {
//                    image = up3;
//                    screenY -= speed;
//                }
//                if(spriteNum == 4){
//                    image = up4;
//                    screenY -= speed;
//                }
//                break;
//
//             case "down":
//                 if(spriteNum==1){
//                     image = down1;
//                     screenY += speed;
//
//                 }
//                 if(spriteNum==2){
//                     image= down2;
//                     screenY += speed;
//                 }
//                 if(spriteNum==3){
//                     image = down3;
//                     screenY += speed;
//
//                 }
//                 if(spriteNum==4){
//                     image= down4;
//                     screenY += speed;
//                 }
//
//                 break;
//
//            case "left":
//                if(spriteNum==1){
//                    image = left1;
//                   screenX -= speed;
//                }
//                if(spriteNum==2){
//                    image= left2;
//                    screenX -= speed;
//                }
//                if(spriteNum==3){
//                    image = left3;
//                    screenX -= speed;
//                }
//                if(spriteNum==4){
//                    image= left4;
//                   screenX -= speed;
//                }
//                break;
//
//            case "right":
//                if(spriteNum==1) {
//                image = right1;
//                    screenX += speed;
//                }
//                if(spriteNum==2){
//                    image = right2;
//                    screenX += speed;
//                }
//                if(spriteNum==3) {
//                    image = right3;
//                   screenX += speed;
//                }
//                if(spriteNum==4)
//                {
//                    image = right4;
//                    screenX += speed;
//                }
//
//                break;
//        }
//        // Now we are ready to draw the image on Screen Use .drawImage()
//        g2.drawImage(image,screenX,screenY, gp.tileSize, gp.tileSize, null);
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

    public void draw1(Graphics2D g2){

        BufferedImage image = null;
        // we will use switch case to determine which direction the player is now
        switch (direction){
            //count if we pressed the left button or not
            // for each consecutive method we update movement to level 1 and vice versa
            case "up":
                if(spriteNum==1){
                    image = up1;
                }
                if(spriteNum==2){
                    image = up2;
                }
                if(spriteNum == 3)
                    image = up3;
                if(spriteNum == 4)
                    image = up4;
                break;

            case "down":
                if(spriteNum==1){
                    image = down1;

                }
                if(spriteNum==2){
                    image= down2;
                }
                if(spriteNum==3){
                    image = down3;

                }
                if(spriteNum==4){
                    image= down4;
                }

                break;

            case "left":
                if(spriteNum==1){
                    image = left1;
                }
                if(spriteNum==2){
                    image= left2;
                }
                if(spriteNum==3){
                    image = left3;
                }
                if(spriteNum==4){
                    image= left4;
                }
                break;

            case "right":
                if(spriteNum==1) {
                    image = right1;
                }
                if(spriteNum==2)
                    image = right2;
                if(spriteNum==3) {
                    image = right3;
                }
                if(spriteNum==4)
                    image = right4;

                break;
        }
        // Now we are ready to draw the image on Screen Use .drawImage()
        g2.drawImage(image,screenX,screenY, gp.tileSize, gp.tileSize, null);
    }

}

//comments
//// Inside your game loop or update method
//boolean collision = collisionChecker.checkPlayerCollision(player1, player2);
//if (collision) {
//    // Handle player collision here (e.g., stop movement, trigger an event, etc.)
//    System.out.println("Players have collided!");
//}

//            if ( !isTurn) {
//                int diceResult = dice.roll();
//                steps = diceResult *2;// Assign the # steps the player can move
//                System.out.println("The steps here are: "+steps+" for player 1");
//                isTurn = true;//Indicate the player's turn has started
//                keyHand.diceRollPressed = false;//Reset the roll dice request
//            }

//}


// Update screen position based on the world position and camera position
// If your game has a camera that follows the player, you need to convert world coordinates to screen coordinates
// For a static camera, it would just be a direct assignment like below: