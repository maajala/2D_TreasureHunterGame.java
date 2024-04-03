package collision;
import com.example.game2d.GamePanel;
import entity.Entity;
import entity.Player;
import entity.Player2;

import java.awt.*;

public class CollisionChecker {

    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        if (entity == null || entity.solidArea == null) {
            // Handle the case when entity.solidArea is null
            System.err.println("Error: entity.solidArea is null");
            return;
        }

        // bellow are the boundaries of player area of collision
        int entityLeftWorldX = entity.worldX + entity.solidArea.x-5;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width+10;
        int entityTopWorldY = entity.worldY + entity.solidArea.y-10;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height+10;

        //now we can get column and row of updated player with collision characteristics by dividing by tileSize

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol= entityRightWorldX/ gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;
        int entityTopRow = entityTopWorldY/ gp.tileSize;

        //checking which block is hitting first of our player area
        int tileNum1, tileNum2;

        switch (entity.direction){
            case "up":// we subtract this player's speed from the player's solidArea
                entityTopRow = (entityTopWorldY - entity.speed) /gp.tileSize;// we predict of going up in a tight area
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];// checked the top left area of player
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];// the top right
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    // update the entityCollision on we declared
                    entity.collisionOn = true;// now we go to player class and check tile collisionOn
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1= gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];// left col bottom solid area
                tileNum2= gp.tileM.mapTileNum[entityRightCol][entityBottomRow];// right col bottom solid area
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    // update the entityCollision on we declared
                    entity.collisionOn = true;// now we go to player class and check tile collisionOn
                }
                break;
            case "left":
//                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1= gp.tileM.mapTileNum[entityLeftCol][entityTopRow];// left col bottom solid area
                tileNum2= gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];// right col bottom solid area
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    // update the entityCollision on we declared
                    entity.collisionOn = true;// now we go to player class and check tile collisionOn
                }
                break;
            case "right":
//                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1= gp.tileM.mapTileNum[entityRightCol][entityTopRow];// left col bottom solid area
                tileNum2= gp.tileM.mapTileNum[entityRightCol][entityBottomRow];// right col bottom solid area
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    // update the entityCollision on we declared
                    entity.collisionOn = true;// now we go to player class and check tile collisionOn
                }
                break;
        }
    }



    // check collision with objects
    public int checkObject(Entity entity, boolean player){
        // the purpose here is to check if the player is hitting the object or not
        // and if he is we return the index of that object which collide with it
       int  index = 999;

       for(int i=0; i < gp.obj.length; i++) {
           if (gp.obj[i] != null) {
               // get entity's solid area position
               entity.solidArea.x = entity.worldX + entity.solidArea.x;
               entity.solidArea.y = entity.worldY + entity.solidArea.y;
               //get the object's solid area position now
               gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
               gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

               switch (entity.direction) {
                   case "up": // check for every direction and apply i to index
                       entity.solidArea.y -= entity.speed;
                       if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                           if (gp.obj[i].collision == true) {
                               entity.collisionOn = true;
                           }
                           if (player == true) {
                               index = i;
                           }
                       }
                       break;
                   case "down":
                       entity.solidArea.y += entity.speed;
                       if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                           if (gp.obj[i].collision == true) {
                               entity.collisionOn = true;
                           }
                           if (player == true) {
                               index = i;
                           }
                       }
                       break;
                   case "left":
                       entity.solidArea.x -= entity.speed;
                       // intersect is a method from Rectangle class which checks if they are touching or not
                       if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                           if (gp.obj[i].collision == true) {
                               entity.collisionOn = true;
                           }
                           if (player == true) {
                               index = i;
                           }
                       }
                       break;
                   case "right":
                       entity.solidArea.x += entity.speed;
                       if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                           if (gp.obj[i].collision == true) {
                               entity.collisionOn = true;
                           }
                           if (player == true) {
                               index = i;
                           }
                           break;
                       }

               }
               entity.solidArea.x = entity.solidAreaDefaultX;
               entity.solidArea.y = entity.solidAreaDefaultY;
               gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
               gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
           }
       }
       return index;
    }


        // Method to check collision between Player1 and Player2
   public boolean checkPlayerCollision(Entity player1, Entity player2) {
            // Calculate the current solidArea positions for both players
            int p1X = player1.worldX + player1.solidArea.x;
            int p1Y = player1.worldY + player1.solidArea.y;
            Rectangle p1Rect = new Rectangle(p1X, p1Y, player1.solidArea.width, player1.solidArea.height);

            int p2X = player2.worldX + player2.solidArea.x;
            int p2Y = player2.worldY + player2.solidArea.y;
            Rectangle p2Rect = new Rectangle(p2X, p2Y, player2.solidArea.width, player2.solidArea.height);

            // Check if the solidAreas intersect
            return p1Rect.intersects(p2Rect);
    }

    //NPC or Monster
    public int checkEntity(Entity entity, Entity[] target){
        int  index = 999;// arbitrary number used as long as it's not used by object array's index


        for(int i=0; i < target.length; i++) {    // get entity's solid area position
            if (target[i] != null) { // same steps as object checker
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //get the object's solid area position now
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;


                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                                entity.collisionOn = true;
                                index = i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                            break;
                        }

                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }

}



