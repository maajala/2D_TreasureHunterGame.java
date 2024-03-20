//package com.example.game2d;
//
//
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//// class created to add movement for the character
//public class Keyhandler implements KeyListener {
//
//    public boolean upPressed, downPressed, rightPressed, leftPressed;
//
//    public boolean wPress ,sPress,aPress,dPress;
//
//    public boolean rollDiceRequested = false;
//    @Override // not going to be used
//    public void keyTyped(KeyEvent e) {
//    }
//    // builtin method in JavaFx that I manipulate to control the flow of movement for the player
//    @Override
//    public void keyPressed(KeyEvent e) {
//        //getKeyCode() Returns the integer keyCode associated with the key we pressed
//        int code = e.getKeyCode();
//
//        // thus we need to check the key we entered by the keyboard
//        if(code == KeyEvent.VK_W){
//            upPressed = true;
//        }
//        if(code == KeyEvent.VK_S){
//            downPressed = true;
//        }
//        if(code == KeyEvent.VK_A){
//            leftPressed = true;
//        }
//        if(code == KeyEvent.VK_D){
//            rightPressed = true;
//        }
//        if(code == KeyEvent.VK_SPACE){
//            rollDiceRequested = true;
//        }
//
//
//        // player2
//        // System.out.println("Key Pressed: " + e.getKeyCode());
//
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        int code = e.getKeyCode();
//        // here when we release the button we should return false
//        if(code == KeyEvent.VK_W){
//            upPressed = false;
//        }
//        if(code == KeyEvent.VK_S){
//            downPressed = false;
//        }
//        if(code == KeyEvent.VK_A){
//            leftPressed = false;
//        }
//        if(code == KeyEvent.VK_D){
//            rightPressed = false;
//        }
//        if(code == KeyEvent.VK_SPACE){
//            rollDiceRequested = false;
//
//          //  System.out.println("Key released: " + e.getKeyCode());}
//
//    }
//
//}
//}
package com.example.game2d;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// This class handles keyboard events for player movement
public class Keyhandler implements KeyListener {
    GamePanel gp;
    // Flags for Player 1
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    //public boolean canMoveUp = true, canMoveDown = true, canMoveLeft = true, canMoveRight = true;

    public boolean diceRollPressed = false;
    public boolean enterPressed = false;
    public boolean playPressed= false;
    public boolean upFirstPressed = false; // Add these flags
    public boolean downFirstPressed = false; // Add these flags
    public boolean leftFirstPressed = false; // Add these flags
    public boolean rightFirstPressed = false; // Add these flags

    public Keyhandler(){};
    public Keyhandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // This method is part of the KeyListener interface but is not used in this class
        // It's called when a key is typed (pressed and released), but it's not implemented here
    }


    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Player 1 movement
        if (code == KeyEvent.VK_UP) {
//         if(!upPressed){// only trigger on first press
//             upFirstPressed = true;
//         }
         upPressed = true;
        }
        if (code == KeyEvent.VK_DOWN) {
//            if(!downPressed){// only trigger on first press
//                downFirstPressed = true;
//            }
            downPressed = true;
        }
        if (code == KeyEvent.VK_LEFT) {
//            if(!leftPressed){// only trigger on first press
//                leftFirstPressed = true;
//            }
            leftPressed = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
//            if(!rightPressed){// only trigger on first press
//                rightFirstPressed = true;
//            }
            rightPressed = true;
        }

        if(code == KeyEvent.VK_SPACE){
            diceRollPressed = true;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if(code == KeyEvent.VK_P){//if pressed P
            if(gp.gameState == gp.playState){// if game state was play
                gp.gameState = gp.pauseState;//pause the game
            }else if(gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        // Player 1 movement
        if (code == KeyEvent.VK_UP) {
            upPressed = false;
         //  upFirstPressed =false;//reset on release
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = false;
         //  downFirstPressed =false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
            //leftFirstPressed =false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
           // rightFirstPressed =false;
        }
        if(code == KeyEvent.VK_SPACE){
            diceRollPressed = false;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = false;
        }
        if(code == KeyEvent.VK_P){
            playPressed = false;
        }
    }
}
