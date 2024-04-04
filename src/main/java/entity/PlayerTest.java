package entity;

import collision.CollisionChecker;
import com.example.game2d.GamePanel;
import com.example.game2d.Keyhandler;
import com.example.game2d.UtilityTool;
import houses.Market;
import lost_Items.Diamond;
import lost_Items.crystalRose;
import lost_Items.moneyBag;
import object.SuperObject;
import object.chest.Chest;
import object.treasures.*;
import object.weapons.Axe;
import object.weapons.Cursed_Sword;
import object.weapons.NormalSword;
import object.weapons.fireAxe;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static object.SuperObject.*;

public class PlayerTest extends Entity{

    //we can delete it we have super in each constructor from Entity
    GamePanel gp;// use game panel created
    Keyhandler keyHand;
    public int screenX;// where we draw player on screen x-axis
    public int screenY; // and y-axis

    // Player Tools
    public int keyCountA=0;//key in pocket
    public double walletA=0;//money at moment
    public double walletB=0;//for player B
    public int maxLife1;//initial health
    public int life1;//varying health
    public int life2;
    public int powerA;//initial power
    public int powerB;
    public int hasWeapons;
    public int hasWeaponsB;
    public int collectedTreasures;
    public int collectedTreasuresB;
    public int hasShield;//SHIELD COUNT
    public int itemFound;//ITEMS FOUND COUNT
    public int winCountPlayer;//WIN COUNT
    public static int counter;//IS A COUNTER FOR THE PLAYER OBJECT
    CollisionChecker collisionChecker;
    public ArrayList<SuperObject> inventory = new ArrayList<>();

    //CURRENT OBJECT FOR MARKET
    public SuperObject currentObject ;
    public Chest currentChest = new Chest();
    //Boolean of Turn
    public boolean isTurn = false ;

    // Collision With Player Boolean
    public boolean collisionWithPlayer = false;
    //MARKET & CHEST COLLISION BOOLEAN
    public boolean collisionWithMarket =false;
    public boolean collisionWithChest =false;

    public String playerSign="";
    public SuperObject currentWeapon;
    public Market market = new Market();
    Dice dice = new Dice();//calling the dice method
    public int steps = 0; // Steps remaining for the player's current turn
    // constructor for player one
    public PlayerTest (GamePanel gp, Keyhandler keyH, CollisionChecker collisionChecker) {
        super(gp);
        this.gp = gp;//we can delete
        this.keyHand = keyH;
        this.collisionChecker = collisionChecker;

        if(counter == 0){
            playerSign = "one";
            //Player Screen Position
            screenX = gp.tileSize; // sub by half to be in the center
            screenY =gp.maxWorldCol/2 * gp.tileSize;;// half-way point of the screen
        }else if(counter == 1){
            playerSign = "two";
            //Player Screen Position
            screenX = gp.tileSize * 12; // sub by half to be in the center
            screenY =gp.maxWorldCol/2 * gp.tileSize;;// half-way point of the screen
        }


        //Solid Area Variables for player
        solidArea = new Rectangle();
        solidArea.x = 8;// changed from 0 to 8 after cut of collision
        solidArea.y = 16;// changed from 0 to 16 after cut // it is optional
        solidArea.width = 44;//changed from tileSize(48) to 32 as the desired cut
        solidArea.height= 46;;
        solidAreaDefaultX = solidArea.x;// default values helpful to retrieve
        solidAreaDefaultY = solidArea.y;// the default values when changing them later


        counter++;
        setDefaultValues();
        try {
            getPlayerImage();
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception or handle it in a way that makes sense for your application.
        }
    }

    //Values to created player
    public void setDefaultValues() {
        // same what we did in GamePanel for player position
        //worldX = gp.tileSize * 23;// starting point , player position in world map // was 23
        //worldY = gp.tileSize * 21;// was 21
        if(playerSign.equals("one")){
            worldX = gp.tileSize;// this is for 10x10 map
            worldY = gp.maxWorldCol/2 * gp.tileSize;
        }else if(playerSign.equals("two")){
            worldX = gp.player1.worldX *12;// this is for 10x10 map
            worldY = gp.player1.worldY;
        }
        speed = 4;
        direction = "down"; // can be chosen any direction as default

        maxLife1=6;//max life to be reached
        life1 = maxLife1;// current life to be used
        life2 = maxLife1;
        //same for both players
        powerA = 5;
        powerB = 5;

        collectedTreasures =0;
        itemFound =0;
        collectedTreasuresB=0;

        hasWeapons =0;
        hasWeaponsB=0;
    }

