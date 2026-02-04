package pieces.horse;

import main.Board;
import java.util.ArrayList;
import libs.Position;
import libs.Settings;
import pieces.Piece;

public class Knight extends Piece {
      public Knight(Position startingPos) {
            super(startingPos);
      }
      
      @Override
      public void calculateLegalMoves(Board board){
            int row = super.getPos().row();
            int col = super.getPos().col();
            
            Piece[][] b = board.getBoard();
            ArrayList<Position> moves = new ArrayList();
            
            int newRow, newCol;
            
            // Up
            newRow = row-2;
            if(newRow>=0){
                  newCol = col+1;
                  if(newCol<Settings.COLS)
                        if(b[newRow][newCol]==null || b[newRow][newCol].isWhite() == !this.isWhite())
                              moves.add( new Position(newRow,newCol));
                  
                  newCol = col-1;
                  if(newCol>=0)
                        if(b[newRow][newCol]==null || b[newRow][newCol].isWhite() == !this.isWhite())
                              moves.add( new Position(newRow,newCol));
            }
            
            // Down
            newRow = row+2;
            if(newRow<Settings.ROWS){
                  newCol = col+1;
                  if(newCol<Settings.COLS)
                        if(b[newRow][newCol]==null || b[newRow][newCol].isWhite() == !this.isWhite())
                              moves.add( new Position(newRow,newCol));
                  
                  newCol = col-1;
                  if(newCol>=0)
                        if(b[newRow][newCol]==null || b[newRow][newCol].isWhite() == !this.isWhite())
                              moves.add( new Position(newRow,newCol));
            }
            
            // Left
            newCol = col-2;
            if(newCol>=0){
                  newRow = row+1;
                  if(newRow<Settings.ROWS)
                        if(b[newRow][newCol]==null || b[newRow][newCol].isWhite() == !this.isWhite())
                              moves.add( new Position(newRow,newCol));
                  
                  newRow = row-1;
                  if(newRow>=0)
                        if(b[newRow][newCol]==null || b[newRow][newCol].isWhite() == !this.isWhite())
                              moves.add( new Position(newRow,newCol));
            }
            
            // Right
            newCol = col+2;
            if(newCol<Settings.COLS){
                  newRow = row+1;
                  if(newRow<Settings.ROWS)
                        if(b[newRow][newCol]==null || b[newRow][newCol].isWhite() == !this.isWhite())
                              moves.add( new Position(newRow,newCol));
                  
                  newRow = row-1;
                  if(newRow>=0)
                        if(b[newRow][newCol]==null || b[newRow][newCol].isWhite() == !this.isWhite())
                              moves.add( new Position(newRow,newCol));
            }
            
            super.setLegalMoves(moves);
      }
      
}
