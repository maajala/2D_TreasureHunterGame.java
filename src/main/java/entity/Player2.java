package entity;

import collision.CollisionChecker;
import com.example.game2d.GamePanel;
import com.example.game2d.KeyHandler2;
import com.example.game2d.Keyhandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class Player2 extends Entity{

    //we can delete it we have super in each constructor from Entity
    GamePanel gp;// use game panel created
    KeyHandler2 keyH2;

    public int screenX;// where we draw player on screen x-axis
    public int screenY; // and y-axis

    // Player Tools
    public int keyCountB=0;
    public double walletB=0;
    public int strengthB;
    CollisionChecker collisionChecker;
    public boolean isTurn =false;

    Dice dice = new Dice();
    public int steps = 0;



    // constructor for player one
    public Player2 (GamePanel gp, KeyHandler2 keyH2, CollisionChecker collisionChecker) {
        super(gp);
        this.gp = gp;//we can delete
        this.keyH2 = keyH2;
        this.collisionChecker = collisionChecker;

        //Player Screen Position
        screenX = gp.tileSize*8; // sub by half to be in the center
        screenY = gp.screenHeight/2 - (gp.tileSize/2);// half-way point of the screen


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
        worldX = 11 * gp.tileSize;// this is for 10x10 map
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

        collisionOn = false;

        // Potential new position
        int newWorldX = worldX;
        int newWorldY = worldY;

        if (keyH2.wPressed) {
            newWorldY -= speed;
            direction = "up";
        } else if (keyH2.sPressed) {
            newWorldY += speed;
            direction = "down";
        } else if (keyH2.aPressed) {
            newWorldX -= speed;
            direction = "left";
        } else if (keyH2.dPressed) {
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
//        if(keyH2.diceRollPressed && !isTurn){
//            int diceResult = dice.roll();
//            steps = diceResult*gp.tileSize;// Assign the # steps the player can move
//            System.out.println("The steps here are: "+steps+"  for");
//            isTurn = true;//Indicate the player's turn has started
//            keyH2.diceRollPressed = false;//Reset the roll dice request
//        }

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
        if (isTurn && (keyH2.wPressed || keyH2.sPressed || keyH2.aPressed || keyH2.dPressed)) {
            steps--; // Decrease the number of steps as the player moves
            if (steps <= 0) {
                isTurn = false; // End the player's turn when steps run out
            }
    }
    }
    public void update2() {
        if (isTurn && steps > 0) {
            collisionOn = false;
            int newWorldX = worldX;
            int newWorldY = worldY;
            boolean moved = false;

            if (keyH2.wPressed) {
                newWorldY -= speed;
                direction = "up";
                moved = true;
            } else if (keyH2.sPressed) {
                newWorldY += speed;
                direction = "down";
                moved = true;
            } else if (keyH2.aPressed) {
                newWorldX -= speed;
                direction = "left";
                moved = true;
            } else if (keyH2.dPressed) {
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


    public void prepareTurn() {
        if (!isTurn) { // Check if it's this player's turn
            int diceResult = dice.roll() ; // Roll the dice to determine steps
            steps = diceResult * 15; // Set the number of steps the player can take this turn
            System.out.println("The steps here are: "+steps/15+" for player 2");
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
                    keyCountB++;
                    gp.obj[i]=null;
                    System.out.println("Player 2 has now "+keyCountB+" key(s)");
                    break;
                case "Door":// OPEN DOOR ACCORDING TO THE KEYS WE HAVE
                    if(keyCountB>0)
                    {
                        gp.obj[i] = null;
                        keyCountB--;
                    }
                    System.out.println("Player 2 has now "+keyCountB+" key(s)");
                    break;
                case "Diamond Ring":// Worth 40.25$
                    walletB +=40.25;
                    gp.obj[i] = null;
                    System.out.println("Player 2 has "+walletB+" Dollar(s) now!!");
                    break;
                case "Dragon Scroll":// Worth 25.5$
                    walletB +=25.5;
                    gp.obj[i] = null;
                    System.out.println("Player 2 has "+walletB+" Dollar(s) now!!");
                    break;
                case "Crystal Goblet":// Worth 45.5$
                    walletB +=45.5;
                    gp.obj[i] = null;
                    System.out.println("Player 2 has "+walletB+" Dollar(s) now!!");
                    break;
                case "Jewel Sword"://Price: 27.5$
                    if(walletB >=27.5) {
                        walletB -=27.5;
                        gp.obj[i] = null;
                        System.out.println("Player 2 equipped a Jewel Sword now");
                    }
                    break;
                case "Golden Goblet":// Worth 30.5$
                    walletB +=30.5;
                    gp.obj[i] = null;
                    System.out.println("Player 2 has "+walletB+" Dollar(s) now!!");
                    break;
                case "Paladin Shield": // Price: 15.5$
                    if(walletB >=15.5) {
                        walletB -=15.5;
                        gp.obj[i] = null;
                        System.out.println("Player 2 equipped a Paladin Shield now");
                    }
                    break;
                case "Wooden Bow":// Price: 12.25$
                    if(walletB >=12.25) {
                        walletB -=12.5;
                        gp.obj[i] = null;
                        System.out.println("Player 2 equipped a Wooden Bow now");}

                    break;
            }
        }

    }

    public void interactNPC(int i){
        if(i !=999){
            System.out.println("Player 2 Hits An NPC");
        }
    }

    public void interactMonster(int i){
        if(i != 999)
            System.out.println("Player 2 Touches a Monster");
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


    // Override the update method
//    public void update() {
//        // Roll the dice if the dice roll is requested and it's the player's turn
//        if (keyH2.diceRollRequested && isTurn) {
//            rollDice();
//        }
//
//        if (!isTurn) {
//            // It's not this player's turn, skip the movement logic
//            return;
//        }
//
//        collisionOn = false;
//
//        // Potential new position based on current direction and speed
//        int newWorldX = worldX;
//        int newWorldY = worldY;
//
//        if (keyH2.wPressed) {
//            newWorldY -= speed;
//            direction = "up";
//        } else if (keyH2.sPressed) {
//            newWorldY += speed;
//            direction = "down";
//        } else if (keyH2.aPressed) {
//            newWorldX -= speed;
//            direction = "left";
//        } else if (keyH2.dPressed) {
//            newWorldX += speed;
//            direction = "right";
//        }
//
//        // Collision checking
//        collisionChecker.checkTile(this);
//
//        // If there is no collision, update the world position
//        if (!collisionOn) {
//            int objectIndex = collisionChecker.checkObject(this, true);
//            if(objectIndex == 999) {
//                worldX = newWorldX;
//                worldY = newWorldY;
//            } else {
//                // Handle object collision event, e.g., pick up a key
//                pickUpObject(objectIndex);
//            }
//        }
//
//        // Update screen position based on the world position and camera position
//        // This part would typically involve adjusting the player's screen position based on the camera logic if your game uses a camera that follows the player
//        screenX = worldX;
//        screenY = worldY;
//
//        // Update sprite animations
//        spriteCounter++;
//        if (spriteCounter > 12) {
//            spriteNum = (spriteNum % 4) + 1; // Cycle through sprite numbers 1 to 4
//            spriteCounter = 0;
//        }
//    }


    // Your existing methods...

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

/*public void update() {
    // Ensure player can move only if there are steps left
    if (isTurn && steps > 0) {
        int newWorldX = worldX;
        int newWorldY = worldY;
        boolean moved = false; // Flag to check if movement happens

        if (keyHand.upPressed && !lastDirection.equals("up")) {
            newWorldY -= tileSize; // Assuming movement is by tile size
            lastDirection = "up";
            moved = true;
        } else if (keyHand.downPressed && !lastDirection.equals("down")) {
            newWorldY += tileSize;
            lastDirection = "down";
            moved = true;
        } else if (keyHand.leftPressed && !lastDirection.equals("left")) {
            newWorldX -= tileSize;
            lastDirection = "left";
            moved = true;
        } else if (keyHand.rightPressed && !lastDirection.equals("right")) {
            newWorldX += tileSize;
            lastDirection = "right";
            moved = true;
        }

        // Process movement if there's no collision
        if (moved) {
            collisionChecker.checkTile(this);
            if (!collisionOn) {
                worldX = newWorldX;
                worldY = newWorldY;
                steps--; // Decrement steps after a successful move
            }
        }

        // Reset turn if no steps left
        if (steps <= 0) {
            isTurn = false;
            lastDirection = ""; // Reset direction after turn ends
        }
    }
}
*/
