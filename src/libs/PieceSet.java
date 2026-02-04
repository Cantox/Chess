package libs;


import java.util.ArrayList;

import pieces.Piece;
import pieces.pawn.*;
import pieces.rook.*;
import pieces.bishop.*;
import pieces.horse.*;
import pieces.queen.*;
import pieces.king.*;

public class PieceSet {
      private final ArrayList<Piece> pieces = new ArrayList();
      
      public PieceSet(){
            // Black rooks
            pieces.add( new BlackRook( new Position(0,0) ) );
            pieces.add( new BlackRook( new Position(0,Settings.COLS-1) ) );
            
            // Black horses
            pieces.add( new BlackKnight( new Position(0,1) ) );
            pieces.add( new BlackKnight( new Position(0,Settings.COLS-2) ) );
            
            // Black bishops
            pieces.add( new BlackBishop( new Position(0,2) ) );
            pieces.add( new BlackBishop( new Position(0,Settings.COLS-3) ) );
            
            // Black queen
            pieces.add( new BlackQueen( new Position(0,3) ) );
            
            // Black king
            pieces.add( new BlackKing( new Position(0,4) ) );

            // Black pawns
            for(int i=0; i<Settings.COLS; i++)
                  pieces.add( new BlackPawn( new Position(1,i) ) );
            
            
            // White rooks
            pieces.add( new WhiteRook( new Position(Settings.ROWS-1,0) ) );
            pieces.add( new WhiteRook( new Position(Settings.ROWS-1,Settings.COLS-1) ) );
            
            // White horses
            pieces.add( new WhiteKnight( new Position(Settings.ROWS-1,1) ) );
            pieces.add( new WhiteKnight( new Position(Settings.ROWS-1,Settings.COLS-2) ) );
            
            // White bishops
            pieces.add( new WhiteBishop( new Position(Settings.ROWS-1,2) ) );
            pieces.add( new WhiteBishop( new Position(Settings.ROWS-1,Settings.COLS-3) ) );
            
            // White queen
            pieces.add( new WhiteQueen( new Position(Settings.ROWS-1,3) ) );
            
            // White king
            pieces.add( new WhiteKing( new Position(Settings.ROWS-1,4) ) );
            
            // White pawns
            for(int i=0; i<Settings.COLS; i++)
                  pieces.add( new WhitePawn( new Position(Settings.ROWS-2,i) ) );
      }
      
      public Piece[] getPieces(){
            return pieces.toArray(Piece[]::new);
      }
}
