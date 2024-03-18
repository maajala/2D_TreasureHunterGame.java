package com.example.game2d;

import entity.NPC_OldMan;
import houses.Castle;
import lost_Items.Diamond;
import lost_Items.Lost;
import houses.Market;
import houses.Trap;
import lost_Items.crystalRose;
import lost_Items.moneyBag;
import monster.MON_GreenSlime;
import object.Heart;
import object.OBJ_Door;
import object.SuperObject;
import object.treasures.*;
import object.treasures.WoodenS;
import object.treasures.OBJ_Key;
import object.treasures.Paladin_Shield;

import java.io.IOException;
import java.util.Random;

public class AssetSetter extends SuperObject {
    GamePanel gp;

    Random random = new Random();
    //CONSTRUCTOR
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    // create a method to instantiate default objects
    public void setObject(){
     //HERE THE OBJECT ARRAY WE CREATED COMES IN PLAY
     //3 keys
     gp.obj[0] = new Castle(); //OBJ_KEY is A SUBCLASS ITSELF FROM SuperObject
     //THIS MEANS WE WILL HAVE worldX and worldY for each object we create in the array
     gp.obj[0].worldX= 5 * gp.tileSize;// here we locate the location of the object
     gp.obj[0].worldY =  5* gp.tileSize;
     // Create as so for any other object
     gp.obj[1] = new Market();
     gp.obj[1].worldX= 0;
     gp.obj[1].worldY = 8 * gp.tileSize;

     //3 Doors
     gp.obj[2] = new OBJ_Door();
     gp.obj[2].worldX=gp.tileSize * 2;
     gp.obj[2].worldY = gp.tileSize * 2;

     //2 More Markets
     gp.obj[3] = new Market();
     gp.obj[3].worldX= 0;
     gp.obj[3].worldY = 6 * gp.tileSize;

     gp.obj[4] = new Market();
     gp.obj[4].worldX= 2*gp.tileSize;
     gp.obj[4].worldY = 9 * gp.tileSize;
     //Treasures
     gp.obj[5] = new WoodenS();
     gp.obj[5].worldX=9 * gp.tileSize;
     gp.obj[5].worldY = 7 * gp.tileSize;

     //CRYSTAL
     gp.obj[6] = new Crystal_Goblet();
     gp.obj[6].worldX= 9 * gp.tileSize;
     gp.obj[6].worldY = 6 * gp.tileSize;

     //Paladin Shield
     gp.obj[7] = new Paladin_Shield();
     gp.obj[7].worldX= 5 * gp.tileSize;
     gp.obj[7].worldY = 6 * gp.tileSize;

     //Golden Goblet
     gp.obj[8] = new Golden_Goblet();
     gp.obj[8].worldX= 10 * gp.tileSize;
     gp.obj[8].worldY =  2*gp.tileSize;

     //Diamond Ring
     gp.obj[9] = new Diamond_Ring();
     gp.obj[9].worldX= 8 * gp.tileSize;
     gp.obj[9].worldY = 6 * gp.tileSize;

     //Dragon Scroll
     gp.obj[10] = new Dragon_Scroll();
     gp.obj[10].worldX= 2 * gp.tileSize;
     gp.obj[10].worldY = 8 * gp.tileSize;

     //Jewel Sword
     gp.obj[11] = new Jewel_Sword();
     gp.obj[11].worldX=5* gp.tileSize;
     gp.obj[11].worldY = 7 * gp.tileSize;
     //Wooden Bow
     gp.obj[12] = new Wooden_Bow();
     gp.obj[12].worldX= 8 * gp.tileSize;
     gp.obj[12].worldY = 5 * gp.tileSize;

     for(int i=13; i<17; i++){
      gp.obj[i] = new Trap();
      int currentX = random.nextInt(3,10)*gp.tileSize;
      int currentY;
      do{
       currentY= random.nextInt(3,10)* gp.tileSize;
      }while (currentY == 5*gp.tileSize);
      gp.obj[i].worldX = currentX;
      gp.obj[i].worldY = currentY;
     }
     // Lost Items
     gp.obj[18] = new moneyBag();
     gp.obj[18].worldX= 9 * gp.tileSize;
     gp.obj[18].worldY = 4 * gp.tileSize;

     gp.obj[19] = new Diamond();
     gp.obj[19].worldX= 9 * gp.tileSize;
     gp.obj[19].worldY = 3 * gp.tileSize;

     gp.obj[20] = new crystalRose();
     gp.obj[20].worldX= 3 * gp.tileSize;
     gp.obj[20].worldY = 6 * gp.tileSize;

     gp.obj[21] = new Market();
     gp.obj[21].worldX= 5* gp.tileSize * 3;
     gp.obj[21].worldY = 6 * gp.tileSize;

     gp.obj[22] = new Market();
     gp.obj[22].worldX= 7* gp.tileSize;
     gp.obj[22].worldY = 4 * gp.tileSize;

     gp.obj[23] = new Market();
     gp.obj[23].worldX= gp.tileSize * 4;
     gp.obj[23].worldY = 6 * gp.tileSize;

     gp.obj[24] = new OBJ_Key();
     gp.obj[24].worldX=7 * gp.tileSize;
     gp.obj[24].worldY = 2 *gp.tileSize;

     gp.obj[25] = new OBJ_Key();
     gp.obj[25].worldX=3 * gp.tileSize;
     gp.obj[25].worldY = 4* gp.tileSize;

     gp.obj[26] = new OBJ_Key();
     gp.obj[26].worldX=  2*gp.tileSize;
     gp.obj[26].worldY = 4* gp.tileSize;
    }

//    public void setNPC() throws IOException {
//     gp.npc[0] = new NPC_OldMan(gp);
//     gp.npc[0].worldX = gp.tileSize * 3;
//     gp.npc[0].worldY = gp.tileSize * 2;
//    }

//    public void setMonster() throws IOException{
//
//     gp.monster[0] = new MON_GreenSlime(gp);
//     gp.monster[0].worldX = 7* gp.tileSize;
//     gp.monster[0].worldY = 4* gp.tileSize*2;
//
//    }
}
