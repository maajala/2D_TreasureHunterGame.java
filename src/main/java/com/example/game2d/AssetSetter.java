package com.example.game2d;

import houses.Castle;
import lost_Items.Diamond;
import houses.Market;
import houses.Trap;
import lost_Items.crystalRose;
import lost_Items.moneyBag;
import object.OBJ_Door;
import object.SuperObject;
import object.chest.Chest;
import object.treasures.*;
import object.treasures.WoodenS;
import object.treasures.OBJ_Key;
import object.treasures.Paladin_Shield;
import object.weapons.Axe;
import object.weapons.Cursed_Sword;
import object.weapons.NormalSword;
import object.weapons.fireAxe;

import java.util.Random;

public class AssetSetter extends SuperObject {
 GamePanel gp;

 Random random = new Random();


 //CONSTRUCTOR

 public AssetSetter(GamePanel gp) {
  this.gp = gp;
 }

  //create a method to instantiate default objects

 public void setObject() {
  //HERE THE OBJECT ARRAY WE CREATED COMES IN PLAY
  //3 keys
  gp.obj[0] = new Castle(); //OBJ_KEY is A SUBCLASS ITSELF FROM SuperObject
  //THIS MEANS WE WILL HAVE worldX and worldY for each object we create in the array
  gp.obj[0].worldX = gp.screenWidth / 2; // here we locate the location of the object
  gp.obj[0].worldY = 6 * gp.tileSize;
  // Create as so for any other object
  gp.obj[1] = new Market();
  gp.obj[1].worldX = 0;
  gp.obj[1].worldY = 8 * gp.tileSize;

  //3 Doors
  gp.obj[2] = new OBJ_Door();
  gp.obj[2].worldX = gp.tileSize * 2;
  gp.obj[2].worldY = gp.tileSize * 2;

  //2 More Markets
  gp.obj[3] = new Market();
  gp.obj[3].worldX = 0;
  gp.obj[3].worldY = 6 * gp.tileSize;

  gp.obj[4] = new Market();
  gp.obj[4].worldX = 2 * gp.tileSize;
  gp.obj[4].worldY = 9 * gp.tileSize;
  //Treasures
  gp.obj[5] = new WoodenS();
  gp.obj[5].worldX = 9 * gp.tileSize;
  gp.obj[5].worldY = 7 * gp.tileSize;

  //CRYSTAL
  gp.obj[6] = new Crystal_Goblet();
  gp.obj[6].worldX = 9 * gp.tileSize;
  gp.obj[6].worldY = 6 * gp.tileSize;

  //Paladin Shield
  gp.obj[7] = new Paladin_Shield();
  gp.obj[7].worldX = 5 * gp.tileSize;
  gp.obj[7].worldY = 6 * gp.tileSize;

  //Golden Goblet
  gp.obj[8] = new Golden_Goblet();
  gp.obj[8].worldX = 10 * gp.tileSize;
  gp.obj[8].worldY = 2 * gp.tileSize;

  //Diamond Ring
  gp.obj[9] = new Diamond_Ring();
  gp.obj[9].worldX = 8 * gp.tileSize;
  gp.obj[9].worldY = 6 * gp.tileSize;

  //Dragon Scroll
  gp.obj[10] = new Dragon_Scroll();
  gp.obj[10].worldX = 2 * gp.tileSize;
  gp.obj[10].worldY = 8 * gp.tileSize;

  //Jewel Sword
  gp.obj[11] = new Jewel_Sword();
  gp.obj[11].worldX = 5 * gp.tileSize;
  gp.obj[11].worldY = 7 * gp.tileSize;
  //Wooden Bow
  gp.obj[12] = new Wooden_Bow();
  gp.obj[12].worldX = 8 * gp.tileSize;
  gp.obj[12].worldY = 5 * gp.tileSize;

  for (int i = 13; i < 17; i++) {
   gp.obj[i] = new Trap();
   int currentX = random.nextInt(3, 10) * gp.tileSize;
   int currentY;
   do {
    currentY = random.nextInt(3, 10) * gp.tileSize;
   } while (currentY == 5 * gp.tileSize);
   gp.obj[i].worldX = currentX;
   gp.obj[i].worldY = currentY;
  }
  // Lost Items
  gp.obj[18] = new moneyBag();
  gp.obj[18].worldX = 9 * gp.tileSize;
  gp.obj[18].worldY = 4 * gp.tileSize;

  gp.obj[19] = new Diamond();
  gp.obj[19].worldX = 9 * gp.tileSize;
  gp.obj[19].worldY = 3 * gp.tileSize;

  gp.obj[20] = new crystalRose();
  gp.obj[20].worldX = 3 * gp.tileSize;
  gp.obj[20].worldY = 6 * gp.tileSize;

  gp.obj[21] = new Market();
  gp.obj[21].worldX = 5 * gp.tileSize * 3;
  gp.obj[21].worldY = 6 * gp.tileSize;

  gp.obj[22] = new Market();
  gp.obj[22].worldX = 7 * gp.tileSize;
  gp.obj[22].worldY = 4 * gp.tileSize;

  gp.obj[23] = new Market();
  gp.obj[23].worldX = gp.tileSize * 4;
  gp.obj[23].worldY = 6 * gp.tileSize;

  gp.obj[24] = new OBJ_Key();
  gp.obj[24].worldX = 7 * gp.tileSize;
  gp.obj[24].worldY = 2 * gp.tileSize;

  gp.obj[25] = new OBJ_Key();
  gp.obj[25].worldX = 3 * gp.tileSize;
  gp.obj[25].worldY = 4 * gp.tileSize;

  gp.obj[26] = new OBJ_Key();
  gp.obj[26].worldX = 6 * gp.tileSize;
  gp.obj[26].worldY = 2 * gp.tileSize;

  gp.obj[27] = new Axe();
  gp.obj[27].worldX = 3 * gp.tileSize;
  gp.obj[27].worldY = 2 * gp.tileSize;

  gp.obj[28] = new fireAxe();
  gp.obj[28].worldX = 3 * gp.tileSize;
  gp.obj[28].worldY = 3 * gp.tileSize;

  gp.obj[29] = new Cursed_Sword();
  gp.obj[29].worldX = 3 * gp.tileSize;
  gp.obj[29].worldY = 8 * gp.tileSize;

  gp.obj[30] = new NormalSword();
  gp.obj[30].worldX = 8 * gp.tileSize;
  gp.obj[30].worldY = 9 * gp.tileSize;

  gp.obj[31] = new Chest();
  gp.obj[31].worldX = 8 * gp.tileSize;
  gp.obj[31].worldY = 5 * gp.tileSize;

 }
}

