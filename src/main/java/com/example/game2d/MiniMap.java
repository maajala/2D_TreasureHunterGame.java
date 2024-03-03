package com.example.game2d;

import entity.Player;

import java.awt.Color;
import java.awt.Graphics2D;

    public class MiniMap {
        public GamePanel gp; // Reference to the main game panel
        public Player player;
        public MiniMap(GamePanel gp) {
            this.gp = gp;
        }

        public void draw(Graphics2D g2) {
            // Draw the minimap
            int miniMapSize = 100; // Adjust the size as needed
            int miniMapX = 10; // Adjust the X position
            int miniMapY = 10; // Adjust the Y position

            g2.setColor(Color.BLACK);
            g2.fillRect(miniMapX, miniMapY, miniMapSize, miniMapSize);

            // Draw the player's position on the minimap
            int miniMapPlayerX = miniMapX + (player.worldX/ gp.WorldWidth * miniMapSize);
            int miniMapPlayerY = miniMapY + (player.worldX / gp.WorldHeight * miniMapSize);

            g2.setColor(Color.RED);
            g2.fillRect(miniMapPlayerX, miniMapPlayerY, 5, 5); // Adjust the player marker size
        }
    }

