package libs;

import pieces.*;

public class PieceSet {
      public static Piece[] fullSet(){
            Piece[] pieces = {
                  new Rook(Settings.BLACK, new Position(0,0)),
                  new Knight(Settings.BLACK, new Position(0,1)),
                  new Bishop(Settings.BLACK, new Position(0,2)),
                  new Queen(Settings.BLACK, new Position(0, 3)),
                  new King(Settings.BLACK, new Position(0, 4)),
                  new Bishop(Settings.BLACK, new Position(0,5)),
                  new Knight(Settings.BLACK, new Position(0,6)),
                  new Rook(Settings.BLACK, new Position(0,7)),
                  
                  new Pawn(Settings.BLACK, new Position(1,0)),
                  new Pawn(Settings.BLACK, new Position(1,1)),
                  new Pawn(Settings.BLACK, new Position(1,2)),
                  new Pawn(Settings.BLACK, new Position(1,3)),
                  new Pawn(Settings.BLACK, new Position(1,4)),
                  new Pawn(Settings.BLACK, new Position(1,5)),
                  new Pawn(Settings.BLACK, new Position(1,6)),
                  new Pawn(Settings.BLACK, new Position(1,7)),
                  
                  new Rook(Settings.WHITE, new Position(Settings.ROWS-1,0)),
                  new Knight(Settings.WHITE, new Position(Settings.ROWS-1,1)),
                  new Bishop(Settings.WHITE, new Position(Settings.ROWS-1,2)),
                  new Queen(Settings.WHITE, new Position(Settings.ROWS-1, 3)),
                  new King(Settings.WHITE, new Position(Settings.ROWS-1, 4)),
                  new Bishop(Settings.WHITE, new Position(Settings.ROWS-1,5)),
                  new Knight(Settings.WHITE, new Position(Settings.ROWS-1,6)),
                  new Rook(Settings.WHITE, new Position(Settings.ROWS-1,7)),
                  
                  new Pawn(Settings.WHITE, new Position(Settings.ROWS-2,0)),
                  new Pawn(Settings.WHITE, new Position(Settings.ROWS-2,1)),
                  new Pawn(Settings.WHITE, new Position(Settings.ROWS-2,2)),
                  new Pawn(Settings.WHITE, new Position(Settings.ROWS-2,3)),
                  new Pawn(Settings.WHITE, new Position(Settings.ROWS-2,4)),
                  new Pawn(Settings.WHITE, new Position(Settings.ROWS-2,5)),
                  new Pawn(Settings.WHITE, new Position(Settings.ROWS-2,6)),
                  new Pawn(Settings.WHITE, new Position(Settings.ROWS-2,7)),
            };
            
            return pieces;
      }
}
