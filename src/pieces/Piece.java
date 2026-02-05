package pieces;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import libs.*;
import main.Board;

public class Piece {
      private int color;
      private Position pos;
      private Position startingPos;
      public ArrayList<Position> legalMoves = new ArrayList();
      private int movesDone = 0;
      
      private BufferedImage sprite = null;

      public Piece(int color, Position startingPos) {
            if(color!=Settings.WHITE && color!=Settings.BLACK)
                  throw new IllegalArgumentException("Not a valid color");
            this.color = color;
            this.startingPos = startingPos;
            this.pos = startingPos;
            this.movesDone = 0;
      }
      
      public boolean isWhite(){
            return color == Settings.WHITE;
      }
      public boolean isLegalMove(Position move){
            for(Position legalMove : legalMoves)
                  if(legalMove.isEqual(move))
                        return true;
            return false;
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
            if(this.movesDone<0)
                  throw new IllegalArgumentException("movesDone went below 0");
      }

      public BufferedImage getSprite() {
            return sprite;
      }
      public void setSprite(BufferedImage sprite) {
            this.sprite = sprite;
      }
      public void scaleAndSetSprite(BufferedImage unscaledSprite){
            // Create a new BufferedImage of desired size
            BufferedImage scaledSprite = new BufferedImage(
                Settings.ICON_SIZE,           // width
                Settings.ICON_SIZE,           // height
                BufferedImage.TYPE_INT_ARGB   // supports transparency
            );

            // Draw the original sprite scaled into the new image
            Graphics2D g2d = scaledSprite.createGraphics();
            g2d.drawImage(unscaledSprite, 0, 0, Settings.ICON_SIZE, Settings.ICON_SIZE, null);
            g2d.dispose(); // always dispose Graphics2D
            
            this.sprite = scaledSprite;
      }
      
      public void calcLegalMoves(Board board, int currentTurn) {
            if(board==null)
                  throw new NullPointerException("Board is null");
            
            if(currentTurn!=Settings.WHITE && currentTurn!=Settings.BLACK)
                  throw new IllegalArgumentException("Not a valid player");
      }
      public Position[] getLegalMovesArray(){
            return legalMoves.toArray(Position[]::new);
      }
      
      public boolean canPromote()  {return false;}
      public boolean canCastle() {return false;}
      public boolean isUnderCheck(Piece[][] board) {return false;}
}
