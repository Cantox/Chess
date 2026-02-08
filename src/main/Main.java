package main;

import java.awt.Dimension;
import javax.swing.JFrame;

import libs.*;
import pieces.Piece;

/**
 * Icons by: https://www.flaticon.com/authors/good-ware
 * 
 * @author Cantoni Alessandro
 */
public class Main {
      
      public static Board board = new Board(false);
      private static Position selectedPos = null;
      
      public static BoardDrawer drawer = new BoardDrawer();
      
      public static int currentPlayer;
      
      public static void main(String[] args) {
            Settings.loadDotSprite();
            selectedPos = null;
            
              JFrame frame = new JFrame("Chess");
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
              drawer.setPreferredSize(new Dimension( 2*(Settings.COLS*Settings.TILE_SIZE) + Settings.TILE_SIZE, Settings.ROWS * Settings.TILE_SIZE));
              
              frame.add(drawer);
              frame.pack();
              frame.setLocationRelativeTo(null);
              frame.setResizable(false);
              
              gameSetup();
              drawer.redraw(board.getPiecesArray(), null, null, currentPlayer);
              
              frame.setVisible(true);
      }
      
      private static void gameSetup(){
            // Simulates black finishing his turn
            currentPlayer = Settings.BLACK;
            board.recalculateLegalMoves(currentPlayer, drawer);
            currentPlayer = Settings.WHITE;
      }
      
      public static void handleClick(int mouseX, int mouseY){
            Position clickedTile;
            if(currentPlayer==Settings.WHITE)
                  clickedTile = new Position(mouseY / Settings.TILE_SIZE, mouseX / Settings.TILE_SIZE);
            else
                  clickedTile = blackSelection(mouseX, mouseY);
            
            if(!board.isValidTile(clickedTile)) return;
            
            boolean isWhiteTurn = currentPlayer == Settings.WHITE;
            Position pieceToHighlight = null;
            
            if(selectedPos==null){ // Select piece
                  Piece selectedPiece = board.getPiece(clickedTile);
                  if(selectedPiece!=null && selectedPiece.isWhite()==isWhiteTurn){
                        selectedPos = clickedTile;
                        pieceToHighlight = selectedPos;
                        drawer.redraw(board.getPiecesArray(), pieceToHighlight, board.getPiece(selectedPos).getLegalMovesArray(), currentPlayer);
                  }
            } else { // Move piece
                  Piece selectedPiece = board.getPiece(selectedPos);
                  
                  if(selectedPiece.isLegalMove(clickedTile)){
                        boolean pieceMoved;
                        
                        if(selectedPiece.isCastlingMove(clickedTile))
                              pieceMoved = selectedPiece.castle(board,clickedTile);
                        else
                              pieceMoved = board.move(selectedPiece, clickedTile);
                        
                        pieceToHighlight = selectedPiece.getPos();
                        
                        // Switch player and recalculate moves
                        if(pieceMoved) {
                              board.recalculateLegalMoves(currentPlayer, drawer);
                              if(currentPlayer==Settings.WHITE) currentPlayer=Settings.BLACK;
                              else currentPlayer=Settings.WHITE;
                        }
                  }
                  
                  selectedPos = null;
                  drawer.redraw(board.getPiecesArray(), pieceToHighlight, null, currentPlayer);
            }
      }
      private static Position blackSelection(int mouseX, int mouseY){
            int blackBoardStart = (Settings.COLS+1) * Settings.TILE_SIZE;
            if(mouseX < blackBoardStart)
                  return new Position(-1,-1);
            
            int clickedRow = mouseY / Settings.TILE_SIZE;
            int clickedCol = (mouseX - blackBoardStart) / Settings.TILE_SIZE;
            return new Position( Settings.ROWS-1 - clickedRow, Settings.COLS-1 - clickedCol );
      }
}
