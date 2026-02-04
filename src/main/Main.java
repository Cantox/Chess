package main;

import java.awt.Dimension;
import javax.swing.JFrame;
import libs.*;
import pieces.*;

/**
 * Icons by: https://www.flaticon.com/authors/good-ware
 * 
 * @author Cantoni Alessandro
 */
public class Main {
      public static void main(String[] args) {
              JFrame frame = new JFrame("Chess");
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
              BoardDrawer drawer = new BoardDrawer();
              drawer.setPreferredSize(new Dimension(Settings.COLS * Settings.TILE_SIZE, Settings.ROWS * Settings.TILE_SIZE));
              
              frame.add(drawer);
              frame.pack();
              frame.setResizable(false);
              
              drawer.reDraw(new Piece[0], new Position[0]);
              
              frame.setVisible(true);
      }
}
