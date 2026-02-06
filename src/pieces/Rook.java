package pieces;

import java.io.IOException;
import javax.imageio.ImageIO;

import libs.*;
import main.Board;

public final class Rook extends Piece {
      
      public Rook(int color, Position startingPos) {
            super(color, startingPos);
            
            this.loadSprite(super.isWhite());
      }
      
      @Override
      public void loadSprite(boolean isWhite) {
            if(isWhite){
                  try { super.scaleAndSetSprite(ImageIO.read( getClass().getResource(Settings.DEFAULT_WHITE_IMG_PATH + "/rook.png") ) ); }
                  catch(IOException e) {System.out.println("Error loading sprite.\n" + e);}
            } else {
                  try { super.scaleAndSetSprite( ImageIO.read( getClass().getResource(Settings.DEFAULT_BLACK_IMG_PATH + "/rook.png") ) ); }
                  catch(IOException e) {System.out.println("Error loading sprite.\n" + e);}
            }
      }
      
      @Override
      public void calcLegalMoves(Board board) {
            if(board==null)
                  throw new NullPointerException("Board is null");
            
            Position[] directions = { new Position(0,1),
                                                  new Position(1,0),
                                                  new Position(0,-1),
                                                  new Position(-1,0)};
            legalMoves.clear();
            
            for(Position dir : directions){
                  int offset = 1;
                  Position checkedTile = new Position( getPos().row() + dir.row()*offset, getPos().col() + dir.col()*offset);
                  
                  while(board.isValidTile(checkedTile) && board.getPiece(checkedTile)==null){
                        legalMoves.add(checkedTile);
                        offset++;
                        checkedTile = new Position( getPos().row() + dir.row()*offset, getPos().col() + dir.col()*offset);
                  }
                  
                  if(board.isValidTile(checkedTile) && board.getPiece(checkedTile).isWhite()==!this.isWhite())
                        legalMoves.add(checkedTile);
            }
      }
}
