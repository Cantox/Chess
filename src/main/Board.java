package main;

import java.util.ArrayList;
import libs.*;
import pieces.*;

public class Board {
      Piece[][] board = new Piece[Settings.ROWS][Settings.COLS];
      
      public Board(boolean empty){
            if(!empty){
                  // Fill with pieces
            }
      }
      
      public Piece[] getPieceArray(){
            ArrayList<Piece> pieces = new ArrayList();
            for(int r=0; r<Settings.ROWS; r++)
                  for(Piece p : board[r])
                        if(p != null)
                              pieces.add(p);
            return pieces.toArray(Piece[]::new);
      }
}
