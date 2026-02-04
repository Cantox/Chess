package pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import libs.*;

public class Piece {
      private int color;
      private Position pos;
      private Position startingPos;
      ArrayList<Position> legalMoves = new ArrayList();
      private int movesDone = 0;
      
      private BufferedImage sprite = null;

      public Piece(int color, Position startingPos) {
            if(color!=Settings.WHITE && color!=Settings.BLACK)
                  throw new IllegalArgumentException("Not a valid color");
            this.color = color;
            this.startingPos = startingPos;
            this.pos = startingPos;
      }
      
      public boolean isWhite(){
            return color == Settings.WHITE;
      }
      
      public void setPos(Position pos){
            this.pos = pos;
      }
      public void setPos(int row, int col){
            this.pos = new Position(row,col);
      }
      public Position getPos(){
            return this.pos;
      }
      
      public Position getStartingPos(){
            return this.startingPos;
      }

      public int getMovesDone() {
            return movesDone;
      }
      public void setMovesDone(int movesDone) {
            this.movesDone = movesDone;
      }

      public BufferedImage getSprite() {
            return sprite;
      }
      public void setSprite(BufferedImage sprite) {
            this.sprite = sprite;
      }
      
      public void calcLegalMoves(Piece[][] board, int currentTurn) {
            if(currentTurn!=Settings.PL_1 && currentTurn!=Settings.PL_2)
                  throw new IllegalArgumentException("Not a valid player");
      }
      
      public boolean canPromote()  {return false;}
      public boolean canCastle() {return false;}
      public boolean isUnderCheck(Piece[][] board) {return false;}
}