// private final int maxObjects;
//
// public AssetSetter(GamePanel gp) {
//  this.gp = gp;
//  this.maxObjects = 35;
// }
//
// public void setObject() {
//  int attempts;
//
//  for (int i = 0; i < maxObjects; i++) {
//   SuperObject obj = createObject(i); // This needs to be implemented
//   boolean placed = false;
//   attempts = 0;
//
//   while (!placed && attempts < 100) {
//    int x = (i % 2 == 0) ? generateRandomXLeft() : generateRandomXRight(); // Alternate sides
//    int y = generateRandomY();
//
//    if (!isOccupiedCell(x, y)) {
//     obj.worldX = x;
//     obj.worldY = y;
//     placed = true;
//     gp.obj[i] = obj; // Place the object in the game panel array
//    }
//    attempts++;
//   }
//
//   if (attempts >= 100) {
//    System.out.println("Failed to place object " + obj.name + " after 100 attempts.");
//   }
//  }
// }
//
// private boolean isOccupiedCell(int x, int y) {
//  for (SuperObject obj : gp.obj) {
//   if (obj != null && obj.worldX == x && obj.worldY == y) {
//    return true; // Cell is occupied
//   }
//  }
//  return false; // Cell is not occupied
// }
//
// public int generateRandomXLeft(){
//  return random.nextInt(3,5)*gp.tileSize;
// }
// public int generateRandomY(){
//  return random.nextInt(3,12)*gp.tileSize;
// }
// public int generateRandomXRight(){
//  return random.nextInt(6,10)*gp.tileSize;
// }
//
// // Implement this method to instantiate different objects based on the index or other criteria
// public SuperObject createObject(int index) {
//  // Example implementation:
//  // Based on the index, instantiate different objects
//  switch (index) {
//   case 0:
//    return new Castle();
//   case 1:
//    return new Market();
//   case 3:
//    return new Diamond();
//   case 4:
//    return new OBJ_Key();
//   case 5:
//    return new Jewel_Sword();
//   case 6:
//    return new Golden_Goblet();
//   case 7:
//    return new Diamond_Ring();
//   case 8:
//    return new Trap();
//   case 9:
//    return new Diamond();
//   case 10:
//    return new fireAxe();
//   case 11:
//    return new Axe();
//   case 12:
//    return new Market();
//   case 13:
//    return new Market();
//   case 14:
//    return new Market();
//   case 15:
//    return new OBJ_Key();
//   case 16:
//    return new Axe();
//   case 17:
//    return new NormalSword();
//   case 18:
//    return new Wooden_Bow();
//   case 19:
//    return new WoodenS();
//   case 20:
//    return new Cursed_Sword();
//   case 21:
//    return new Trap();
//   case 22:
//    return new Trap();
//   case 23:
//    return new crystalRose();
//   case 24:
//    return new moneyBag();
//   case 25:
//    return new OBJ_Key();
//   case 26:
//    return new Crystal_Goblet();
//   case 27:
//    return new OBJ_Door();
//   case 28:
//    return new Chest();
//   case 29:
//    return new Dragon_Scroll();
//   case 30:
//    return new Market();
//   case 31:
//    return new Market();
//
//   // Add cases for each type of object
//   // ...
//   default:
//    return new SuperObject(); // Replace with a default or error case as appropriate
//   }
// }
//}




