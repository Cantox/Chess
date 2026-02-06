package pieces;

import java.io.IOException;
import javax.imageio.ImageIO;

import libs.*;
import main.Board;

public final class Knight extends Piece {
      public Knight(int color, Position startingPos) {
            super(color, startingPos);
            
            this.loadSprite(super.isWhite());
      }
      
      @Override
      public void loadSprite(boolean isWhite) {
            if(isWhite){
                  try { super.scaleAndSetSprite(ImageIO.read( getClass().getResource(Settings.DEFAULT_WHITE_IMG_PATH + "/knight.png") ) ); }
                  catch(IOException e) {System.out.println("Error loading sprite.\n" + e);}
            } else {
                  try { super.scaleAndSetSprite( ImageIO.read( getClass().getResource(Settings.DEFAULT_BLACK_IMG_PATH + "/knight.png") ) ); }
                  catch(IOException e) {System.out.println("Error loading sprite.\n" + e);}
            }
      }
      
      @Override
      public void calcLegalMoves(Board board) {
            if(board==null)
                  throw new NullPointerException("Board is null");
            
            legalMoves.clear();
            
            // Two tile offset
            for(int twoDiff=-2; twoDiff<=2; twoDiff+=4)
                  for(int oneDiff=-1; oneDiff<=1; oneDiff+=2){
                        Position checkedTile = new Position(getPos().row() + twoDiff, getPos().col() + oneDiff);
                        if(canGoThere(checkedTile, board))
                              legalMoves.add(checkedTile);
                        
                        checkedTile = new Position(getPos().row() + oneDiff, getPos().col() + twoDiff);
                        if(canGoThere(checkedTile, board))
                              legalMoves.add(checkedTile);
                  }
      }
      
      private boolean canGoThere(Position checkedTile, Board board){
            if(!board.isValidTile(checkedTile))
                  return false;
            
            return board.getPiece(checkedTile)==null || board.getPiece(checkedTile).isWhite()==!this.isWhite();
      }
}
