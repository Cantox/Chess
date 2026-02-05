package main;

import java.awt.Dimension;
import javax.swing.JFrame;

import libs.*;

/**
 * Icons by: https://www.flaticon.com/authors/good-ware
 * 
 * @author Cantoni Alessandro
 */
public class Main {
      
      public static Board board = new Board(false);
      private static Position selectedPos = null;

      public static Position getSelectedPos() {
            return selectedPos;
      }
      public static void setSelectedPos(Position selectedPos) {
            Main.selectedPos = selectedPos;
      }
      
      public static BoardDrawer drawer = new BoardDrawer();
      
      public static int currentPlayer;
      
      public static void main(String[] args) {
            Settings.loadDotSprite();
            setSelectedPos(null);
            
              JFrame frame = new JFrame("Chess");
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
              drawer.setPreferredSize(new Dimension(Settings.COLS * Settings.TILE_SIZE, Settings.ROWS * Settings.TILE_SIZE));
              
              frame.add(drawer);
              frame.pack();
              frame.setResizable(false);
              
              gameSetup();
              drawer.redraw(board.getPiecesArray(), null, null);
              
              frame.setVisible(true);
      }
      
      private static void gameSetup(){
            // Simulates black finishing his turn
            currentPlayer = Settings.BLACK;
            board.recalculateLegalMoves(currentPlayer);
            currentPlayer = Settings.WHITE;
      }
      
      public static void handleClick(int mouseX, int mouseY){
            Position clickedTile = new Position(mouseY / Settings.TILE_SIZE, mouseX / Settings.TILE_SIZE);
            
            if(!board.isValidTile(clickedTile))
                  return;
            
            boolean isWhiteTurn = currentPlayer == Settings.WHITE;
            if(selectedPos==null){ // Select piece
                  if(board.board[clickedTile.row()][clickedTile.col()]!=null && board.board[clickedTile.row()][clickedTile.col()].isWhite()==isWhiteTurn){
                        selectedPos = clickedTile;
                        drawer.redraw(board.getPiecesArray(), selectedPos, board.getPiece(selectedPos).getLegalMovesArray());
                  }
            } else { // Move piece
                  boolean pieceMoved = board.move(selectedPos, clickedTile);
                  selectedPos = null;
                  drawer.redraw(board.getPiecesArray(), null, null);
                  
                  // Switch player and recalculate moves
                  if(pieceMoved) {
                        board.recalculateLegalMoves(currentPlayer);
                        if(currentPlayer==Settings.WHITE) currentPlayer=Settings.BLACK;
                        else currentPlayer=Settings.WHITE;
                  }
            }
      }
}
