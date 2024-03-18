package entity;

import collision.CollisionChecker;
import com.example.game2d.GamePanel;
import com.example.game2d.Keyhandler;

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
    public int keyCountA=0;
    public double walletA=0;
    public int strengthA;
   CollisionChecker collisionChecker;

   public boolean isTurn = false ;
   public Random random; // For rolling the dice
    Dice dice = new Dice();
    public int steps = 0; // Steps remaining for the player's current turn

    // constructor for player one
    public Player (GamePanel gp, Keyhandler keyH, CollisionChecker collisionChecker) {
        super(gp);
        this.gp = gp;//we can delete
        this.keyHand = keyH;
        this.collisionChecker = collisionChecker;

        //Player Screen Position
        screenX = worldX; // sub by half to be in the center
        screenY = worldY;// half-way point of the screen


        //Solid Area Variables for player
        solidArea = new Rectangle();
        solidArea.x = 8;// changed from 0 to 8 after cut of collision
        solidArea.y = 16;// changed from 0 to 16 after cut // it is optional
        solidArea.width = 32;//changed from tileSize(48) to 32 as the desired cut
        solidArea.height= 32;;
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


    public void setDefaultValues() {
        // same what we did in GamePanel for player position
        //worldX = gp.tileSize * 23;// starting point , player position in world map // was 23
        //worldY = gp.tileSize * 21;// was 21
        worldX = gp.tileSize;// this is for 10x10 map
        worldY = gp.maxWorldCol/2 * gp.tileSize;
        speed = 4;
        direction = "down"; // can be chosen any direction as default

    }


    // to get image of player
    public void getPlayerImage() throws IOException{
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
    // update method is called 60 times /second , every


    // Update player position based on key input
    public void update() {
//        if(!isTurn){
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
            screenX = worldX;
            screenY = worldY;

            // Update sprite animations
            spriteCounter++;
            if (spriteCounter > 12) {
                spriteNum = (spriteNum % 4) + 1; // Cycle through sprite numbers 1 to 4
                spriteCounter = 0;
            }
            if (isTurn && (keyHand.upPressed || keyHand.downPressed || keyHand.leftPressed || keyHand.rightPressed)) {
                steps--; // Decrease the number of steps as the player moves
                if (steps <= 0) {
                    isTurn = false; // End the player's turn when steps run out
                }
            }
        }

//    public void update(Keyhandler keyH) {
//        // copied from gamePanel
//        // System.out.println("Player Update Called");
//        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
//
//            if (keyH.upPressed) {//THE IF STATEMENT HERE CHECKS THE DIRECTION NOW
//                // add direction info to be updated on screen
//                direction = "up";
//                //worldY -= speed;// I changed this from  to switch case for collision
//
//            } else if (keyH.downPressed) {
//                direction = "down";
//               // worldY += speed;
//            } else if (keyH.rightPressed) {
//                direction = "right";
//               // worldX += speed;
//            } else if (keyH.leftPressed) { // for readability left
//                direction = "left";
//                 //worldX -= speed;
//            }
//
//        //} else {
//
//            spriteCounter++;
//            if (spriteCounter > 8) {
//                //updates happens every 10 frames
//                if (spriteNum == 1) {
//                    spriteNum = 2; //update when it is pose 1 to pose 2
//                } else if (spriteNum == 2) {
//                    spriteNum = 3;// update when it is pose 2 to pose 1
//                } else if (spriteNum == 3) {
//                    spriteNum = 4;
//                } else if (spriteNum == 4) {
//                    spriteNum = 1;
//                }
//
//                spriteCounter = 0; // initialize  again to zero
//            }
//        }
//
////
////        if(keyH.rightPressed == true || keyH.leftPressed == true || keyH.upPressed == true || keyH.downPressed == true) {
////
////           // if (!movedThisTurn) {
////
////                if (keyH.upPressed) {//THE IF STATEMENT HERE CHECKS THE DIRECTION NOW
////                    // add direction info to be updated on screen
////                    direction = "up";
////                    //worldY -= speed;// I changed this from  to switch case for collision
////
////                } else if (keyH.downPressed) {
////                    direction = "down";
////                    // worldY += speed;
////                } else if (keyH.rightPressed) {
////                    direction = "right";
////                    //worldX += speed;
////                } else if (keyH.leftPressed) { // for readability left
////                    direction = "left";
////                    // worldX -= speed;
////                }
////
////                // Use the dice result to determine the number of steps
////               // int steps = diceResult * gp.tileSize;
////
////
////
////
////
////            //CHECK THE COLLISION HERE AFTER DIRECTION BEEN KNOWN
////            collisionOn = false;
////            gp.collisionChecker.checkTile(this);
////            //CHECK OBJECT COLLISION
////            int objectIndex = gp.collisionChecker.checkObject(this,true);
////            pickUpObject(objectIndex);
////
////            //CHECK NPC COLLISION
////            int npcIndex = gp.collisionChecker.checkEntity(this,gp.npc);
////            interactNPC(npcIndex);
////
////            //CHECK Monster Collision
////            int  monsterIndex = gp.collisionChecker.checkEntity(this,gp.monster);
////            interactMonster(monsterIndex);
////
////
////                //IF IT WAS FALSE , PLAYER CAN MOVE
////                if (collisionOn == false) {
////                    switch (direction) {
////                        case "up":
////                            worldY -= speed;//steps; // go up
////                            break;
////                        case "down":
////                            worldY += speed; // goes down
////                            break;
////                        case "left":
////                            worldX -= speed; // goes to left
////                            break;
////                        case "right":
////                            worldX += speed; // goes to right
////                            break;
////                    }
////                   // movedThisTurn = true;
////                }
////
////                //sprite counter to update images
////                spriteCounter++;
////                if (spriteCounter >8) {
////                    //updates happens every 10 frames
////                    if (spriteNum == 1) {
////                        spriteNum = 2; //update when it is pose 1 to pose 2
////                    } else if (spriteNum == 2) {
////                        spriteNum = 3;// update when it is pose 2 to pose 1
////                    } else if (spriteNum == 3) {
////                        spriteNum = 4;
////                    } else if (spriteNum == 4) {
////                        spriteNum = 1;
////                    }
////
////                    spriteCounter = 0; // initialize  again to zero
////                }
////           // }
////        }
////
//    }


    // Override the update method

    // Helper method to handle movement and collision
    // The move method should include collision checking and decrementing of steps

    public void prepareTurn() {
        if (!isTurn) { // Check if it's this player's turn
            int diceResult = dice.roll(); // Roll the dice to determine steps
            steps = diceResult*15; // Set the number of steps the player can take this turn
            System.out.println("The steps here are: "+steps/15+" for player 1");
            isTurn = true; // Mark it as the player's turn
            // Optionally, add any additional logic needed at the start of a turn
        }
    }
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
                    System.out.println("Player has now "+keyCountA+" key(s)");
                    break;
                case "Door":// OPEN DOOR ACCORDING TO THE KEYS WE HAVE
                    if(keyCountA>0)
                    {
                        gp.obj[i] = null;
                        keyCountA--;
                    }
                    System.out.println("Player has now "+keyCountA+" key(s)");
                    break;
                case "Diamond Ring":// Worth 40.25$
                    walletA +=40.25;
                    gp.obj[i] = null;
                    System.out.println("Player has "+walletA+" Dollar(s) now!!");
                    break;
                case "Dragon Scroll":// Worth 25.5$
                    walletA +=25.5;
                    gp.obj[i] = null;
                    System.out.println("Player has "+walletA+" Dollar(s) now!!");
                    break;
                case "Crystal Goblet":// Worth 45.5$
                    walletA +=45.5;
                    gp.obj[i] = null;
                    System.out.println("Player has "+walletA+" Dollar(s) now!!");
                    break;
                case "Jewel Sword"://Price: 27.5$
                    if(walletA >=27.5) {
                        walletA -=27.5;
                        gp.obj[i] = null;
                        System.out.println("Player 2 equipped a Jewel Sword now");
                    }
                    break;
                case "Golden Goblet":// Worth 30.5$
                    walletA +=30.5;
                    gp.obj[i] = null;
                    System.out.println("Player has "+walletA+" Dollar(s) now!!");
                    break;
                case "Paladin Shield": // Price: 15.5$
                    if(walletA >=15.5) {
                        walletA -=15.5;
                        gp.obj[i] = null;
                        System.out.println("Player 2 equipped a Paladin Shield now");
                    }
                    break;
                case "Wooden Bow":// Price: 12.25$
                    if(walletA >=12.25) {
                        walletA -=12.5;
                        gp.obj[i] = null;
                        System.out.println("Player 2 equipped a Wooden Bow now");}

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
            image = spriteNum == 1 ? up1 : spriteNum == 2 ? up2 : spriteNum == 3 ? up3 : up4;
            break;
        case "down":
            image = spriteNum == 1 ? down1 : spriteNum == 2 ? down2 : spriteNum == 3 ? down3 : down4;
            break;
        case "left":
            image = spriteNum == 1 ? left1 : spriteNum == 2 ? left2 : spriteNum == 3 ? left3 : left4;
            break;
        case "right":
            image = spriteNum == 1 ? right1 : spriteNum == 2 ? right2 : spriteNum == 3 ? right3 : right4;
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
