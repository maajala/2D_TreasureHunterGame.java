package entity;

import com.example.game2d.GamePanel;
import com.example.game2d.KeyHand2;
import com.example.game2d.Keyhandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;// use game panel created
    Keyhandler keyH;// key handler for movements for p1
   KeyHand2 keyH2;

    public final int screenX;// where we draw player on screen x-axis
    public final int screenY; // and y-axis

    // constructor for player one
    public Player (GamePanel gp, Keyhandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 -(gp.tileSize/2); // sub by half to be in the center
        screenY = gp.screenHeight/2 - (gp.tileSize/2);// half-way point of the screen
        setDefaultValues();

        solidArea = new Rectangle();
        solidArea.x = 8;// changed from 0 to 8 after cut of collision
        solidArea.y = 16;// changed from 0 to 16 after cut // it is optional
        solidArea.width = 32;//changed from tileSize(48) to 32 as the desired cut
        solidArea.height= 32;;
        try {
            getPlayerImage();
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception or handle it in a way that makes sense for your application.
        }
    }

    // FOR PLAYER 2
   /* public Player (GamePanel gp, KeyHand2 keyH) {
        this.gp = gp;
        this.keyH2 = keyH;

        screenX = gp.screenWidth/3 -(gp.tileSize/3); // sub by half to be in the center
        screenY = gp.screenHeight/2 - (gp.tileSize/2);// half-way point of the screen
        setDefaultValues();

        solidArea = new Rectangle();
        solidArea.x = 8;// changed from 0 to 8 after cut of collision
        solidArea.y = 16;// changed from 0 to 16 after cut // it is optional
        solidArea.width = 32;//changed from tileSize(48) to 32 as the desired cut
        solidArea.height= 32;;
        try {
            getPlayerImage();
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception or handle it in a way that makes sense for your application.
        }
    }*/

    public void setDefaultValues(){
        // same what we did in GamePanel for player position
        worldX = gp.tileSize * 23;// starting point , player position in world map // was 23
        worldY = gp.tileSize * 21;// was 21
      //  worldX = gp.maxWorldRow/2 * gp.tileSize;// this is for 10x10 map
       // worldY = gp.maxWorldCol/2 * gp.tileSize;
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
    public void update(Keyhandler keyH){ // copied from gamePanel
        if(keyH.rightPressed == true || keyH.leftPressed == true || keyH.upPressed == true || keyH.downPressed == true){
            if(keyH.upPressed){//THE IF STATEMENT HERE CHECKS THE DIRECTION NOW
                // add direction info to be updated on screen

                direction ="up";
              //  worldY -= speed; I changed this from  to switch case for collision

            }
            else if(keyH.downPressed){
                direction = "down";
              //  worldY += speed;
            }
            else if(keyH.rightPressed) {
                direction = "right";
              //  worldX += speed;
            }
            else if(keyH.leftPressed) { // for readability left
                direction = "left";
             //   worldX -= speed;
            }
            //CHECK THE COLLISION HERE AFTER DIRECTION BEEN KNOWN
            collisionOn = false;
            gp.collisionChecker.checkTile(this);
            //IF IT WAS FALSE , PLAYER CAN MOVE
            if(collisionOn == false){
                switch (direction){
                    case "up":
                        worldY -= speed; // go up
                        break;
                    case "down":
                        worldY += speed; // goes down
                        break;
                    case"left":
                        worldX -= speed; // goes to left
                        break;
                    case "right":
                        worldX += speed; // goes to right
                        break;
                }
            }

            //sprite counter to update images
            spriteCounter++;
            if(spriteCounter > 10){
                //updates happens every 10 frames
                if(spriteNum == 1){
                    spriteNum =2; //update when it is pose 1 to pose 2
                }
                else if(spriteNum == 2){
                    spriteNum = 3;// update when it is pose 2 to pose 1
                }else if(spriteNum == 3)
                {
                    spriteNum = 4;
                } else if (spriteNum==4) {
                    spriteNum = 1;
                }

                spriteCounter = 0; // initialize  again to zero
            }
        }

    }
    /*public void update(KeyHand2 keyH2){ // copied from gamePanel
        if(keyH2.rightPressed == true || keyH2.leftPressed == true || keyH2.upPressed == true || keyH2.downPressed == true){
            if(keyH2.upPressed){//THE IF STATEMENT HERE CHECKS THE DIRECTION NOW
                // add direction info to be updated on screen

                direction ="up";
                //  worldY -= speed; I changed this from  to switch case for collision

            }
            else if(keyH2.downPressed){
                direction = "down";
                //  worldY += speed;
            }
            else if(keyH2.rightPressed) {
                direction = "right";
                //  worldX += speed;
            }
            else if(keyH2.leftPressed) { // for readability left
                direction = "left";
                //   worldX -= speed;
            }
            //CHECK THE COLLISION HERE AFTER DIRECTION BEEN KNOWN
            collisionOn = false;
            gp.collisionChecker.checkTile(this);
            //IF IT WAS FALSE , PLAYER CAN MOVE
            if(collisionOn == false){
                switch (direction){
                    case "up":
                        worldY -= speed; // go up
                        break;
                    case "down":
                        worldY += speed; // goes down
                        break;
                    case"left":
                        worldX -= speed; // goes to left
                        break;
                    case "right":
                        worldX += speed; // goes to right
                        break;
                }
            }

            //sprite counter to update images
            spriteCounter++;
            if(spriteCounter > 11){
                //updates happens every 10 frames
                if(spriteNum == 1){
                    spriteNum =2; //update when it is pose 1 to pose 2
                }
                else if(spriteNum == 2){
                    spriteNum = 1;// update when it is pose 2 to pose 1
                }
                spriteCounter = 0; // initialize  again to zero
            }
        }

    }*/
    public void draw(Graphics2D g2){

        // g2.setColor(Color.white);
        //g2.fillRect(x,y,gp.tileSize,gp.tileSize);// X and y coords is not fixed anymore and can be changed anytime to change position
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
