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
public class KeyHandler2 implements KeyListener {
    // Flags for Player 2
    public boolean wPressed, sPressed, aPressed, dPressed;

    public boolean diceRollPressed= false;

    @Override
    public void keyTyped(KeyEvent e) {
        // This method is part of the KeyListener interface but is not used in this class
        // It's called when a key is typed (pressed and released), but it's not implemented here
    }


    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Player 1 movement
        if (code == KeyEvent.VK_W) {
            wPressed = true;
            //canMoveUp = false;
        }
        if (code == KeyEvent.VK_S) {
            sPressed = true;
            // canMoveDown = false;
        }
        if (code == KeyEvent.VK_A) {
            aPressed = true;
            //  canMoveLeft = false;
        }
        if (code == KeyEvent.VK_D) {
            dPressed = true;
            //  canMoveRight = false;
        }
        if (code == KeyEvent.VK_SPACE){
            diceRollPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        // Player 1 movement
        if (code == KeyEvent.VK_W) {
            wPressed = false;
            // canMoveUp = true;
        }
        if (code == KeyEvent.VK_S) {
            sPressed = false;
            // canMoveDown = true;
        }
        if (code == KeyEvent.VK_A) {
            aPressed = false;
            //  canMoveLeft = true;
        }
        if (code == KeyEvent.VK_D) {
            dPressed = false;
            // canMoveRight = true;
        }
        if (code == KeyEvent.VK_SPACE){
            diceRollPressed = false;
        }

    }
}
