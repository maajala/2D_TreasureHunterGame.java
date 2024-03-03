package collision;
import com.example.game2d.GamePanel;
import entity.Entity;

public class CollisionChecker {

    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){

        // bellow are the boundaries of player area of collision
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

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
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1= gp.tileM.mapTileNum[entityLeftCol][entityTopRow];// left col bottom solid area
                tileNum2= gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];// right col bottom solid area
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    // update the entityCollision on we declared
                    entity.collisionOn = true;// now we go to player class and check tile collisionOn
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1= gp.tileM.mapTileNum[entityRightCol][entityTopRow];// left col bottom solid area
                tileNum2= gp.tileM.mapTileNum[entityRightCol][entityBottomRow];// right col bottom solid area
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    // update the entityCollision on we declared
                    entity.collisionOn = true;// now we go to player class and check tile collisionOn
                }
                break;
        }
    }
}
