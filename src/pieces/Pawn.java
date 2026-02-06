package pieces;

import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import libs.*;
import main.Board;

public final class Pawn extends Piece {
      public Pawn(int color, Position startingPos) {
            super(color, startingPos);
            
            this.loadSprite(super.isWhite());
      }
      
      @Override
      public void loadSprite(boolean isWhite) {
            if(isWhite){
                  try { super.scaleAndSetSprite(ImageIO.read( getClass().getResource(Settings.DEFAULT_WHITE_IMG_PATH + "/pawn.png") ) ); }
                  catch(IOException e) {System.out.println("Error loading sprite.\n" + e);}
            } else {
                  try { super.scaleAndSetSprite( ImageIO.read( getClass().getResource(Settings.DEFAULT_BLACK_IMG_PATH + "/pawn.png") ) ); }
                  catch(IOException e) {System.out.println("Error loading sprite.\n" + e);}
            }
      }
      
      @Override
      public void calcLegalMoves(Board board) {
            if(board==null)
                  throw new NullPointerException("Board is null");
            
            legalMoves.clear();
            
            int dir;
            if(isWhite()) dir=-1;
            else dir=1;
            
            //  Check tile in front
            Position tileChecked = new Position(getPos().row() + dir, getPos().col());
            if(!board.isValidTile(tileChecked)) {}
            
            else if(board.getPiece(tileChecked)==null){
                  legalMoves.add(tileChecked);
                  tryDoubleStep(board, legalMoves, dir);
            }
            
            // Check for captures
            for(int i=-1; i<=1; i+=2){
                  tileChecked = new Position(getPos().row() + dir, getPos().col()+i);
                  if(!board.isValidTile(tileChecked)) {}
                  else if(board.getPiece(tileChecked)==null) {}
                  
                  else if(board.getPiece(tileChecked).isWhite()==!this.isWhite())
                        legalMoves.add(tileChecked);
            }
      }
      
      private void tryDoubleStep(Board board, ArrayList<Position> moves, int dir){
            if(getMovesDone()!=0)
                  return;
            
            Position  tileChecked = new Position(getPos().row() + (2*dir), getPos().col());
            if(!board.isValidTile(tileChecked))
                  return;
            
            if(board.getPiece(tileChecked)==null)
                  moves.add(tileChecked);
      }
}