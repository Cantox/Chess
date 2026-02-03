package board;

import java.awt.Graphics;
import javax.swing.JPanel;

import pieces.*;
import libs.*;

public class Board extends JPanel {
      private Piece[][] board = new Piece[Settings.ROWS][Settings.COLS];
      
      public Board(boolean empty) {
            if(!empty){
                  board[0][0] = new BlackPawn(new Position(0,0));
            }
      }
      
      @Override
      protected void paintComponent(Graphics g){
            super.paintComponent(g);
            
            for(int r=0; r<Settings.ROWS; r++)
                  for(int c=0; c<Settings.COLS; c++){
                        if( (r+c)%2==0 )
                              g.setColor(Settings.LIGHT_TILE);
                        else
                              g.setColor(Settings.DARK_TILE);
                        
                        g.fillRect(c * Settings.TILE_SIZE, r * Settings.TILE_SIZE, Settings.TILE_SIZE, Settings.TILE_SIZE);
                  }
            
            drawPieces(g);
      }
      
      private void drawPieces(Graphics g){
            for(int r=0; r<Settings.ROWS; r++)
                  for(Piece piece : board[r])
                        if(piece != null){
                              Position p = piece.getPos();
                              int x = (p.col() * Settings.TILE_SIZE) + Settings.PADDING;
                              int y = (p.row() * Settings.TILE_SIZE) + Settings.PADDING;
                              
                              g.drawImage(
                                      piece.getSprite(), 
                                      x,
                                      y,
                                      Settings.ICON_SIZE, 
                                      Settings.ICON_SIZE, 
                                      null);
                        }
      }
      
}
