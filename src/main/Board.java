package main;

import java.util.ArrayList;
import libs.*;
import pieces.*;

public class Board {
      Piece[][] board = new Piece[Settings.ROWS][Settings.COLS];
      
      private Piece lastMoveFirstPiece = null;
      private Position lastMoveFirstPos = null;
      private Piece lastMoveSecondPiece = null;
      private Position lastMoveSecondPos = null;
      
      public Board(boolean empty){
            if(!empty){
                  // Fill with pieces
            }
      }
      
      public Piece[] getPiecesArray(){
            ArrayList<Piece> pieces = new ArrayList();
            for(int r=0; r<Settings.ROWS; r++)
                  for(Piece p : board[r])
                        if(p != null)
                              pieces.add(p);
            return pieces.toArray(Piece[]::new);
      }
      public Piece getPiece(Position piecePos){
            if(piecePos==null)
                  return null;
            
            return board[piecePos.row()][piecePos.col()];
      }
      
      public boolean isValidTile(Position tile){
            if(tile==null)
                  return false;
            
            return  tile.row()>=0 && tile.row()<Settings.ROWS && tile.col()>=0 && tile.col()<Settings.COLS;
      }
      public boolean isValidTile(int row, int col){
            return  row>=0 && row<Settings.ROWS && col>=0 && col<Settings.COLS;
      }
      
      public Position selectPiece(Position piecePos){
            if(piecePos==null)
                  return null;
            
            if(board[piecePos.row()][piecePos.col()]!=null)
                  return piecePos;
            else
                  return null;
      }
      
      public void move(Piece piece, Position dest){
            if(piece==null)
                  return;
            
            if(!isValidTile(dest)) // Also checks if tile==null
                  return;
            
            if(!piece.isLegalMove(dest))
                  return;
            
            // Save pieces (to do undo)
            lastMoveFirstPiece = piece;
            lastMoveFirstPos = piece.getPos();
            lastMoveSecondPiece = board[dest.row()][dest.col()];
            lastMoveSecondPos = dest;
            
            board[piece.getPos().row()][piece.getPos().col()] = null; // Remove from board
            board[dest.row()][dest.col()] = piece; // Put back on board in new position
            piece.setPos(dest); // Update piece position
            piece.setMovesDone(piece.getMovesDone()+1); // Update moves done with that piece
      }
      public void move(Position piecePos, Position dest){
            Piece piece = board[piecePos.row()][piecePos.col()];
            if(piece==null)
                  return;
            
            if(!isValidTile(dest))
                  return;
            
            if(!piece.isLegalMove(dest))
                  return;
            
            // Save pieces (to do undo)
            lastMoveFirstPiece = piece;
            lastMoveFirstPos = piece.getPos();
            lastMoveSecondPiece = board[dest.row()][dest.col()];
            lastMoveSecondPos = dest;
            
            board[piece.getPos().row()][piece.getPos().col()] = null; // Remove from board
            board[dest.row()][dest.col()] = piece; // Put back on board in new position
            board[dest.row()][dest.col()].setPos(dest); // Update piece position
      }
      
      public void undoLastMove(){
            board[lastMoveFirstPos.row()][lastMoveFirstPos.col()] = lastMoveFirstPiece;
            board[lastMoveSecondPos.row()][lastMoveSecondPos.col()] = lastMoveSecondPiece;
      }
}