//    public void setNPC() throws IOException {
//     gp.npc[0] = new NPC_OldMan(gp);
//     gp.npc[0].worldX = gp.tileSize * 3;
//     gp.npc[0].worldY = gp.tileSize * 2;
//    }
//
//    public void setMonster() throws IOException{
//
//     gp.monster[0] = new MON_GreenSlime(gp);
//     gp.monster[0].worldX = 7* gp.tileSize;
//     gp.monster[0].worldY = 4* gp.tileSize*2;
//     }




//package com.example.game2d;
//
//import houses.Castle;
//import houses.Market;
//import lost_Items.*;
//import object.OBJ_Door;
//import object.SuperObject;
//import object.treasures.*;
//import object.weapons.*;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Random;
//import houses.Trap;
//
//public class AssetSetter extends SuperObject {
// GamePanel gp;
// Random random = new Random();
//
// public AssetSetter(GamePanel gp){
//  this.gp = gp;
//
// }
//
// public void setObject(){
//
//
//  // Define arrays for different types of objects you have
//  Class[] leftSideObjects = {
//          Castle.class,Market.class, OBJ_Door.class,  Market.class,
//          WoodenS.class, Crystal_Goblet.class, Market.class,Paladin_Shield.class, Golden_Goblet.class,
//          Diamond_Ring.class, Dragon_Scroll.class, Jewel_Sword.class, Wooden_Bow.class
//  };
//
//  Class[] rightSideObjects = {
//          Trap.class, moneyBag.class, Diamond.class, crystalRose.class,
//          Market.class, Market.class, Market.class,Trap.class,
//          OBJ_Key.class, Trap.class, OBJ_Key.class,Trap.class,
//          Axe.class, OBJ_Key.class,fireAxe.class, Cursed_Sword.class, NormalSword.class
//  };
//
//  // Randomly shuffle the arrays to randomize object placement
//  Collections.shuffle(Arrays.asList(leftSideObjects), random);
//  Collections.shuffle(Arrays.asList(rightSideObjects), random);
//
//  // Place objects on the left side
//  for (int i = 0; i < leftSideObjects.length; i++) {
//   int x = random.nextInt(2, 6); // Random X for left side
//   int y = random.nextInt(2, 11); // Random Y within the map bounds
//   gp.obj[i] = createObject(leftSideObjects[i], x, y);
//  }
//
//  // Place objects on the right side
//  for (int i = 0; i < rightSideObjects.length; i++) {
//   int x = random.nextInt(6, 12); // Random X for right side
//   int y = random.nextInt(2, 11); // Random Y within the map bounds
//   gp.obj[i + leftSideObjects.length] = createObject(rightSideObjects[i], x, y);
//  }
// }
//
// private SuperObject createObject(Class objClass, int x, int y) {
//
//  // Create an instance of the object class and set its position
//  SuperObject obj = null;
//  try {
//   obj = (SuperObject) objClass.getConstructor().newInstance();
//   obj.worldX = x * gp.tileSize;
//   obj.worldY = y * gp.tileSize;
//
//
//  } catch (Exception e) {
//   e.printStackTrace();
//  }
//  return obj;
// }
//}


