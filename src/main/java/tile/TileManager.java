//package tile;
//
//import com.example.game2d.GamePanel;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.Objects;
//
////TO NAVIGATE BETWEEN WORLD AND SMALL
////CHANGE mapTileNum[][] rows and cols
//// choose draw1
////ALSO IN PLAYER CHANGE THE POSITIONING
//
//public class TileManager {
//
//    GamePanel gp ;
//    public Tile[] tile;
//
//    public int mapTileNum[][];// 2D array to catch the number of background tiles
//
//
//    // constructor to use this class later
//    public TileManager(GamePanel gp){
//
//        this.gp = gp;
//
//        tile = new Tile[10];// 10 kinds of tiles for now
//
//        //mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];//to store the map01 numbers in this 2D array
//        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
//        getTileImage();
//        loadMap("/maps/map01.txt");
//    }
//
//    // create a method to get Image inside the TileManager
//    //similar to what we did in Player
//    public void getTileImage(){
//        try{ // should create objects of buffered images for each tile from the Tile[] tile created in this class
//            //GRASS
//            tile[0] = new Tile();
//            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));
//
//            //WALL
//            tile[1] = new Tile();
//            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
//            tile[1].collision = true;
//
//            //Blue
//            tile[2] = new Tile();
//            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/blue.png")));
//            tile[2].collision = true;
//
//            //EARTH
//            tile[3] = new Tile();
//            tile[3].image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png")));
//
//            //TREE
//            tile[4] = new Tile();
//            tile[4].image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));
//            tile[4].collision = true;
//
//            //SAND
//            tile[5] = new Tile();
//            tile[5].image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png")));
//
//            //CASTLE BLOCK
//            tile[6] = new Tile();
//            tile[6].image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/black.png")));
//            //tile[6].collision = true;
//
//            //STONE
//            tile[7] = new Tile();
//            tile[7].image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/green.png")));
//            //tile[7].collision = true;
//
//            //TETRIS
//            tile[8] = new Tile();
//            tile[8].image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/houses/tetris.png")));
//            //tile[8].collision = true;
//            //
//            // 9 Red 8 yellow 7 Green 6 Black 5 Orange 2 Blue
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//    // method to store numbers of map file into 2D array mapTileNum
//
//    public void loadMap(String filePath){
//        try{
//            // I will use a known format to read the numbers in a text file
//            InputStream is = getClass().getResourceAsStream(filePath);
//            assert is != null; // checker for null for the stream input
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//            int col =0;
//            int row =0;
//
//            // loop for the numbers inside the text file to catch
//            while(col < gp.maxWorldCol && row < gp.maxWorldRow){//I changed here after the sample of map01 the
//                // maxScreenCol & row to world ones
//                String line = br.readLine();// read each line
//
//                    //another loop for each element in each row
//                while(col < gp.maxWorldCol){// maximum of each line
//                    String[] numbers = line.split(" ");// array to hold numbers as string
//
//                    int num = Integer.parseInt(numbers[col]);// elements being cast to integers
//                    // now we are ready to use our 2D array of integers "mapTileNum" to store integer
//                    mapTileNum [col][row] = num;
//                    col++;
//                }// exits when max reached
//                if(col == gp.maxWorldCol){ // checker to jump to next row
//                    // I changed here after the sample of map01 the maxScreenCol to world ones
//                    col = 0;//resets for next row
//                    row++;
//                }
//            }
//            br.close();// good practice to close after usage
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//   //  draw method , we don't need to update for fixed objects
//    public void draw1(Graphics2D g2){// some modification to make camera movements
//
//        int worldCol =0;//the naming here to make it easy for the
//        int worldRow =0;// programmer to read the code
//        //int x=0; modified to get movable camera
//        //int y=0;
//
//        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
//            // extract tile number from the map text file we extracted the numbers from
//            int tileNum = mapTileNum [worldCol][worldRow];// this should be our index in the tile
//
//            int worldX = worldCol * gp.tileSize;// to get the x position of each case in the loop
//            int worldY = worldRow * gp.tileSize;// to get the y position of each case
//            int screenX= worldX - gp.player1.worldX + gp.player1.screenX;// if worldX(0),i.e; -500+screenX in player(center)
//            int screenY= worldY - gp.player1.worldY  + gp.player1.screenY;// same for y see the screenshots for more understanding
//
//            if(worldX + gp.tileSize > gp.player1.worldX - gp.player1.screenX &&
//               worldX - gp.tileSize < gp.player1.worldX + gp.player1.screenX &&
//               worldY + gp.tileSize > gp.player1.worldY - gp.player1.screenY &&
//               worldY - gp.tileSize < gp.player1.worldY + gp.player1.screenY )
//            {   // if statement to ensure efficiency of the code// we only size the range to draw inside
//                g2.drawImage(tile[tileNum].image, screenX , screenY , gp.tileSize, gp.tileSize, null);
//            }
//
//
//            worldCol++; // going to next column
//           // x += gp.tileSize;// jumping to next block to draw
//
//            if( worldCol == gp.maxWorldCol){//check if reached max
//                worldCol=0; // reset and draw on next row
//             //   x=0; // reset x to begin from the zero of next y
//                worldRow++; // next row
//               // y += gp.tileSize; // jump by block downward 48
//            }//this modification has a trick: 1st: we need to know the tile image//2nd)x and y(where should we draw)
//        }
//
//    }
//    // Non MoveAble mapDrawer Method
//    public void draw(Graphics2D g2){
//
//        int col =0;
//        int row=0;
//        int x=0; //modified to get movable camera
//        int y=0;
//
//        while(col< gp.maxScreenCol&& row< gp.maxScreenRow){
//
//            int tileNum = mapTileNum [col][row];// here drawing once
//            g2.drawImage(tile[tileNum].image,x,y,gp.tileSize, gp.tileSize, null); // draw each block alone
//            // After each draw increment the column for each row
//            col++;
//            x += gp.tileSize;// HERE WE GO TO NEXT BLOCK BY JUMPING 48 PIXELS
//            if(col == gp.maxScreenCol){// checker for last COLUMN IF REACHED
//                col =0;//RESET TO 1ST COLUMN
//                x=0;
//                row++; // GO TO NEXT LINE OR ROW TO DRAW
//                y += gp.tileSize; // FOR EACH ROW JUMP Y SHOULD BE INCREMENTED TO DRAW ON Y AXIS DOWNWARD BY 48 PIXEL
//            }
//        }
//    }
//}
package tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import com.example.game2d.GamePanel;
import com.example.game2d.UtilityTool;

