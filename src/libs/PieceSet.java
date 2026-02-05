package libs;

import java.util.ArrayList;
import pieces.*;

public class PieceSet {
      public static Piece[] fullSet(){
            //Piece[] pieces = new Piece[32];
            ArrayList<Piece> pieces = new ArrayList();
            
            for(int i=0; i<Settings.COLS; i++)
                  pieces.add(new Pawn(Settings.WHITE, new Position(Settings.ROWS-2, i) ));
            
            for(int i=0; i<Settings.COLS; i++)
                  pieces.add(new Pawn(Settings.BLACK, new Position(1, i) ));
            
            return  pieces.toArray(Piece[]::new);
      }
}
