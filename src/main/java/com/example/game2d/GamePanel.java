package com.example.game2d;

import collision.CollisionChecker;
import entity.*;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{
    // works as game screen ,
    //SCREEN SETTING
    private final int Big_Num = 1000000000;//used to create a logical interval when drawing
    final int originalTileSize =32; // 16 x 16
    final int scalar = 2;// SCALAR THAT CHANGE THE SIZE OF THE GAME PAGE LATER
    public final int tileSize = originalTileSize  * scalar; // 48 x 48
    public final int maxScreenCol = 14;//
    public final int maxScreenRow = 14;//
    public final int  screenWidth = tileSize * maxScreenCol;//768 pixels // 10> 480
    public final int screenHeight = tileSize * maxScreenRow;//576 pixels

    //Game State
    public int gameState;//making  state for the game
    public final int playState = 1;
    public final int pauseState = 2;
    public final int characterState =3;
    public final int inventoryState = 4;
    public final int marketState = 5;
    public final int chestState = 6;

    //WORLD SETTING
    public final int maxWorldCol = 13;//50
    public String playerTurnName = "";

   // public final int maxWorldRow = 13;//50
   // public final int WorldWidth = tileSize * maxWorldCol;
    //public final int WorldHeight= tileSize * maxWorldRow; // this is going to be our resize

    //  a constructor used for GamePanel when called
    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));// set dimensions for the game panel
        this.setBackground(Color.BLACK);// set the color of the background
        this.setDoubleBuffered(true);// for any buffer solve it outside offscreen for more improvement of the performance
        this.addKeyListener(keyH);// this line of code to recognise the controls of class KeyHandler
        //this.addKeyListener(keyH2);
        this.setFocusable(true);//With this, GamePanel can be "focused" to receive key input.
    }
    //IT IS IMPORTANT TO CALL THIS GAME METHOD BEFORE GAME STARTS
    public void setUpGame() throws IOException {
        //assetSetter.setNPC();
        //assetSetter.setMonster();
       assetSetter.setObject();
       gameState = playState;// State calls to play

    }

    //FPS
    int FPS = 60;

    //SYSTEM
    Keyhandler keyH = new Keyhandler(this); //FOR PLAYER 1 MOVEMENT
    KeyHandler2 keyH2 = new KeyHandler2();
    //FOR PLAYER 2 MOVEMENT
    Thread gameThread;// calling thread later will invoke the run method down

    public TileManager tileM = new TileManager(this); // this is related to this Game Panel

    //INSTANTIATE COLLISION CHECKER FOR BOTH PLAYERS
    public CollisionChecker collisionChecker1 = new CollisionChecker(this);// instantiate collision checker
    public CollisionChecker collisionChecker2 = new CollisionChecker(this);// instantiate collision checker

    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    //DICE INSTANTIATION
    Dice dice = new Dice();
    public boolean playerTurn = true;// Add a variable to track the
    // current turn; true for player1's turn, false for player2's turn

    //Entity and Objects
    public PlayerTest player1 = new PlayerTest(this,keyH,collisionChecker1);
    public PlayerTest player2 = new PlayerTest(this,keyH,collisionChecker2);
    public SuperObject obj[] = new SuperObject[40]; // we prepare an array of size 10 for objects // we will use it in another class
    // THAT MEANS WE CAN DISPLAY 25 OBJECTS AT THE SAME TIME
    public Entity npc[] = new Entity[10];// create npc from entity
    public Entity monster[] = new Entity[20];//create monster from npc
    public void startGameThread1() {
        gameThread = new Thread(this);// instantiating a thread
        gameThread.start();// start the thread
    }

   @Override // Creating a Logical interval when drawing
    public void run() {//A method that is essential part of the Runnable interface
        double drawInterval = Big_Num/FPS; // 0.0166 seconds

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
    public void update() {
        if(gameState == playState){
            if (playerTurn) {
                playerTurnName = "Player 1";
                player1.update();//update player one actions
                if (player1.steps <= 0) { // used his steps
                    playerTurn = false; // Switch to Player 2's turn
                    player2.prepareTurn(); // Prepare Player 2 for his turn
                }
            } else {//if it was not playerTurn 2 which is true after 1st if
                playerTurnName = "Player 2";
                player2.update();
                if (player2.steps <= 0) {
                    playerTurn = true; // Switch back to Player 1's turn
                    player1.prepareTurn(); // Prepare Player 1 for their turn
                }
            }
        }
        // IF P PRESSED PAUSE
        else if(gameState == pauseState){
            ui.drawPauseScreen();
        }
        // IF C PRESSED
        else if(gameState == characterState){
            ui.drawCharacterScreen();
        }
        // IF I PRESSED
        else if(gameState == inventoryState){
            ui.drawInventory();
        }
        //IF M PRESSED
         if(gameState == marketState){
            ui.drawMarketScreen();
        }
        //IF O Pressed

    }

    // METHOD TO PAINT COMPONENT ON SCREEN
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // I will transform this graphics to 2d ones to have more control
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

        //Player1
        player1.draw(g2);
        //player 2
        player2.draw(g2);

        //draw grid
        drawGrid(g);

        //UI
        ui.draw(g2);

        //to save some memory use dispose
        g2.dispose();
    }
    private void drawGrid(Graphics g) {
        g.setColor(Color.black);
        // Draw the vertical lines
        for (int x = 0; x <= screenWidth; x += tileSize) {
            g.drawLine(x, 2, x, screenHeight);//takes 2 points co-ordinates

        }
        // Draw the horizontal lines
        for (int y = 0; y <= screenHeight; y += tileSize) {
            g.drawLine(0, y, screenWidth, y);
        }
    }
}
