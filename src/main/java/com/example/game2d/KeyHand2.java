package com.example.game2d;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHand2 implements KeyListener {

    public boolean upPress, downPress, rightPress, leftPress;


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //getKeyCode() Returns the integer keyCode associated with the key we pressed
        int code = e.getKeyCode();

        // thus we need to check the key we entered by the keyboard
        if(code == KeyEvent.VK_UP){
            upPress = true;
        }
        if(code == KeyEvent.VK_DOWN){
            downPress = true;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPress = true;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPress = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        // here when we release the button we should return false
        if(code == KeyEvent.VK_UP){
            upPress = false;
        }
        if(code == KeyEvent.VK_DOWN){
            downPress = false;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPress = false;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPress = false;
        }
    }
}
