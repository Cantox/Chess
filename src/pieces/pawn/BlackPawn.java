package pieces.pawn;

import java.util.ArrayList;

import main.Board;
import libs.Position;
import libs.Settings;
import pieces.Piece;

public class BlackPawn extends Pawn {
      public BlackPawn(Position startingPos){
            super(startingPos);
            
            super.setSprite(super.LoadAndScaleSprite(Settings.DEFAULT_PACK_PATH + "black/pawn.png"));
            
            super.setIsWhite(false);
      }
      
      @Override
      public Piece copy(){
            return new BlackPawn(new Position(getPos().row(), getPos().col()));
      }
      
      @Override
      public void calculateLegalMoves(Board board){
            int row = super.getPos().row();
            int col = super.getPos().col();
            
            Piece[][] b = board.getBoard();
            ArrayList<Position> moves = new ArrayList();
            
            // 2 step for first move
            if(super.getPos() == super.getStartingPos() && b[row+2][col] == null && b[row+1][col] == null)
                  moves.add(new Position(row+2, col));
            
            // 1 step forward
            if(row+1 < Settings.ROWS)
                  if(b[row+1][col] == null)
                        moves.add(new Position(row+1,col));
            
            // Capture
            if(col-1 >= 0 && row+1 < Settings.ROWS)
                  if(b[row+1][col-1] != null && b[row+1][col-1].isWhite())
                        moves.add(new Position(row+1, col-1));
            
            if(col+1 < Settings.COLS && row+1 < Settings.ROWS)
                  if(b[row+1][col+1] != null && b[row+1][col+1].isWhite())
                        moves.add(new Position(row+1, col+1));
            
            super.setLegalMoves(moves);
      }
      
      @Override
      public boolean canPromote(){
            return super.getPos().row() == Settings.ROWS-1;
      }
}
