package libs;

import java.util.ArrayList;
import pieces.*;

public class PieceSet {
      private final ArrayList<Piece> pieces = new ArrayList();
      
      public PieceSet(){
            // Black pawns
            for(int i=0; i<Settings.COLS; i++)
                  pieces.add(new BlackPawn(new Position(1,i)));
            
            // White pawns
            for(int i=0; i<Settings.COLS; i++)
                  pieces.add(new WhitePawn(new Position(Settings.ROWS-2,i)));
      }
      
      public Piece[] getPieces(){
            return pieces.toArray(new Piece[0]);
      }
}
