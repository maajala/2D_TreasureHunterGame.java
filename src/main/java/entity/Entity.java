package entity;

import com.example.game2d.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

// this class will be used to store variables to be used in our player
public class Entity {

    GamePanel gp;
    public int worldX,worldY;
    public String name;
    public int speed;
    //It describes an Image with an accessible buffer of image data.
    //(We use this to store our image files)
    public BufferedImage up1 ,up2 ,up3,up4, down1, down2,down3,down4, left1 , left2 ,left3,left4, right1, right2, right3,right4;
    public BufferedImage attack_up1 , attack_up2, attack_right1,
            attack_right2 , attack_left1, attack_left2,attack_down1,attack_down2;
    //will be useful for the image of the character where it will be using the direction that will be created
    public String direction;//will be acting as updater for the direction of player
    public int spriteCounter = 0;
    public int spriteNum =1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;

    //CHARACTER STATUS
    public boolean collisionOn = false;
    public boolean winnerPlayer = false;
    public boolean attacking = false; // default no attack until we handle a collision

     public Entity(GamePanel gp){//abstract
        this.gp = gp;
        solidArea = new Rectangle(0, 0, 48 ,48);
    }

    public void setAction(){}
    // identical for all NPC which is best to creat own version inside each npc via override
    public void update(){

        setAction();

        //sprite counter to update images
        spriteCounter++;
        if (spriteCounter >10) {
            //updates happens every 10 frames
            if (spriteNum == 1) {
                spriteNum = 2; //update when it is pose 1 to pose 2
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }

            spriteCounter = 0; // initialize  again to zero
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX= worldX - gp.player1.worldX + gp.player1.screenX;// if worldX(0),i.e; -500+screenX in player(center)
        int screenY= worldY - gp.player1.worldY  + gp.player1.screenY;// same for y see the screenshots for more understanding

        if(worldX + gp.tileSize > gp.player1.worldX - gp.player1.screenX &&
                worldX - gp.tileSize < gp.player1.worldX + gp.player1.screenX &&
                worldY + gp.tileSize > gp.player1.worldY - gp.player1.screenY &&
                worldY - gp.tileSize < gp.player1.worldY + gp.player1.screenY )
        {   // if statement to ensure efficiency of the code// we only size the range to draw inside

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
            g2.drawImage(image, screenX , screenY , gp.tileSize, gp.tileSize, null);
        }
    }
}
