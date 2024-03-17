package com.example.game2d;

import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        // to close the window after usage
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setResizable(false);
        window.setTitle("Salesmen Go");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        // window would be centered in the middle
        window.setLocationRelativeTo(null);
        // make window visibly possible
        window.setVisible(true);

        // WE CALL THIS AFTER CREATION OF setUpGame in GamePanel
        gamePanel.setUpGame();

        gamePanel.startGameThread1();
    }
}

  /*  public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        // to close the window after usage
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Salesmen Go");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        // window would be centered in the middle
        window.setLocationRelativeTo(null);
        // make window visibly possible
        window.setVisible(true);

        // WE CALL THIS AFTER CREATION OF setUpGame in GamePanel
          gamePanel.setUpGame();

        gamePanel.startGameThread1();
    }*/

  /* public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        // to close the window after usage
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Salesmen Go");

        // Create a main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create two GamePanels
        GamePanel gamePanel1 = new GamePanel();
        GamePanel gamePanel2 = new GamePanel();

        // Add the first GamePanel to the left
        gamePanel1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.BLACK));
        mainPanel.add(gamePanel1, BorderLayout.WEST);

        // Add the second GamePanel to the right
        mainPanel.add(gamePanel2, BorderLayout.EAST);

        window.add(mainPanel);

        window.pack();
        // window would be centered in the middle
        window.setLocationRelativeTo(null);
        // make window visibly possible
        window.setVisible(true);

        // WE CALL THIS AFTER CREATION OF setUpGame in GamePanel
        gamePanel1.setUpGame();
        gamePanel2.setUpGame();

        gamePanel1.startGameThread1();
        gamePanel2.startGameThread2();
    }*/

