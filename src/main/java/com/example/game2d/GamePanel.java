package com.example.game2d;

import collision.CollisionChecker;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{
    // works as game screen ,
    //SCREEN SETTING
    final int originalTileSize =16; // 16 x 16
    final int scalar = 3;// SCALAR THAT CHANGE THE SIZE OF THE GAME PAGE LATER
    public final int tileSize = originalTileSize  * scalar; // 48 x 48
    public final int maxScreenCol = 10;//
    public final int maxScreenRow = 10;//
    public final int  screenWidth = tileSize * maxScreenCol;//768 pixels // 10> 480
    public final int screenHeight = tileSize * maxScreenRow;//576 pixels

    //WORLD SETTING
    public final int maxWorldCol = 50;//50
    public final int maxWorldRow = 50;//50
    public final int WorldWidth = tileSize * maxWorldCol;
    public final int WorldHeight= tileSize * maxWorldRow; // this is going to be our resize

    //  a constructor used for GamePanel when called
    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));// set dimensions for the game panel
        this.setBackground(Color.GRAY);// set the color of the background
        this.setDoubleBuffered(true);// for any buffer solve it outside offscreen for more improvement of the performance
        this.addKeyListener(keyH);// this line of code to recognise the controls of class KeyHandler
      //  this.addKeyListener(keyH2);
        this.setFocusable(true);//With this, GamePanel can be "focused" to receive key input.
    }

    //IT IS IMPORTANT TO CALL THIS GAME METHOD BEFORE GAME STARTS
    public void setUpGame(){
        assetSetter.setObject();
    }

    //FPS
    int FPS = 60;

    //SYSTEM
    Keyhandler keyH = new Keyhandler(); //FOR PLAYER 1 MOVEMENT
    //KeyHand2 keyH2 = new KeyHand2(); //FOR PLAYER 2 MOVEMENT
    Thread gameThread;// calling thread later will invoke the run method down
   public Player player1 = new Player(this,keyH);
  // public Player player2 = new Player(this,keyH2);
    public TileManager tileM = new TileManager(this); // this is related to this Game Panel

    public CollisionChecker collisionChecker = new CollisionChecker(this);// instantiate collision checker

    public AssetSetter assetSetter = new AssetSetter(this);
    public SuperObject obj[] = new SuperObject[20]; // we prepare an array of size 10 for objects // we will use it in another class
    // THAT MEANS WE CAN DISPLAY 10 OBJECTS AT THE SAME TIME



    public void startGameThread1() {
        gameThread = new Thread(this);// instantiating a thread
        gameThread.start();// start the thread
    }

   @Override
    public void run() {
        double drawInterval = 1000000000/FPS; // 0.0166 seconds

        double lastTime = System.nanoTime(); // last time before entering the loop
        double delta=0;
        double currentTime;

    while(gameThread != null)
        {

        currentTime = System.nanoTime();
        delta += (currentTime - lastTime)/drawInterval; // keep track of the difference then check
        //then update lastTime
        lastTime = currentTime;
        if(delta >= 1)
            {
            // updating the information
            update();

            // painting using repaint
            repaint();
            delta--;
            }
        }
    }

    // method to update screen information
    public void update(){
        // here we update pixels with 4
      player1.update(keyH);


    }

    // method to paint or draw the component
    public void paintComponent(Graphics g){ // I will transform this graphics to 2d ones to have more control
        super.paintComponent(g);
        // graphics 2d class extends graphics class to add more control over geometry,x,y components, color and text layout
        Graphics2D g2 = (Graphics2D)g;

        //Tile
        tileM.draw(g2);// we draw the background then player inorder to see the player

        //Object(here we need to know what kind of object we need to draw first)
        for(int i = 0; i < obj.length; i++){ //we scan each element in the object array one by one
            if(obj[i] != null){ // check for null objects to avoid null pointers
                obj[i].draw(g2, this);
            }

        }
        //Player
        player1.draw(g2);

        //to save some memory use dispose
        g2.dispose();
    }

}
