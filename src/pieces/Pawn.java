package pieces;

import libs.Position;
import libs.Settings;

public class Pawn extends Piece{
      private boolean isWhite;
      
      public Pawn(Position startingPos) {
            super(startingPos);
      }
      
      public boolean isWhite() {
            return isWhite;
      }
      public void setIsWhite(boolean isWhite) {
            this.isWhite = isWhite;
      }
      
      public Position[] getPossibleMoves() {
            if(isWhite)
                  return whiteMoves();
            else
                  return blackMoves();
      }
      private Position[] whiteMoves(){
            Position[] m;
            if(super.getPos() == super.getStartingPos())
                  m = new Position[] { new Position(super.getPos().row()-2, super.getPos().col()), new Position(super.getPos().row()-1, super.getPos().col()) };
            else
                  m = new Position[] { new Position(super.getPos().row()-1, super.getPos().col()) };
                  
            return m;
      }
      private Position[] blackMoves(){
            Position[] m;
            if(super.getPos() == super.getStartingPos())
                  m = new Position[] { new Position(super.getPos().row()+2, super.getPos().col()), new Position(super.getPos().row()+1, super.getPos().col()) };
            else
                  m = new Position[] { new Position(super.getPos().row()+1, super.getPos().col()) };
                  
            return m;
      }
      
      public boolean canPromote(){
            if(isWhite)
                  return promoteWhite();
            else
                  return promoteBlack();
      }
      private boolean promoteWhite(){
            return super.getPos().row() == 0;
      }
      private boolean promoteBlack(){
            return super.getPos().row() == Settings.COLS - 1;
      }
}
