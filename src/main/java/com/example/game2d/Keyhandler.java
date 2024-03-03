package com.example.game2d;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// class created to add movement for the character
public class Keyhandler implements KeyListener {

    public boolean upPressed, downPressed, rightPressed, leftPressed;

    @Override // not going to be used
    public void keyTyped(KeyEvent e) {
    }
    // builtin method in JavaFx that I manipulate to control the flow of movement for the player
    @Override
    public void keyPressed(KeyEvent e) {
        //getKeyCode() Returns the integer keyCode associated with the key we pressed
        int code = e.getKeyCode();

        // thus we need to check the key we entered by the keyboard
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        // here when we release the button we should return false
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }

}
