package object;

import com.example.game2d.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject { // game objects
    //THE PARENT CLASS FOR OTHER OBJECTS

    public BufferedImage image; // to capture image of each object
    public String name;// name for each object
    public boolean collision = false ; // for collision checker
    public int worldX, worldY; // we need to creat object class for keyHandling

    public void draw(Graphics2D g2, GamePanel gp){
        //almost same as background Tiles we need screenX and screenY only with if statement
        // just the same we modified the tile array and leave it as image
        int screenX= worldX - gp.player1.worldX + gp.player1.screenX;// if worldX(0),i.e; -500+screenX in player(center)
        int screenY= worldY - gp.player1.worldY  + gp.player1.screenY;// same for y see the screenshots for more understanding

        if(worldX + gp.tileSize > gp.player1.worldX - gp.player1.screenX &&
                worldX - gp.tileSize < gp.player1.worldX + gp.player1.screenX &&
                worldY + gp.tileSize > gp.player1.worldY - gp.player1.screenY &&
                worldY - gp.tileSize < gp.player1.worldY + gp.player1.screenY )
        {   // if statement to ensure efficiency of the code// we only size the range to draw inside
            g2.drawImage(image, screenX , screenY , gp.tileSize, gp.tileSize, null);
        }//NOW WE CALL THIS IN THE PAINT METHOD IN GamePanel
    }
}