import javax.imageio.ImageIO;


public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int [gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();

    }


//    public void getTileImage(){
//        try{ // should create objects of buffered images for each tile from the Tile[] tile created in this class
//            //GRASS
//            tile[0] = new Tile();
//            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));
//
//
//            BufferedImage scaledImage = new BufferedImage(gp.tileSize, gp.tileSize, tile[0].image.getType());
//            Graphics2D g2 = scaledImage.createGraphics();//used to draw into this bufferedImage
//            g2.drawImage(tile[0].image,0,0,gp.tileSize,gp.tileSize,null);
//            tile[0].image = scaledImage;
//
//            //WALL
//            tile[1] = new Tile();
//            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
//            tile[1].collision = true;
//
//            //Blue
//            tile[2] = new Tile();
//            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/blue.png")));
//            tile[2].collision = true;
//
//            //EARTH
//            tile[3] = new Tile();
//            tile[3].image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png")));
//
//            //TREE
//            tile[4] = new Tile();
//            tile[4].image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));
//            tile[4].collision = true;
//
//            //SAND
//            tile[5] = new Tile();
//            tile[5].image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png")));
//
//            //CASTLE BLOCK
//            tile[6] = new Tile();
//            tile[6].image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/black.png")));
//            //tile[6].collision = true;
//
//            //STONE
//            tile[7] = new Tile();
//            tile[7].image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/green.png")));
//            //tile[7].collision = true;
//
//            //TETRIS
//            tile[8] = new Tile();
//            tile[8].image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tetris.png")));
//            //tile[8].collision = true;
//            //
//            // 9 Red 8 yellow 7 Green 6 Black 5 Orange 2 Blue
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//    }
    //using the setup method its easy now for us to draw tiles
    public void getTileImage(){
       // should create objects of buffered images for each tile from the Tile[] tile created in this class

            setup(0,"grass",false);
            setup(1,"wall",true);
            setup(2,"blue",true);
            setup(3,"earth",false);
            setup(4,"tree",true);
            setup(5,"sand",false);
            setup(6,"black",false);
            setup(7,"green",false);
            setup(8,"tetris",true);

    }

    //setup here creates tiles of passes index and image
    public void setup(int index , String imageName, boolean collision){
        // THIS METHOD'S PURPOSE WAS TO MAKE TILES FITS EXACTLY THE SIZE OF TILES IN MY SCREEN
        UtilityTool uTool = new UtilityTool();
        try{
            tile[index] = new Tile();
            tile[index].image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/"+ imageName +".png")));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap ()  {

        try {
            InputStream is = getClass().getResourceAsStream("/maps/map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;

            while (col <gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();

                while (col<gp.maxScreenCol){
                    String numbers[] =line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;

                }
                if(col == gp.maxScreenCol){
                    col =0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {


        }

    }
    public void draw (Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow)  {

            int tileNum = mapTileNum[col][row];

           // g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            g2.drawImage(tile[tileNum].image, x, y, null);
            col ++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol)  {
                col = 0;
                x =0;
                row ++;
                y+= gp.tileSize;
            }
        }

    }

}

