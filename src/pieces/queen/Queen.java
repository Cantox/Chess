package pieces.queen;

import main.Board;
import java.util.ArrayList;
import libs.Position;
import libs.Settings;
import pieces.Piece;

public class Queen extends Piece {
      public Queen(Position startingPos) {
            super(startingPos);
      }
      
      @Override
      public void calculateLegalMoves(Board board){
            int row = super.getPos().row();
            int col = super.getPos().col();
            
            Piece[][] b = board.getBoard();
            ArrayList<Position> moves = new ArrayList();
            
            // Up
            int newRow = row-1;
            while(newRow>=0 && b[newRow][col]==null){
                  moves.add(new Position(newRow,col));
                  newRow--;
            }
            if(newRow>=0 && b[newRow][col].isWhite() == !this.isWhite())
                  moves.add(new Position(newRow,col));
            
            // Down
            newRow = row+1;
            while(newRow<Settings.ROWS && b[newRow][col]==null){
                  moves.add(new Position(newRow,col));
                  newRow++;
            }
            if(newRow<Settings.ROWS && b[newRow][col].isWhite() == !this.isWhite())
                  moves.add(new Position(newRow,col));
            
            // Left
            int newCol = col-1;
            while(newCol>=0 && b[row][newCol]==null){
                  moves.add(new Position(row,newCol));
                  newCol--;
            }
            if(newCol>=0 && b[row][newCol].isWhite() == !this.isWhite())
                  moves.add(new Position(row,newCol));
            
            // Right
            newCol = col+1;
            while(newCol<Settings.COLS && b[row][newCol]==null){
                  moves.add(new Position(row,newCol));
                  newCol++;
            }
            if(newCol<Settings.COLS && b[row][newCol].isWhite() == !this.isWhite())
                  moves.add(new Position(row,newCol));
            
            // Bottom-Right
            newRow = row+1; newCol = col+1;
            while(newRow<Settings.ROWS && newCol<Settings.COLS && b[newRow][newCol]==null){
                  moves.add(new Position(newRow,newCol));
                  newRow++;
                  newCol++;
            }
            if(newRow<Settings.ROWS && newCol<Settings.COLS && b[newRow][newCol].isWhite() == !this.isWhite())
                  moves.add(new Position(newRow,newCol));
            
            // Bottom-left
            newRow = row+1; newCol = col-1;
            while(newRow<Settings.ROWS && newCol>=0 && b[newRow][newCol]==null){
                  moves.add(new Position(newRow,newCol));
                  newRow++;
                  newCol--;
            }
            if(newRow<Settings.ROWS && newCol>=0 && b[newRow][newCol].isWhite() == !this.isWhite())
                  moves.add(new Position(newRow,newCol));
            
            // Top-Right
            newRow = row-1; newCol = col+1;
            while(newRow>=0 && newCol<Settings.COLS && b[newRow][newCol]==null){
                  moves.add(new Position(newRow,newCol));
                  newRow--;
                  newCol++;
            }
            if(newRow>=0 && newCol<Settings.COLS && b[newRow][newCol].isWhite() == !this.isWhite())
                  moves.add(new Position(newRow,newCol));
            
            // Top-Left
            newRow = row-1; newCol = col-1;
            while(newRow>=0 && newCol>=0 && b[newRow][newCol]==null){
                  moves.add(new Position(newRow,newCol));
                  newRow--;
                  newCol--;
            }
            if(newRow>=0 && newCol>=0 && b[newRow][newCol].isWhite() == !this.isWhite())
                  moves.add(new Position(newRow,newCol));
            
            super.setLegalMoves(moves);
      }
      
}
