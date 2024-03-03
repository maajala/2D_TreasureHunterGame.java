package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

// this class will be used to store variables to be used in our player
public class Entity {
    public int worldX,worldY;

    public int speed;

    //It describes an Image with an accessible buffer of image data.
    //(We use this to store our image files)
    public BufferedImage up1 ,up2 ,up3,up4, down1, down2,down3,down4, left1 , left2 ,left3,left4, right1, right2, right3,right4;
    //will be useful for the image of the character where it will be using the direction that will be created
    public String direction;//will be acting as updater for the direction of player

    public int spriteCounter = 0;
    public int spriteNum =1;

    public Rectangle solidArea;

    public boolean collisionOn = false;
}