    //Set Items for the Player
    // Call this method when playerA wins the battle
    public void takeMoneyFromOpponent(PlayerTest opponent) {
        if(this.powerA < opponent.powerB){
            gp.ui.showMessage("Power A less Than Power B");
            return;
        }
        // Calculate the fraction of money to take based on the given formula
        double moneyTaken = (this.powerA - opponent.powerB) / (double)(this.powerA + opponent.powerB) * opponent.walletB;
        // Subtract that money from the opponent's wallet
        opponent.walletB -= moneyTaken;
        this.powerA -= opponent.powerB;// decrease power of winning player
        opponent.powerB=0;
        // Ensure the opponent's wallet does not go negative
        if (opponent.walletB < 0) {
            opponent.walletB = 0;
        }
        // Add the taken money to player A's wallet
        this.walletA += moneyTaken;

        // Will add additional logic to handle what happens if the opponent runs out of money

    }

    // Another Fast Way to Draw or Call PLayers Image
    public void getPlayerImage() throws IOException{
        up1 = setup("player_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("player_up_2",gp.tileSize,gp.tileSize);
        up3 = setup("player_up_3",gp.tileSize,gp.tileSize);
        up4 = setup("player_up_4",gp.tileSize,gp.tileSize);

        down1=setup("player_down_3",gp.tileSize,gp.tileSize);
        down2=setup("player_down_2",gp.tileSize,gp.tileSize);
        down3=setup("player_down_1",gp.tileSize,gp.tileSize);
        down4=setup("player_down_4",gp.tileSize,gp.tileSize);

        right1=setup("player_right_1",gp.tileSize,gp.tileSize);
        right2=setup("player_right_2",gp.tileSize,gp.tileSize);
        right3=setup("player_right_3",gp.tileSize,gp.tileSize);
        right4=setup("player_right_4",gp.tileSize,gp.tileSize);

        left1=setup("player_left_1",gp.tileSize,gp.tileSize);
        left2=setup("player_left_4",gp.tileSize,gp.tileSize);
        left3=setup("player_left_3",gp.tileSize,gp.tileSize);
        left4=setup("player_left_2",gp.tileSize,gp.tileSize);
    }
    // Buffered IMage to scale it as tiles into game tile size screen
    public BufferedImage setup(String imageName , int width , int height){
        //instantiate Utility Tool class to scale
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/"+imageName+".png")));
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    //will be used for battle system
    public void getPlayerAttackImage1(){

        up1 = setup("boy_up_1",gp.tileSize,gp.tileSize*2);
        up2 = setup("boy_up_2",gp.tileSize,gp.tileSize*2);
        up3 = setup("boy_up_1",gp.tileSize,gp.tileSize*2);
        up4 = setup("boy_up_2",gp.tileSize,gp.tileSize*2);

        down1=setup("boy_down_1",gp.tileSize,gp.tileSize*2);
        down2=setup("boy_down_2",gp.tileSize,gp.tileSize*2);
        down3=setup("boy_down_1",gp.tileSize,gp.tileSize*2);
        down4=setup("boy_down_2",gp.tileSize,gp.tileSize*2);

        right1=setup("boy_right_1",gp.tileSize*2,gp.tileSize);
        right2=setup("boy_right_2",gp.tileSize*2,gp.tileSize);
        right3=setup("boy_right_1",gp.tileSize*2,gp.tileSize);
        right4=setup("boy_right_2",gp.tileSize*2,gp.tileSize);

        left1=setup("boy_left_1",gp.tileSize*2,gp.tileSize);
        left2=setup("boy_left_2",gp.tileSize*2,gp.tileSize);
        left3=setup("boy_left_1",gp.tileSize*2,gp.tileSize);
        left4=setup("boy_left_2",gp.tileSize*2,gp.tileSize);
    }


    public void equipWeapon(SuperObject weapon) {
        if (weapon != null) { // Assuming Weapon is a subclass of SuperObject
            this.currentWeapon = weapon;
            inventory.add(weapon);
        } else {
            System.out.println("This item is not there");
        }
    }

    public void equipShield(SuperObject shield) {
        if (shield != null) { // Assuming Shield is a subclass of SuperObject
            this.currentShield = shield;
            inventory.add(shield);
            // Example: Increase defense or provide some benefits
            // This part of the code depends on how you want shields to affect the gameplay.
        } else {
            System.out.println("This item is not a shield.");
        }
    }

    public void update() {

        collisionOn = false;

        // Potential new position
        int newWorldX = worldX;
        int newWorldY = worldY;

        if (keyHand.upPressed) {
            newWorldY -= speed;
            direction = "up";
        } else if (keyHand.downPressed) {
            newWorldY += speed;
            direction = "down";
        } else if (keyHand.leftPressed) {
            newWorldX -= speed;
            direction = "left";
        } else if (keyHand.rightPressed) {
            newWorldX += speed;
            direction = "right";
        }

        // Collision checking
        collisionChecker.checkTile(this);

        // If there is no collision, update the world position
        if (!collisionOn) {
            collisionWithMarket = false; // RESET WHEN NOT COLLIDING
            collisionWithChest = false; // RESET WHEN NOT COLLIDING
            int objectIndex = collisionChecker.checkObject(this, true);// CHECK COLLISION INDEX OBJECT
            if (objectIndex == 999) {//CHANGE THE WORLD X AND Y COMPONENT INTO newWorld ones
                worldX = newWorldX;
                worldY = newWorldY;

            } else {
                // Handle object collision event, e.g., pick up a key
                pickUpObject(objectIndex);// INVOKE THE METHOD OF PICKING OBJECT
            }
        }

        // Then check collision with the other player using their current position and your new potential position
        if(playerSign.equals("one")) {
            collisionWithPlayer = collisionChecker.checkPlayerCollision(this, gp.player2);
        }if(playerSign.equals("two")){
        collisionWithPlayer = collisionChecker.checkPlayerCollision(this,gp.player1);
        }

        // If no collision with the environment or the other player, update position
        // Simplified attack logic for demonstration
        if (collisionWithPlayer) {
            if (playerSign.equals("one") && keyHand.enterPressed) {
                if(this.powerA > gp.player2.powerA){

                    gp.player2.life1 -= currentWeapon.attack; // Player 1 attacks Player 2
                    System.out.println("Player2 life"+gp.player2.life1);
                    if (gp.player2.life1 <= 0) {
                        gp.ui.showMessage("Player 1 won the battle against Player 2");
                        takeMoneyFromOpponent(gp.player2);
                        // Assume respawnPlayer is a method to handle respawning the player
                        respawnPlayer2(gp.player2); // Respawn Player 2
                    }
                }

            } else if (playerSign.equals("two") && keyHand.enterPressed) {
                if(this.powerA > gp.player1.powerA){
                    gp.player1.life1 -= currentWeapon.attack; // Player 2 attacks Player 1
                    if (gp.player1.life1 <= 0) {
                        gp.ui.showMessage("Player 2 won the battle against Player 1");
                        takeMoneyFromOpponent(gp.player1);
                        respawnPlayer1(gp.player1); // Respawn Player 1
                    }
                }

            }
            keyHand.enterPressed = false; // Ensure the key state is reset after the attack
        }else {

        }
//        } else {
//            if(playerSign.equals("one") && keyHand.enterPressed){
//                System.out.println("Attempting to attack");
//                attacking();
//                gp.player2.life1 -=1;
//                if(gp.player2.life1 == 0){
//                    gp.ui.showMessage("Player2 won The Battle ");
//                    takeMoneyFromOpponent(gp.player2);
//                    System.out.println("WalletB =  "+gp.player2.walletA+" WalleA = "+this.walletA);
//
//                }
//            if(keyHand.enterPressed && playerSign.equals("two")){
//                System.out.println("Attempting to attack");
//                attacking();
//                gp.player1.life1 -=1;
//                if(gp.player1.life1 == 0){
//                    gp.ui.showMessage("Player1 won The Battle ");
//                    takeMoneyFromOpponent(gp.player1);
//                    System.out.println("WalletA =  "+gp.player1.walletA+" WalletB = "+this.walletA);
//                }
//            }
//            }
//            System.out.println("Player 1 hits PLayer 2 !");
//            // Handle collision (e.g., stop movement, play sound, etc.)
//        }

        screenX = worldX;
        screenY = worldY;

        // Update sprite animations
        spriteCounter++;
        if (spriteCounter > 12) {// interval of 12
            spriteNum = (spriteNum % 4) + 1; // Cycle through sprite numbers 1 to 4
            // The result is always in the range 0 to 3
            spriteCounter = 0;
        }
        if (isTurn && (keyHand.upPressed || keyHand.downPressed || keyHand.leftPressed || keyHand.rightPressed)) {
            steps--; // Decrease the number of steps as the player moves
            if (steps <= 0) {
                isTurn = false; // End the player's turn when steps run out
            }
        }

    }

    private void updateSpriteAnimation() {
        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = (spriteNum % 4) + 1; // Cycle through sprite numbers 1 to 4
            spriteCounter = 0;
        }
    }

    public void respawnPlayer1(PlayerTest player) {
        // Implement respawn logic based on player sign (player 1 or player 2)
        gp.player1.worldX = gp.tileSize;
        gp.player1.worldY = gp.maxWorldCol / 2 * gp.tileSize;
        gp.player1.life1 = 6; // Reset life
        gp.player1.powerA = 1; // Reset power
    }
     public void respawnPlayer2(PlayerTest player) {
            // Implement respawn logic based on player sign (player 1 or player 2)
                gp.player2.worldX = gp.tileSize*12;
                gp.player2.worldY = gp.maxWorldCol / 2 * gp.tileSize;
                gp.player2.life1 = 6; // Reset life
                gp.player2.powerA = 1; // Reset power

        }


    private void endTurn() {
        isTurn = false; // Mark the turn as ended
        // Additional logic to switch turns or handle turn end can be added here
    }


    //Prepare for other turn to switch
    public void prepareTurn() {
        if (!isTurn) { // Check if it's this player's turn
            int diceResult = dice.roll(); // Roll the dice to determine steps
            steps = diceResult*15; // Set the number of steps the player can take this turn
            System.out.println("The steps here are: "+steps/15+" for player 1");
            isTurn = true; // Mark it as the player's turn
            // Optionally, add any additional logic needed at the start of a turn
        }
    }

    //attacking in action using this method

    public void attacking(){
        spriteCounter++;

        if(spriteCounter <= 5){
            spriteNum =1;
        }
        if(spriteCounter >5 && spriteCounter <= 25){
            spriteNum =2;
        }
        if(spriteCounter >25 && spriteCounter <= 45){
            spriteNum =3;
        }
        if(spriteCounter >45 && spriteCounter <= 65){
            spriteNum =4;
        }
        if(spriteCounter >65){
            spriteNum =1;
            spriteCounter=0;
            attacking = false;
        }

    }

    //INTERACT WITH OBJECTS ON MAP
    public void pickUpObject(int i){
        //Object Being Collected
        if(i !=999)// any index not used in the object array
        {
            String objectName = gp.obj[i].name; // use this String to check our object type
            if(objectName!= null){
            switch (objectName)
            {
                //KEYS
                case "Key"://COLLECTING KEY TO OPEN DOORS
                    keyCountA++;//ADD KEY COUNT TO POCKET
                    gp.obj[i]=null;//REMOVE ITEM
                    inventory.add(new OBJ_Key());//ADD TO INVENTORY
                    gp.ui.showMessage("Payer A got A key!");
                    break;
                    //DOOR
                case "Door":// OPEN DOOR ACCORDING TO THE KEYS WE HAVE
                    if(keyCountA>0)
                    {
                        gp.obj[i] = null;
                        keyCountA--;
                        gp.ui.showMessage("You Opened the Door");
                    }
                    else {
                        gp.ui.showMessage("Need A Key To Open The Door");
                    }

                    break;
                    // TREASURES
                case "Diamond Ring":// Worth 40.25$
                    walletA +=40.25;
                    gp.obj[i] = null;
                    inventory.add(new Diamond_Ring());
                    collectedTreasures++;
                    gp.ui.showMessage("Player wallet has "+walletA+" $");
                    break;
                case "Dragon Scroll":// Worth 25.5$
                    walletA +=25.5;
                    gp.obj[i] = null;
                    inventory.add(new Dragon_Scroll());
                    collectedTreasures++;
                    gp.ui.showMessage("Player has "+walletA+" Dollar(s) now!!");
                    break;
                case "Crystal Goblet":// Worth 45.5$
                    walletA +=45.5;
                    gp.obj[i] = null;
                    collectedTreasures++;
                    inventory.add(new Crystal_Goblet());
                    gp.ui.showMessage("Player has "+walletA+" Dollar(s) now!!");
                    break;
                case "Golden Goblet":// Worth 30.5$
                    walletA +=30.5;
                    gp.obj[i] = null;
                    collectedTreasures++;
                    inventory.add(new Golden_Goblet());
                    gp.ui.showMessage("Player has "+walletA+" Dollar(s) now!!");
                    break;
                    //Shields
                case "Wooden Shield"://Wooden Shield Worth 10$
                    if(walletA >=10.0){
                    gp.obj[i] = null;
                    hasShield++;
                    equipShield(new WoodenS());
                    }else{
                       gp.ui.showMessage("10 Dollars to Purchase");
                    }
                    break;
                case "Paladin Shield": // Price: 15.5$
                    if(walletA >=15.5) {
                        walletA -=15.5;
                        hasShield++;
                        equipShield(new Paladin_Shield());
                        gp.obj[i] = null;
                        gp.ui.showMessage("Player equipped a Paladin Shield now");
                    }
                    else{
                        gp.ui.showMessage("15.5$ Dollars to Purchase");
                    }
                    break;
                    //Castle
                case "Castle":
                    if(keyCountA==3){
                        gp.ui.gameFinished = true;
                        winCountPlayer++;
                        keyCountA -=3;
                        gp.obj[i]=null;
                        this.winnerPlayer = true;
                        if(playerSign.equals("one")){
                            gp.ui.showMessage("Player1 WON !");
                        }
                        if(playerSign.equals("two")){
                            gp.ui.showMessage("Player2 WON !");
                        }

                    }else {
                        gp.ui.showMessage("Get 3 keys to WIN");
                    }
                    break;
                    //Weapons
                case "Jewel Sword"://Price: 27.5$
                    if(walletA >=27.5) {
                        walletA -=27.5;
                        gp.obj[i] = null;
                        hasWeapons++;
                        equipWeapon(new Jewel_Sword());
                        System.out.println("Current Weapon is "+ this.currentWeapon.name);
                        powerA++;
                        gp.ui.showMessage("Player equipped a Jewel Sword now");
                    }
                    break;
                case "Axe":
                    if(walletA>=40)
                    {
                        walletA -=40;
                        gp.obj[i] = null;
                        hasWeapons++;
                        equipWeapon(new Axe());
                        powerA +=2;
                        gp.ui.showMessage("Player got a Axe!");
                    }else{
                        gp.ui.showMessage("40 Dollars to Purchase");
                    }
                    break;
                case "Fire Axe"://45$
                    if(walletA>=45){
                        walletA -= 45;
                        gp.obj[i] = null;
                        hasWeapons++;
                        equipWeapon(new fireAxe());
                        powerA+=3;
                        gp.ui.showMessage("Player got a Fire Axe!");
                    }else{
                        gp.ui.showMessage("45 Dollars to Purchase");
                    }
                    break;
                case "Cursed Sword"://worth 55.5
                    if(walletA>=55.5){
                        walletA -=55.5;//pay for it
                        gp.obj[i] = null;
                        hasWeapons++;
                        equipWeapon(new Cursed_Sword());
                        powerA+=2;
                        gp.ui.showMessage("Player got a Cursed Sword!");
                    }else{
                        gp.ui.showMessage("55.5 Dollars to Purchase");
                    }
                    break;
                case "Normal Sword"://25.5$
                    if(walletA>=25.5){
                        walletA -=25.5;
                        gp.obj[i] = null;
                        hasWeapons++;
                        equipWeapon(new NormalSword());
                        powerA++;
                        gp.ui.showMessage("Player got a Sword!");
                    }else{
                        gp.ui.showMessage("25.5 Dollars to Purchase");
                    }
                    break;
                case "Wooden Bow":// Price: 12.25$
                    if(walletA >=12.25) {
                        walletA -=12.5;
                        hasWeapons++;
                        equipWeapon(new Wooden_Bow());
                        powerA++;
                        gp.obj[i] = null;
                        gp.ui.showMessage("Player equipped a Wooden Bow now");
                    }else{
                        gp.ui.showMessage("12.5 Dollars to Purchase");
                    }
                    break;
                    //TRAP
                case "Trap":
                    gp.obj[i] = null;
                    this.life1 --;
                    gp.ui.showMessage("Player Went Into A TRAP");
                    break;
                    //LOST ITEMS
                case "Crystal Rose"://Lost Items
                    gp.obj[i] = null;
                    itemFound++;
                    inventory.add(new crystalRose());
                    gp.ui.showMessage("Lost Item Found");
                    break;
                case "Badge"://Lost Items
                    gp.obj[i] = null;
                    itemFound++;
                    gp.ui.showMessage("Lost Item Found");
                    break;

                case "Diamond"://Lost Items //Worth 40$
                    gp.obj[i] = null;
                    itemFound++;
                    inventory.add(new Diamond());
                    walletA +=40;
                    System.out.println(itemFound);
                    gp.ui.showMessage("Lost Item Found");
                    break;

                case "Money Bag"://Lost Items Worth 35$
                    gp.obj[i] = null;
                    itemFound++;
                    walletA += 35;
                    inventory.add(new moneyBag());
                    gp.ui.showMessage("Lost Item Found");
                    break;
                case "Market"://Interact With market
                    collisionWithMarket = true;
                    currentObject = gp.obj[i];
                    break;
                case "Chest":
                    collisionWithChest = true;
                    if(gp.ui.collectedT){
                       gp.obj[i].image = gp.obj[i].image2;
                       gp.ui.collectedT = false;
//                       gp.obj[i] = null;
                    }
                    break;

            }

        }
        }

    }
    //Select Items Now
    public void selectItem(){ // We call this method by pressing ENTER
        int itemIndex;
        //CHECK CASES FOR PLAYERS
        if(playerSign.equals("one")){
            //METHOD CREATED IN UI CLASS TO GET THE INDEX OF THE SLOT IN THE WINDOW ITEMS
            itemIndex = gp.ui.getItemIndexFromPlayer1();
            if(itemIndex < inventory.size()){
                //select items from SuperObject
                SuperObject selectedItem = inventory.get(itemIndex);
                // check type of object
                if(selectedItem.type == type_sword|| selectedItem.type == type_axe
                        || selectedItem.type == type_bow){
                    currentWeapon = selectedItem;
                }
                if(selectedItem.type == type_shield){
                    currentShield = selectedItem;
                }
                if(selectedItem.type == type_consumable){

                }
            }
        }
        if(playerSign.equals("two")){
            itemIndex = gp.ui.getItemIndexFromPlayer2();
            if(itemIndex < inventory.size()){
                //select items from SuperObject
                SuperObject selectedItem = inventory.get(itemIndex);
                // check type of object
                if(selectedItem.type == type_sword|| selectedItem.type == type_axe
                        || selectedItem.type == type_bow){
                    currentWeapon = selectedItem;
                }
                if(selectedItem.type == type_shield){
                    currentShield = selectedItem;
                }
            }
        }

    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(attacking == false){
                    image = spriteNum == 1 ? up1 : spriteNum == 2 ? up2 : spriteNum == 3 ? up3 : up4;
                }
                if(attacking == true){
                    if(spriteNum == 1){ image = attack_up1;}
                    if(spriteNum == 2){ image = attack_up2;}
                }
//            image = spriteNum == 1 ? up1 : spriteNum == 2 ? up2 : spriteNum == 3 ? up3 : up4;
                break;
            case "down":
                if(attacking==false){
                    image = spriteNum == 1 ? down1 : spriteNum == 2 ? down2 : spriteNum == 3 ? down3 : down4;
                }
                if(attacking== true){
                    if(spriteNum == 1){ image = attack_down1;}
                    if(spriteNum == 2){ image = attack_down2;}
                }
                break;
            case "left":
                if(attacking==false){
                    image = spriteNum == 1 ? left1 : spriteNum == 2 ? left2 : spriteNum == 3 ? left3 : left4;
                }
                if(attacking== true){
                    if(spriteNum == 1){ image = attack_left1;}
                    if(spriteNum == 2){ image = attack_left2;}
                }
                break;
            case "right":
                if(attacking == false){
                    image = spriteNum == 1 ? right1 : spriteNum == 2 ? right2 : spriteNum == 3 ? right3 : right4;
                }
                if(attacking== true){
                    if(spriteNum == 1){ image = attack_right1;}
                    if(spriteNum == 2){ image = attack_right2;}
                }
                break;

        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }


}