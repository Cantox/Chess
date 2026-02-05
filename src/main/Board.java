package main;

import java.util.ArrayList;
import libs.*;

import pieces.*;

public class Board {
      public Piece[][] board = new Piece[Settings.ROWS][Settings.COLS];
      
      private Piece lastMovedPiece = null;
      private Position lastMovedPiecePos = null;
      private Piece lastEatenPiece = null;
      private Position lastEatenPiecePos = null;
      
      public Board(boolean empty){
            if(!empty){
                  Piece[] pieceSet  = PieceSet.fullSet();
                  for(Piece piece : pieceSet)
                        board[piece.getPos().row()][piece.getPos().col()] = piece;
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
                  throw new IllegalArgumentException("Piece pos is null");
            
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
      
      public boolean move(Piece piece, Position dest){
            if(piece==null || !isValidTile(dest) || !piece.isLegalMove(dest))
                  return false;
            
            // Save pieces (to do undo)
            lastMovedPiece = piece;
            lastMovedPiecePos = piece.getPos();
            lastEatenPiece = board[dest.row()][dest.col()];
            lastEatenPiecePos = dest;
            
            board[piece.getPos().row()][piece.getPos().col()] = null; // Remove from board
            board[dest.row()][dest.col()] = piece; // Put back on board in new position
            piece.setPos(dest); // Update piece position
            piece.setMovesDone(piece.getMovesDone()+1); // Update moves done with that piece
            
            return true;
      }
      public boolean move(Position piecePos, Position dest){
            Piece piece = board[piecePos.row()][piecePos.col()];
            if(piece==null || !isValidTile(dest) || !piece.isLegalMove(dest))
                  return false;
            
            // Save pieces (to do undo)
            lastMovedPiece = piece;
            lastMovedPiecePos = piece.getPos();
            lastEatenPiece = board[dest.row()][dest.col()];
            lastEatenPiecePos = dest;
            
            board[piece.getPos().row()][piece.getPos().col()] = null; // Remove from board
            board[dest.row()][dest.col()] = piece; // Put back on board in new position
            board[dest.row()][dest.col()].setPos(dest); // Update piece position
            piece.setMovesDone(piece.getMovesDone()+1); // Update moves done with that piece
            
            return true;
      }
      
      public void undoLastMove(){
            board[lastMovedPiecePos.row()][lastMovedPiecePos.col()] = lastMovedPiece;
            board[lastMovedPiecePos.row()][lastMovedPiecePos.col()].setPos(lastMovedPiecePos);
            lastMovedPiece.setMovesDone(lastMovedPiece.getMovesDone()-1);
            board[lastEatenPiecePos.row()][lastEatenPiecePos.col()] = lastEatenPiece;
            if(lastEatenPiece!=null)
                  board[lastEatenPiecePos.row()][lastEatenPiecePos.col()].setPos(lastEatenPiecePos);
      }
      
      public void recalculateLegalMoves(int currentPlayer){
            boolean isWhiteTurn = currentPlayer == Settings.WHITE;
            
            Piece[] pieces = getPiecesArray();
            // Update affected same team pieces
            // !!! IT UPDATES ALSO THE MOVED PIECE BECAUSE "LAST EATEN POS" IS STILL IN THE LEGAL MOVES OF THE MOVED PIECE !!!
            for(Piece piece : pieces)
                  if( (piece.legalMoves.contains(lastMovedPiecePos) || piece.legalMoves.contains(lastEatenPiecePos)) && piece.isWhite()==isWhiteTurn )
                        piece.calcLegalMoves(this, currentPlayer);
            
            // Update enemy pieces
            for(Piece piece : pieces)
                  if(piece.isWhite() == !isWhiteTurn)
                        piece.calcLegalMoves(this, currentPlayer);
                        
            
            // Update enemy king
      }
}
