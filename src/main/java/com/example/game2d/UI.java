package com.example.game2d;

import entity.*;
import houses.Market;
import object.Heart;
import object.SuperObject;
import object.chest.Chest;
import object.treasures.OBJ_Key;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class UI {
    //HINT to remember , do not instantiate any sub method in the draw method as  it will take time to progress in runtime
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_60B;//font to be used for Counting keys
    BufferedImage keyImage;// For Key Image
    BufferedImage heart_full, heart_half, heart_blank; // Heart Images
    //message field
    public boolean messageOn = false;
    int messageCounter = 0;
    public String message = "";// message to be used inorder to be displayed

    //Slot INFOS for WINDOW REGARDING INVENTORY
    public int slotCol = 0;
    public int slotCol2=0;
    public int slotRow = 0;
    public int marketSlotCol =0;
    public  int marketSlotRow =0;
    public int slotRow2=0;
    //CREATE A MARKET
    public Market market = new Market();
    public Chest chest = new Chest();
   ArrayList <SuperObject> currentList = new ArrayList<>();
    //CURRENT ITEM BEING POINTED IN MARKET
    public SuperObject currentItem;
    //Player WHO IS IN ACTION
    public PlayerTest playerCollideWithMarket;
    public PlayerTest playerCollideWithChest;

    //SubSTATE INTEGER
    public int subState;

    //Flags for the State of Market Options
    public static final int SELECT_STATE = 0;
    public static final int BUY_STATE = 1;
    public static final int SELL_STATE = 2;

    //Command Number Acts like a cursor
    public int commandNum;//Just Flag for the cursor navigation
    //boolean for collection
    public boolean collectedT=false;

    // TIME Taken
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.0");//display format to 2 decimal places
    public boolean gameFinished = false;
    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial",Font.PLAIN, 40);// called here better than calling from draw method
        arial_60B = new Font("Arial",Font.BOLD, 60);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;

        // Create HUD Object
        SuperObject heart = new Heart();
        heart_blank= heart.image;
        heart_half = heart.image2;
        heart_full = heart.image3;
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState){
          //  do play stuff later
                    if(gameFinished == true){
            // method to display text on screen for update
            g2.setFont(arial_40);
            g2.setColor(Color.white);// color of Font
            // need to find my String length to display correctly
            String text;
            int textLength;
            int x;
            int y;
            String name = "";
            if(gp.player1.winnerPlayer)
                name = "Player1";
            else
                if(gp.player2.winnerPlayer)
                    name = "Player2";

            text = name+" Found the Treasure!";  // this down here returns the length of text length
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x=gp.screenWidth/2 - textLength/2; //alligned to center
            y = gp.screenHeight/2 - (gp.tileSize*3);
            // now draw the string
            g2.drawString(text,x,y);

            // FONT COLOR
            g2.setFont(arial_60B);
            g2.setColor(Color.YELLOW);// color of Font
            text = "Congratulations to "+name+"!";  // this down here returns the length of text length
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x=gp.screenWidth/2 - textLength/2; //aligned to center
            y = gp.screenHeight/2 + (gp.tileSize*2);
            // now draw the string
            g2.drawString(text,x,y);


            //Stop The game
            gp.gameThread = null;

        }else{// IF GAME IS NOT FINISHED YET DRAW NORMALLY ON SCREEN THE INFOS
            // method to display text on screen for update
            g2.setFont(arial_40);//FONT USED TO DRAW
            g2.setColor(Color.white);// color of Font
            //For Player 1 KEY and DICE
            g2.drawImage(keyImage,gp.tileSize/2+10,gp.tileSize/2,gp.tileSize,gp.tileSize,null);//coordinates of the key on screen
            g2.drawString("x "+gp.player1.keyCountA, 100, 80);
            g2.drawString("#DICE: "+gp.player1.steps/15,100,gp.tileSize*2-5);
            g2.drawString("$:"+dFormat.format(gp.player1.walletA),gp.tileSize/2,gp.tileSize*5);
          //  g2.drawString("power:"+gp.player1.powerA,gp.tileSize,gp.tileSize*13);
            //SAME CONCEPT FOR WALLET FOR A AND B


            //For Player 2
            g2.drawImage(keyImage,gp.tileSize*12-10,gp.tileSize/2,gp.tileSize,gp.tileSize,null);//coordinates of the key on screen
            g2.drawString(gp.player2.keyCountA+" x", gp.tileSize*11, 80);
            g2.drawString("#DICE:"+gp.player2.steps/15,gp.tileSize*9,gp.tileSize*2-5);
            g2.drawString(dFormat.format(gp.player2.walletA)+":$",gp.tileSize*12,gp.tileSize*5);
           // g2.drawString("power:"+gp.player2.powerB,gp.tileSize*9,gp.tileSize*13);

            // Buttons to Press Inorder to Act Upon
            g2.drawString("C: Character",0,gp.tileSize*13);
            g2.drawString("I: Items",gp.tileSize*5 - 24,gp.tileSize*13);
            g2.drawString("M: Market",gp.tileSize*8 - 24,gp.tileSize*13);
            g2.drawString("P: Pause",gp.tileSize*11,gp.tileSize*13);

            //TIME TAKEN
            playTime += (double)1/60;// we divide by our FPS which is 60
            g2.drawString("Time: "+dFormat.format(playTime), gp.tileSize*5,gp.tileSize);

            // Player1 Life
            drawPlayerLife1(g2);
            //Player2 Life
            drawPlayerLife2(g2);

            //Message For Heads Up
            if(messageOn == true){
                // here we just changed our created font size to 30
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message,gp.tileSize*4,gp.tileSize*6);
                // I want the msg to disappear after a definite time
                messageCounter++;
                if(messageCounter > 110){//120 FPS then 2 seconds since its 60 FPS
                    messageCounter=0;
                    messageOn =false;
                }
            }

        }
        }

        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
        }
        if(gp.gameState == gp.inventoryState){
            drawInventory();
        }
        if(gp.gameState == gp.marketState){
            drawMarketScreen();
        }

    }
    //DRAW CHARACTER SCREEN(STATUS BOARD
    public void drawCharacterScreen(){
        //CREATE A FRAME PLAYER 1
        final int frameX = gp.tileSize/5;
        final int frameY = gp.tileSize/3;
        final int frameWidth = gp.tileSize * 6;
        final int frameHeight = gp.tileSize * 6;

        final int frameWidthG = gp.tileSize * 8;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //CREATE A FRAME PLAYER 2
        final int frameX2 = gp.tileSize *7;
        drawSubWindow(frameX2, frameY, frameWidth, frameHeight);

        //CREATE A General FRAME
        final int frameCenterX = gp.tileSize * 3;
        final int frameCenterY = gp.tileSize *6 + 20;
        drawSubWindow(frameCenterX, frameCenterY, frameWidthG, frameHeight);


        //TEXT FOR PLAYER 1 and 2 and General
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20; // little to the right, adjustable for the x coordinate FOR PLAYER 1
        int textX2 = frameX2 + 20; // FOR PLAYER 2
        int textGeneralX = frameCenterX + 20; //FOR GENERAL SCREEN STATUS
        int textGeneralY = frameCenterY + gp.tileSize;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 50; // same as Font size



        //STATUS BOARD
        //1st line
        g2.drawString("PowerA", textX, textY);
        g2.drawString("PowerB", textX2, textY);
        g2.drawString("Elapsed Time(s)",textGeneralX,textGeneralY);
        textY += lineHeight;
        textGeneralY +=lineHeight;

        //2nd line
        g2.drawString("WalletA($)", textX, textY);
        g2.drawString("WalletB($)", textX2, textY);
        g2.drawString("Treasures Found(T)",textGeneralX,textGeneralY);
        textY += lineHeight;
        textGeneralY +=lineHeight;

        //3rd line
        g2.drawString("#Treasures Found", textX, textY);
        g2.drawString("#Treasures Found", textX2, textY);
        g2.drawString("Player Turn",textGeneralX,textGeneralY);
        textY += lineHeight;
        textGeneralY +=lineHeight;

        //4th line
        g2.drawString("Keys", textX, textY);
        g2.drawString("Keys", textX2, textY);
        textY += lineHeight;
        //5th line
        g2.drawString("#Weapons", textX, textY);
        g2.drawString("#Weapons", textX2, textY);
        textY += lineHeight;
        //6th line
        g2.drawString("#Shield", textX, textY);
        g2.drawString("#Shield", textX2, textY);
        textY += lineHeight;
        //7th line
        g2.drawString("#Lost Item", textX, textY);
        g2.drawString("#Lost Item", textX2, textY);
        g2.drawString("Lost Items(T)",textGeneralX,textGeneralY);

        // VALUES TO BE ON TEXT
        int tailX =(frameX + frameWidth) - 30;//tail to catch the right side if the Board
        int tailX2 =(frameX2 + frameWidth) - 30;
        int tailXGeneral = (frameCenterX + frameWidthG)-30;//

        //RESET textY
        textY = frameY + gp.tileSize;
        textGeneralY = frameCenterY + gp.tileSize;

        String value, value2, valueGeneral;

        //First LINE of Type
        value = String.valueOf(gp.player1.powerA);//Value Concept to ge the value of integer into String text
        value2 = String.valueOf(gp.player2.powerA);
        valueGeneral = String.valueOf(dFormat.format(playTime));

        textX = getXForAlignToRightText(value , tailX);//TEXT X POSITION TO TYPE ON
        textX2 = getXForAlignToRightText(value2,tailX2);// TEXT Y POSITION TO TYPE ON
        textGeneralX = getXForAlignToRightText(valueGeneral,tailXGeneral);// FOR THE GENERAL BOARD
        //THEN WE DRAW
        g2.drawString(value, textX, textY);
        g2.drawString(value2, textX2, textY);
        g2.drawString(valueGeneral, textGeneralX, textGeneralY);
        textY += lineHeight;//  WE SKIP TO NEW LINE AFTER EACH LINE
        textGeneralY +=lineHeight;//WE SKIP TO NEW LINE AS PRESSING ENTER KEY

        //Second Line Of Type
        value = String.valueOf(dFormat.format(gp.player1.walletA));//SOLVE THE DFormat Problem here
        value2 = String.valueOf(dFormat.format(gp.player2.walletA));
        valueGeneral = String.valueOf(gp.player1.collectedTreasures + gp.player2.collectedTreasures);

        textX = getXForAlignToRightText(value , tailX);
        textX2 = getXForAlignToRightText(value2,tailX2);
        textGeneralX = getXForAlignToRightText(valueGeneral,tailXGeneral);
        //THEN WE DRAW
        g2.drawString(value, textX, textY);
        g2.drawString(value2, textX2, textY);
        g2.drawString(valueGeneral, textGeneralX, textGeneralY);
        textY += lineHeight;
        textGeneralY += lineHeight;

        //3rd Line oF TExt
        value = String.valueOf(gp.player1.collectedTreasures);//collected treasures in player1
        value2 = String.valueOf(gp.player2.collectedTreasures);//collected treasures in player2
        valueGeneral = gp.playerTurnName;//Get player name from the turns boolean in GamePanel

        textX = getXForAlignToRightText(value,tailX);
        textX2 = getXForAlignToRightText(value2,tailX2);
        textGeneralX = getXForAlignToRightText(valueGeneral,tailXGeneral);
        //THEN WE DRAW
        g2.drawString(value, textX, textY);
        g2.drawString(value2, textX2, textY);
        g2.drawString(valueGeneral, textGeneralX, textGeneralY);
        textY += lineHeight;
        textGeneralY +=lineHeight;

        // 4th line of text
        value = String.valueOf(gp.player1.keyCountA);
        value2 = String.valueOf(gp.player2.keyCountA);

        textX = getXForAlignToRightText(value , tailX);
        textX2 = getXForAlignToRightText(value2,tailX2);
        //THEN WE DRAW
        g2.drawString(value, textX, textY);
        g2.drawString(value2, textX2, textY);
        textY += lineHeight;

        //Fifth Line of text
        value = String.valueOf(gp.player1.hasWeapons);
        value2 = String.valueOf(gp.player2.hasWeapons);

        textX = getXForAlignToRightText(value , tailX);
        textX2 = getXForAlignToRightText(value2,tailX2);
        //THEN WE DRAW
        g2.drawString(value, textX, textY);
        g2.drawString(value2, textX2, textY);
        textY += lineHeight;


        //6th Line
        value = String.valueOf(gp.player1.hasShield);
        value2 = String.valueOf(gp.player2.hasShield);

        textX = getXForAlignToRightText(value , tailX);
        textX2 = getXForAlignToRightText(value2,tailX2);
        //THEN WE DRAW
        g2.drawString(value, textX, textY);
        g2.drawString(value2, textX2, textY);
        textY +=lineHeight;

        //7th Line
        value = String.valueOf(gp.player1.itemFound);
        value2 = String.valueOf(gp.player2.itemFound);
        valueGeneral = String.valueOf(gp.player1.itemFound+gp.player2.itemFound);

        textX = getXForAlignToRightText(value , tailX);
        textX2 = getXForAlignToRightText(value2,tailX2);
        textGeneralX = getXForAlignToRightText(valueGeneral,tailXGeneral);
        //THEN WE DRAW
        g2.drawString(value, textX, textY);
        g2.drawString(value2, textX2, textY);
        g2.drawString(valueGeneral, textGeneralX, textGeneralY);

    }
    //DRAW PAUSE SCREEN
    public void drawPauseScreen(){
        String text = "Paused";

        int x=gp.tileSize*6 ; //alligned to center

        int y = gp.tileSize*7;

        g2.drawString(text, x, y);
    }
    public void drawDialogue(String currentDialogue){
        //Window for display
        int x = gp.tileSize *2;
        int y= gp.tileSize /2;
        int width= gp.screenWidth-(gp.tileSize *7);
        int height = gp.tileSize * 4;
        drawSubWindow(x ,y , width,  height);

        // choose the font of the dialogue
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));//MARUMONICA TYPE
        x += gp.tileSize-24;
        y += gp.tileSize +14;

        // type here your text
        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += gp.tileSize;
        }
    }

    //Handling input
    public void handleInput(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_B: // Assuming 'B' for buy
                subState = BUY_STATE;
                break;
            case KeyEvent.VK_S: // Assuming 'S' for sell
                subState = SELL_STATE;
                break;
            case KeyEvent.VK_DELETE: // Go back via DELETE to select state
                subState = SELECT_STATE;
                break;
        }
    }
    //DRAW THE MARKET SCREEN (TO PURCHASE ITEMS)
    public void drawMarketScreen(){

        switch (subState){//Chose from menu via subState
            case SELECT_STATE: trade_select();break;
            case BUY_STATE: trade_buy();break;
            case SELL_STATE: trade_sell(); break;
            default:
                System.out.println("error Occurred here");
                break;
        }
        gp.keyH.selectPressed= false;
    }
    //SUB STATES FOR MARKET SCREEN DOWN HERE

    //DRAW INVENTORY SCREEN
    public void trade_select(){
        //DRAW DIALOGUE SCREEN
        drawDialogue("Hi There\nChoose From THESE\nOptions!");
        //Draw Window
        int x = gp.tileSize * 6;//x-Component of Window
        int y = gp.tileSize * 4;//y-Component of Window
        int width = gp.tileSize *3;//Width of the window
        int height = (int)(gp.tileSize * 3.5);//height of the window
        drawSubWindow(x, y, width, height);


        //DRAWING A STRING Inside the Market SELECT Window
        x += gp.tileSize;
        y += gp.tileSize;

        // FOR BUY OPTION STRING
        g2.drawString("BUY", x,y);
        // if statements for these options
        if(commandNum == 0){
            g2.drawString(">", x-24,y);
            //CHECK IF THE PLAYER PRESSED ENTER
            if(gp.keyH.selectPressed == true){
                subState = BUY_STATE;
            }
        }
        y += gp.tileSize; // ACTS AS NEW LINE

        // FOR SELL OPTION STRING
        g2.drawString("SELL", x,y);
        if(commandNum == 1){
            g2.drawString(">", x-24,y);
            //CHECK IF THE PLAYER PRESSED ENTER
            if(gp.keyH.selectPressed == true){
                subState = SELL_STATE;
            }
        }
        y += gp.tileSize; // ACTS AS NEW LINE

        // FOR LEAVE OPTION STRING
        g2.drawString("LEAVE", x,y);
        if(commandNum == 2){
            g2.drawString(">", x-24,y);
            //CHECK IF THE PLAYER PRESSED ENTER
            if(gp.keyH.selectPressed == true){
                commandNum = 0;
                gp.gameState = gp.playState;
            }
        }
    }
    public void trade_buy(){
        if(gp.player1.collisionWithMarket)
            playerCollideWithMarket = gp.player1;
        else{
            playerCollideWithMarket = gp.player2;
        }
        drawInventoryMarket(playerCollideWithMarket);

    }
    public void trade_sell(){

    }

    public void drawInventoryMarket(PlayerTest player){
        //Draw Screen For MARKET WINDOW ITEMS
        int frameX = gp.tileSize * 2;
        int frameY = gp.tileSize*2;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        //DEFINE UNIFORM SLOT SIZE FOR ALL SLOTS
        int slotSize = gp.tileSize + 3;// SO WHEN WE CHANGE OUR SLOT SIZE OR WHY WE USE IT

        // Slot For MARKET
        final int slotXStart = frameX + 20;// for slot position frame x and
        final int slotYStart = frameY + 20;// position frame y
        int marketSlotX = slotXStart;// x slot
        int marketSlotY = slotYStart;// y slot

        //DRAW WHAT IS INSIDE THE MARKET
        for(int i = 0; i<market.marketItems.size(); i++){

            //EQUIP CURSOR BY DRAWING AROUND THE CURRENT ITEM

            //Scan one by one and draw the gotten image of the ITEM picked up
            g2.drawImage(market.marketItems.get(i).image,marketSlotX,marketSlotY,null);

            marketSlotX += slotSize;// We need to check our position in the next line
            if(i == 4 || i == 9 || i == 14){// WE HAVE 4X5 MATRIX START FROM 0
                // WE NEED TO CHECK INDICES
                marketSlotX = slotXStart;
                marketSlotY += slotSize;
            }
        }//END OF LOOP

        //Cursor for The MARKET NAVIGATION
        int cursorX = slotXStart +(slotSize * marketSlotCol);
        int cursorY = slotYStart + (slotSize * marketSlotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        //Draw Cursor inventory
        g2.setColor(Color.white);// color white
        g2.setStroke(new BasicStroke(3));// METHOD For thickness
        g2.drawRoundRect(cursorX, cursorY,cursorWidth,cursorHeight,10,10);//draw now

        //DESCRIPTION FRAME FOR TOOLS This block of code is optional
        int dFrameX = frameX;//EASY CONCEPT AS ANY OTHER WINDOW DRAWN BEFORE
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize*2;
        //Draw DESCRIPTION TEXT HERE
        int textX = dFrameX +20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));

        //GET THE ITEM FROM THIS INDEX SLOT LATER
        int itemIndex = getItemIndexFromMarket();

        if(itemIndex < market.marketItems.size()){//If there is an Item
            System.out.println("Item index market now:"+itemIndex);

            //DISPLAY THE DESCRIPTION WINDOW
            drawSubWindow(dFrameX, dFrameY, dFrameWidth,dFrameHeight);

            for(String line : market.marketItems.get(itemIndex).description.split("\n")){
                g2.drawString(line,textX,textY);
                textY +=32;//as new line for each entry of Writing the string
            }
        }
    }
    public void drawInventory(){
        //Draw Screen For Inventory For Player 2
        int frameX = gp.tileSize * 8;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        //Draw Screen For Inventory For Player 1
        int frameX2 = 0;
        int frameY2 = gp.tileSize;
        drawSubWindow(frameX2,frameY2,frameWidth,frameHeight);

        //DEFINE UNIFORM SLOT sIZE FOR ALL SLOTS
        int slotSize = gp.tileSize + 3;// SO WHEN WE CHANGE OUR SLOT SIZE OR WHY WE USE IT

        // Slot For Player 2
        final int slotXStart = frameX + 20;// for slot position frame x and
        final int slotYStart = frameY + 20;// position frame y
        int slotX = slotXStart;// x slot
        int slotY = slotYStart;// y slot

        // Slot For player 1
        final int slotXStart2 = frameX2 + 20;// for slot position frame x and
        final int slotYStart2 = frameY2 + 20;// position frame y
        int slotX2 = slotXStart2;// x slot
        int slotY2 = slotYStart2;// y slot


        //DRAW PLAYER's ITEMS for PLayer 1
        for(int i=0; i<gp.player1.inventory.size(); i++){
            // size is not fixed using ArrayList

            //EQUIP CURSOR by drawing around the current toold

            if(gp.player1.inventory.get(i) == gp.player1.currentWeapon ||
                    gp.player1.inventory.get(i) == gp.player1.currentShield){
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX2, slotY2, gp.tileSize, gp.tileSize, 10, 10);
            }

            //Scan one by one and draw the gotten image of the object picked up
            g2.drawImage(gp.player1.inventory.get(i).image,slotX2,slotY2,null);

            slotX2 += slotSize;// We need to check our position in the next line

            if(i == 4 || i == 9 || i == 14){// WE HAVE 4X5 MATRIX START FROM 0
                // WE NEED TO CHECK INDICES
                slotX2 = slotXStart2;
                slotY2 += slotSize;
            }
        }
        //DRAW PLAYER's ITEMS for PLAYER 2
        for(int i=0; i<gp.player2.inventory.size(); i++){// size is not fixed using ArrayList
            //Scan one by one
            //draw the gotten image of the object picked up
            //EQUIP ITEM FOR PLAYER 2
            if(gp.player2.inventory.get(i) == gp.player2.currentWeapon ||
                    gp.player2.inventory.get(i) == gp.player2.currentShield){
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX, slotY, slotSize, slotSize, 10, 10);
            }

            g2.drawImage(gp.player2.inventory.get(i).image,slotX,slotY,null);

            slotX += slotSize;// We need to check our position in the next line
            if(i == 4 || i == 9 || i == 14){// WE HAVE 4X5 MATRIX START FROM 0
                // WE NEED TO CHECK INDICES
                slotX = slotXStart;
                slotY += slotSize;
            }
        }

        //Remember to use SLOT SIZE HERE INSTEAD OF GAME PANEL TILE SIZE
        //Cursor for PLAYER 1
        int cursorX2 = slotXStart2 +(slotSize * slotCol2);
        int cursorY2 = slotYStart2 + (slotSize * slotRow2);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        //Draw Cursor for each inventory
        g2.setColor(Color.white);// color white
        g2.setStroke(new BasicStroke(3));// METHOD For thickness
        g2.drawRoundRect(cursorX2, cursorY2,cursorWidth,cursorHeight,10,10);//draw now

        //DESCRIPTION FRAME FOR TOOLS This block of code is optional
        int dFrameX = frameX2;//EASY CONCEPT AS ANY OTHER WINDOW DRAWN BEFORE
        int dFrameY = frameY2 + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize*2;
        //Draw DESCRIPTION TEXT HERE
        int textX = dFrameX +20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));//Choosing the font of the TEXT

        //GET THE OBJECT FROM THIS INDEX SLOT LATER
        int itemIndex = getItemIndexFromPlayer1();

        if(itemIndex < gp.player1.inventory.size()){//If there is an Item
            //DISPLAY THE DESCRIPTION WINDOW
            drawSubWindow(dFrameX, dFrameY, dFrameWidth,dFrameHeight);
            for(String line : gp.player1.inventory.get(itemIndex).description.split("\n")){
                g2.drawString(line,textX,textY);
                textY +=32;//as new line for each entry of Writing the string
            }
        }

        //Cursor for PLAYER 2
        int cursorX = slotXStart +(slotSize * slotCol);
        int cursorY = slotYStart + (slotSize * slotRow);
        //Draw Cursor for each inventory
        g2.setColor(Color.white);// color white
        g2.setStroke(new BasicStroke(3));// METHOD For thickness
        g2.drawRoundRect(cursorX, cursorY,cursorWidth,cursorHeight,10,10);//draw now

        //DESCRIPTION FRAME FOR TOOLS This block of code is optional
        int dFrameXTwo = frameX;//EASY CONCEPT AS ANY OTHER WINDOW DRAWN BEFORE
        int dFrameYTwo = frameY + frameHeight;
        //Draw DESCRIPTION TEXT HERE
        int textX2 = dFrameXTwo +20;
        int textY2 = dFrameYTwo + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));//Choosing the font of the TEXT

        int itemIndex2 = getItemIndexFromPlayer2();//GET THE OBJECT FROM THIS INDEX SLOT LATER

        if(itemIndex2 < gp.player2.inventory.size()){//If there is an Item
            //DISPLAY THE DESCRIPTION WINDOW
            drawSubWindow(dFrameXTwo, dFrameYTwo, dFrameWidth,dFrameHeight);
            for(String line : gp.player2.inventory.get(itemIndex2).description.split("\n")){
                g2.drawString(line,textX2,textY2);
                textY2 +=32;//as new line for each entry of Writing the string
            }
        }
    }

    public void drawChestWindow(){
        System.out.println("Size of the chest is "+chest.inventory.size());
        int x = gp.tileSize *6;
        int y = gp.tileSize *3;
        int width = gp.tileSize * 6;
        int height = gp.tileSize * 5;

        drawSubWindow(x,y,width,height);
    }

    public void buyItemMarket(){ // We call this method by pressing ENTER
        int itemIndex;
        //get Market Index Slot
            itemIndex = gp.ui.getItemIndexFromMarket();
            if(itemIndex !=0){
                //select items from SuperObject
                SuperObject selectedMarketItem = market.marketItems.get(itemIndex);
                currentItem = selectedMarketItem;
                //Check If the Wallet of the Player is Greater than or not
                if(playerCollideWithMarket.walletA >= currentItem.worth) {
                    playerCollideWithMarket.inventory.add(currentItem);
                    //deduct from player the amount
                    playerCollideWithMarket.walletA -= currentItem.worth;
                    showMessage("A "+currentItem.name+" Purchased");
                }
                else showMessage("Not Enough Money");
            }

    }

    //GETTING INDEX FOR SLOT POSITION FOR PLAYERS ONE AND ANOTHER ONE FOR TWO (USED IN INVENTORY DRAWING)
    public int getItemIndexFromMarket(){
        //we Going  use our slot columns and rows to select the object we captured the image from
        int itemIndexMarket = marketSlotCol + (marketSlotRow * 5);//counting the element value in my matrix
        return itemIndexMarket;
    }
    public int getItemIndexFromPlayer1(){
        //we Going  use our slot columns and rows to select the object we captured the image from
        int itemIndex = slotCol2 + (slotRow2 * 5);//counting the element value in my matrix
        return itemIndex;
    }
    public int getItemIndexFromPlayer2(){
        //we Going  use our slot columns and rows to select the object we captured the image from
        int itemIndex2 = slotCol + (slotRow * 5);//counting the element value in my matrix
        return itemIndex2;
    }

    public void drawPlayerLife1(Graphics2D g2){
        //updating the player Life
      //  gp.player1.life1=5;
        int x = gp.tileSize*2;
        int y = 0;
        int i =0;

        //Draw Blank Heart for Player 1
        while(i< gp.player1.maxLife1/2){//if life was lower than that number draw blank
            //first draw the blank , each 2 means one heart
            g2.drawImage(heart_blank,x,y,null);
            i++;// increase the heart by i
            x += gp.tileSize;
        }
        //Reset
         x = gp.tileSize*2;
         y = 0;
         i =0;
         //Draw Current Heart Life
        while(i < gp.player1.life1){//As if i am filling my player's heart
            g2.drawImage(heart_half, x,y,null);
            i++;
            if(i< gp.player1.life1){ // color full heart if true
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x +=gp.tileSize;

        }

    }
    public void drawPlayerLife2(Graphics2D g2){
        //updating the player Life2
        int x = gp.tileSize*8;
        int y = 0;
        int i =0;

        //Draw Blank Heart for Player 2
        while(i< gp.player2.maxLife1/2){
            //first draw the blank , each 2 means one heart
            g2.drawImage(heart_blank,x,y,null);
            i++;// increase the heart by i
            x += gp.tileSize;
        }
        //Reset
        x = gp.tileSize*8;
        y = 0;
        i =0;
        //Draw Current Heart Life
        while(i < gp.player2.life1){
            g2.drawImage(heart_half, x,y,null);
            i++;
            if(i< gp.player2.life1){ // color full heart if true
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x +=gp.tileSize;

        }

    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0, 0, 0,210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35 ,35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10,25,25);
    }
    public void collectTreasures(){
        if(gp.player1.collisionWithChest)
        {
            playerCollideWithChest = gp.player1;
        }else
            playerCollideWithChest = gp.player2;
        playerCollideWithChest.inventory.addAll(chest.inventory);

        collectedT = true;

    }
    public void showMessage(String text){
        message = text;// copy that text to message
        messageOn = true; // FLAG ON
    }

    //Method To Align the position after the text
    public int getXForAlignToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();//Watched video about aligning
        int x =tailX - length;
        return x;
    }
}
