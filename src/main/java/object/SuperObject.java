package object;

import com.example.game2d.GamePanel;
import com.example.game2d.UtilityTool;
import houses.Market;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SuperObject { // game objects
    //THE PARENT CLASS FOR OTHER OBJECTS

    public BufferedImage image, image2 , image3, image4; // to capture image of each object
    public String name;// name for each object
    public double worth;
    public boolean collision = false ; // for collision checker
    public Rectangle solidArea = new Rectangle(0 , 0, 48 ,48);// create a border around each object
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY=0;
    //Position of Solid Area
    public int worldX, worldY; // we need to creat object class for keyHandler

    // ITEM ATTRIBUTES
    public String description = "";
    public BufferedImage originalImage;

    // TYPE of Objects
    public int type;
    public static final int type_sword = 0;
    public static final int type_bow=1;
    public static final int type_axe = 2;
    public static final int type_shield =3;
    public static final int type_consumable = 4;
    //BATTLE TOOL
    public int attack;
    public int defence;


    public void draw(Graphics2D g2, GamePanel gp) {
     

        // Adjust the drawing coordinates by the difference
        int adjustedX = worldX ;//+ dx;
        int adjustedY = worldY ;//+ dy;

        // Draw the object at the adjusted coordinates
        g2.drawImage(image, adjustedX, adjustedY, gp.tileSize, gp.tileSize, null);
    }

    public BufferedImage scaleImage(BufferedImage before, double scaleX, double scaleY) {
        int w = before.getWidth();//image information before
        int h = before.getHeight();//image information before scaling
        // Create a new BufferedImage for the scaled image
        BufferedImage after = new BufferedImage((int)(w * scaleX), (int)(h * scaleY), BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();// Create an AffineTransform to perform scaling
        at.scale(scaleX, scaleY);// Create an AffineTransformOp to apply the scaling operation
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        // Apply the scaling operation to the original image and store the result in the 'after' BufferedImage
        after = scaleOp.filter(before, after);
        // Return the scaled image
        return after;
    }

}
//public void draw2(Graphics2D g2, GamePanel gp){
//    //almost same as background Tiles we need screenX and screenY only with if statement
//    // just the same we modified the tile array and leave it as image
//    int screenX= worldX - gp.player1.worldX + gp.player1.screenX;// if worldX(0),i.e; -500+screenX in player(center)
//    int screenY= worldY - gp.player1.worldY  + gp.player1.screenY;// same for y see the screenshots for more understanding
//
//    if(worldX + gp.tileSize > gp.player1.worldX - gp.player1.screenX &&
//            worldX - gp.tileSize < gp.player1.worldX + gp.player1.screenX &&
//            worldY + gp.tileSize > gp.player1.worldY - gp.player1.screenY &&
//            worldY - gp.tileSize < gp.player1.worldY + gp.player1.screenY )
//    {   // if statement to ensure efficiency of the code// we only size the range to draw inside
//        g2.drawImage(image, screenX , screenY , gp.tileSize, gp.tileSize, null);
//    }//NOW WE CALL THIS IN THE PAINT METHOD IN GamePanel
//}
