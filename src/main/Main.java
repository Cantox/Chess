package main;

import java.awt.Dimension;
import javax.swing.JFrame;

import libs.*;
import pieces.*;
import board.*;


/**
 * Icons by: https://www.flaticon.com/authors/good-ware
 * 
 * @author Cantoni Alessandro
 */
public class Main {
      public static void main(String[] args) {
              JFrame frame = new JFrame("Chess game");
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
              Board board = new Board(false);
              board.setPreferredSize(new Dimension(Settings.ROWS * Settings.TILE_SIZE, Settings.COLS * Settings.TILE_SIZE));
              
              frame.add(board);
              frame.pack();
              frame.setResizable(false);
              frame.setVisible(true);
      }
}
