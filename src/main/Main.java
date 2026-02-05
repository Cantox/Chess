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
      
      public static Board board = new Board(true);
      private static Position selectedPos = null;

      public static Position getSelectedPos() {
            return selectedPos;
      }
      public static void setSelectedPos(Position selectedPos) {
            Main.selectedPos = selectedPos;
      }
      
      public static BoardDrawer drawer = new BoardDrawer();
      
      public static void main(String[] args) {
            Settings.loadDotSprite();
            
              JFrame frame = new JFrame("Chess");
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
              drawer.setPreferredSize(new Dimension(Settings.COLS * Settings.TILE_SIZE, Settings.ROWS * Settings.TILE_SIZE));
              
              frame.add(drawer);
              frame.pack();
              frame.setResizable(false);
              
              drawer.redraw(board.getPiecesArray(), null, null);
              
              frame.setVisible(true);
      }
      
      public static void handleClick(int mouseX, int mouseY){
            Position clickedTile = new Position(mouseX / Settings.TILE_SIZE, mouseY / Settings.TILE_SIZE);
            
            if(board.isValidTile(clickedTile)){
                  if(selectedPos==null){
                        selectedPos = board.selectPiece(clickedTile);
                        if(selectedPos!=null) {}
                              drawer.redraw(board.getPiecesArray(), selectedPos, board.getPiece(selectedPos).getLegalMovesArray());
                  } else {
                        board.move(selectedPos, clickedTile);
                        selectedPos = null;
                        drawer.redraw(board.getPiecesArray() ,null, null);
                  }
            }
      }
}
