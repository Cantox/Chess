package libs;


import java.util.ArrayList;
import pieces.*;
import pieces.pawn.*;
import pieces.rook.*;

public class PieceSet {
      private final ArrayList<Piece> pieces = new ArrayList();
      
      public PieceSet(){
            // Black rook
            pieces.add( new BlackRook( new Position(0,0) ) );
            pieces.add( new BlackRook( new Position(0,Settings.COLS-1) ) );
            
            // White rook
            pieces.add( new WhiteRook( new Position(Settings.ROWS-1,0) ) );
            pieces.add( new WhiteRook( new Position(Settings.ROWS-1,Settings.COLS-1) ) );
            
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
