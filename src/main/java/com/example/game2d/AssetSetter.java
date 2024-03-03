package com.example.game2d;

import houses.Castle;
import object.OBJ_Door;
import object.treasures.*;
import object.treasures.WoodenS;
import object.treasures.OBJ_Key;
import object.treasures.Paladin_Shield;

public class AssetSetter {
    GamePanel gp;

    //CONSTRUCTOR
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    // create a method to instantiate default objects
    public void setObject(){ //HERE THE OBJECT ARRAY WE CREATED COMES IN PLAY
     //3 keys
     gp.obj[0] = new OBJ_Key(); //OBJ_KEY is A SUBCLASS ITSELF FROM SuperObject
     //THIS MEANS WE WILL HAVE worldX and worldY for each object we create in the array
     gp.obj[0].worldX= 31 * gp.tileSize;// here we locate the location of the object
     gp.obj[0].worldY =  16* gp.tileSize;
     // Create as so for any other object
     gp.obj[1] = new OBJ_Key();
     gp.obj[1].worldX=23 * gp.tileSize;
     gp.obj[1].worldY = 40 * gp.tileSize;

     gp.obj[2] = new OBJ_Key();
     gp.obj[2].worldX=38 * gp.tileSize;
     gp.obj[2].worldY = 8 * gp.tileSize;

     //3 Doors
     gp.obj[3] = new OBJ_Door();
     gp.obj[3].worldX=10 * gp.tileSize;
     gp.obj[3].worldY = 11 * gp.tileSize;

     gp.obj[4] = new OBJ_Door();
     gp.obj[4].worldX=23 * gp.tileSize;
     gp.obj[4].worldY = 7 * gp.tileSize;

     gp.obj[5] = new OBJ_Door();
     gp.obj[5].worldX=32 * gp.tileSize;
     gp.obj[5].worldY = 17 * gp.tileSize;

     /*gp.obj[14] = new OBJ_Door();
     gp.obj[14].worldX=26 * gp.tileSize;
     gp.obj[14].worldY = 17 * gp.tileSize;

     gp.obj[15] = new OBJ_Door();
     gp.obj[15].worldX=18 * gp.tileSize;
     gp.obj[15].worldY = 16 * gp.tileSize;*/ // Additional Doors if needed for markets

     //Treasures
     gp.obj[6] = new WoodenS();
     gp.obj[6].worldX=27 * gp.tileSize;
     gp.obj[6].worldY = 17 * gp.tileSize;

     //CRYSTAL
     gp.obj[7] = new Crystal_Goblet();
     gp.obj[7].worldX= 27 * gp.tileSize;
     gp.obj[7].worldY = 16 * gp.tileSize;

     //Paladin Shield
     gp.obj[8] = new Paladin_Shield();
     gp.obj[8].worldX= 25 * gp.tileSize;
     gp.obj[8].worldY = 16 * gp.tileSize;

     //Golden Goblet
     gp.obj[9] = new Golden_Goblet();
     gp.obj[9].worldX= 19 * gp.tileSize;
     gp.obj[9].worldY = 17 * gp.tileSize;

     //Diamond Ring
     gp.obj[10] = new Diamond_Ring();
     gp.obj[10].worldX= 8 * gp.tileSize;
     gp.obj[10].worldY = 6 * gp.tileSize;

     //Dragon Scroll
     gp.obj[11] = new Dragon_Scroll();
     gp.obj[11].worldX= 25 * gp.tileSize;
     gp.obj[11].worldY = 8 * gp.tileSize;

     //Jewel Sword
     gp.obj[12] = new Jewel_Sword();
     gp.obj[12].worldX= 12 * gp.tileSize;
     gp.obj[12].worldY = 7 * gp.tileSize;
     //Wooden Bow
     gp.obj[13] = new Wooden_Bow();
     gp.obj[13].worldX= 18 * gp.tileSize;
     gp.obj[13].worldY = 18 * gp.tileSize;
     //Castle created with 4 tetris , castle blocks
     //TRAP  Assigned with letter "T" at the moment
     //MARKET(5) done created on actual map with entities
     //EMPTY done
     //WALL(burned) done


    }
}
