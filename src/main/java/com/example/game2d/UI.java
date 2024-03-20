package com.example.game2d;

import entity.Entity;
import object.Heart;
import object.SuperObject;
import object.treasures.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

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
    // TIME Taken
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");//display format to 2 decimal places
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
            x=gp.screenWidth/2 - textLength/2; //alligned to center
            y = gp.screenHeight/2 + (gp.tileSize*2);
            // now draw the string
            g2.drawString(text,x,y);


            //Stop The game
            gp.gameThread = null;

        }else {
            // method to display text on screen for update
            g2.setFont(arial_40);
            g2.setColor(Color.white);// color of Font
            //For Player 1 KEY and DICE
            g2.drawImage(keyImage,gp.tileSize/2+10,gp.tileSize/2,gp.tileSize,gp.tileSize,null);//coordinates of the key on screen
            g2.drawString("x "+gp.player1.keyCountA, 100, 80);
            g2.drawString("#DICE: "+gp.player1.steps/15,100,gp.tileSize*2-5);

            //For Player 2
            g2.drawImage(keyImage,gp.tileSize*12-10,gp.tileSize/2,gp.tileSize,gp.tileSize,null);//coordinates of the key on screen
            g2.drawString(gp.player2.keyCountB+" x", gp.tileSize*11, 80);
            g2.drawString("#DICE:"+gp.player2.steps/15,gp.tileSize*9,gp.tileSize*2-5);

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
                g2.drawString(message,gp.tileSize/2+10,gp.tileSize*6);
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

//        if(gameFinished == true){
//            // method to display text on screen for update
//            g2.setFont(arial_40);
//            g2.setColor(Color.white);// color of Font
//            // need to find my String length to display correctly
//            String text;
//            int textLength;
//            int x;
//            int y;
//            String name = "";
//            if(gp.player1.winnerPlayer)
//                name = "Player1";
//            else
//                if(gp.player2.winnerPlayer)
//                    name = "Player2";
//
//            text = name+" Found the Treasure!";  // this down here returns the length of text length
//            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
//            x=gp.screenWidth/2 - textLength/2; //alligned to center
//            y = gp.screenHeight/2 - (gp.tileSize*3);
//            // now draw the string
//            g2.drawString(text,x,y);
//
//            // FONT COLOR
//            g2.setFont(arial_60B);
//            g2.setColor(Color.YELLOW);// color of Font
//            text = "Congratulations to "+name+"!";  // this down here returns the length of text length
//            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
//            x=gp.screenWidth/2 - textLength/2; //alligned to center
//            y = gp.screenHeight/2 + (gp.tileSize*2);
//            // now draw the string
//            g2.drawString(text,x,y);
//
//
//            //Stop The game
//            gp.gameThread = null;
//
//        }else {
//            // method to display text on screen for update
//            g2.setFont(arial_40);
//            g2.setColor(Color.white);// color of Font
//            //For Player 1 KEY and DICE
//            g2.drawImage(keyImage,gp.tileSize/2+10,gp.tileSize/2,gp.tileSize,gp.tileSize,null);//coordinates of the key on screen
//            g2.drawString("x "+gp.player1.keyCountA, 100, 80);
//            g2.drawString("#DICE: "+gp.player1.steps/15,100,gp.tileSize*2-5);
//
//            //For Player 2
//            g2.drawImage(keyImage,gp.tileSize*12-10,gp.tileSize/2,gp.tileSize,gp.tileSize,null);//coordinates of the key on screen
//            g2.drawString(gp.player2.keyCountB+" x", gp.tileSize*11, 80);
//            g2.drawString("#DICE:"+gp.player2.steps/15,gp.tileSize*9,gp.tileSize*2-5);
//
//            //TIME TAKEN
//            playTime += (double)1/60;// we divide by our FPS which is 60
//            g2.drawString("Time: "+dFormat.format(playTime), gp.tileSize*5,gp.tileSize);
//
//            // Player1 Life
//            drawPlayerLife1(g2);
//            //Player2 Life
//            drawPlayerLife2(g2);
//            //Message For Heads Up
//            if(messageOn == true){
//                // here we just changed our created font size to 30
//                g2.setFont(g2.getFont().deriveFont(30F));
//                g2.drawString(message,gp.tileSize/2+10,gp.tileSize*6);
//                // I want the msg to disappear after a definite time
//                messageCounter++;
//                if(messageCounter > 110){//120 FPS then 2 seconds since its 60 FPS
//                    messageCounter=0;
//                    messageOn =false;
//                }
//            }
//
//        }

    }
    public void drawPauseScreen(){
        String text = "Paused";

        int x=gp.tileSize*5 ; //alligned to center

        int y = gp.tileSize*7;

        g2.drawString(text, x, y);
    }
    public void drawPlayerLife1(Graphics2D g2){
        //updating the player Life
      //  gp.player1.life1=5;
        int x = gp.tileSize*2;
        int y = 0;
        int i =0;

        //Draw Blank Heart for Player 1
        while(i< gp.player1.maxLife1/2){
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
        while(i < gp.player1.life1){
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
        while(i< gp.player2.maxLife2/2){
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
        while(i < gp.player2.life2){
            g2.drawImage(heart_half, x,y,null);
            i++;
            if(i< gp.player2.life2){ // color full heart if true
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x +=gp.tileSize;

        }

    }
//    public void drawInventory(){
//        //FRAME
//        int frameX = gp.tileSize *9;
//        int frameY = gp.tileSize;
//        int frameWidth = gp.tileSize * 6;
//        int frameHeight = gp.tileSize * 5;
//        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
//    }

//    public void drawSubWindow(int x, int y, int width, int height){};
    public void showMessage(String text){
        message = text;// copy that text to message
        messageOn = true; // FLAG ON
    }
}
