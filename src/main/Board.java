package main;

import java.util.ArrayList;
import java.util.Iterator;

import libs.*;

import pieces.*;

public class Board {
      public Piece[][] board = new Piece[Settings.ROWS][Settings.COLS];
      
      private Piece lastMovedPiece = null;
      private Position lastMovedPiecePos = null;
      private Piece lastCapturedPiece = null;
      private Position lastCapturedPiecePos = null;
      
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
            
            if(!isValidTile(piecePos))
                  throw new IllegalArgumentException("Piece pos is not a valid tile");
            
            return board[piecePos.row()][piecePos.col()];
      }
      public Piece getPiece(int row, int col){
            if(!isValidTile(row, col))
                  throw new IllegalArgumentException("Piece pos is not a valid tile");
            
            return board[row][col];
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
            if(piece==null || !isValidTile(dest))
                  return false;
            
            // Save pieces (to do undo)
            lastMovedPiece = piece;
            lastMovedPiecePos = piece.getPos();
            lastCapturedPiece = board[dest.row()][dest.col()];
            lastCapturedPiecePos = dest;
            
            board[piece.getPos().row()][piece.getPos().col()] = null; // Remove from board
            board[dest.row()][dest.col()] = piece; // Put back on board in new position
            piece.setPos(dest); // Update piece position
            piece.setMovesDone(piece.getMovesDone()+1); // Update moves done with that piece
            
            return true;
      }
      public boolean move(Position piecePos, Position dest){
            Piece piece = board[piecePos.row()][piecePos.col()];
            if(piece==null || !isValidTile(dest))
                  return false;
            
            // Save pieces (to do undo)
            lastMovedPiece = piece;
            lastMovedPiecePos = piece.getPos();
            lastCapturedPiece = board[dest.row()][dest.col()];
            lastCapturedPiecePos = dest;
            
            board[piece.getPos().row()][piece.getPos().col()] = null; // Remove from board
            board[dest.row()][dest.col()] = piece; // Put back on board in new position
            board[dest.row()][dest.col()].setPos(dest); // Update piece position
            piece.setMovesDone(piece.getMovesDone()+1); // Update moves done with that piece
            
            return true;
      }
      public boolean move(Piece piece, int destRow, int destCol){
            if(piece==null || !isValidTile(destRow,destCol))
                  return false;
            
            // Save pieces (to do undo)
            lastMovedPiece = piece;
            lastMovedPiecePos = piece.getPos();
            lastCapturedPiece = board[destRow][destCol];
            lastCapturedPiecePos = new Position(destRow, destCol);
            
            board[piece.getPos().row()][piece.getPos().col()] = null; // Remove from board
            board[destRow][destCol] = piece; // Put back on board in new position
            piece.setPos(new Position(destRow, destCol)); // Update piece position
            piece.setMovesDone(piece.getMovesDone()+1); // Update moves done with that piece
            
            return true;
      }
      
      public void undoLastMove(){
            board[lastMovedPiecePos.row()][lastMovedPiecePos.col()] = lastMovedPiece;
            board[lastMovedPiecePos.row()][lastMovedPiecePos.col()].setPos(lastMovedPiecePos);
            lastMovedPiece.setMovesDone(lastMovedPiece.getMovesDone()-1);
            board[lastCapturedPiecePos.row()][lastCapturedPiecePos.col()] = lastCapturedPiece;
            if(lastCapturedPiece!=null)
                  board[lastCapturedPiecePos.row()][lastCapturedPiecePos.col()].setPos(lastCapturedPiecePos);
      }
      
      public void recalculateLegalMoves(int currentPlayer){
            boolean isWhiteTurn = currentPlayer == Settings.WHITE;
            Piece[] pieces = getPiecesArray();
            Piece enemyKing = null;
            
            // Update enemy pieces
            for(Piece piece : pieces){
                  if(piece.isWhite()!=isWhiteTurn && piece instanceof King)
                        enemyKing = piece;
                  piece.calcLegalMoves(this);
            }
            
            if(enemyKing==null)
                  throw new IllegalArgumentException("enemy king is null");
            
            // Remove moves that expose the king
            for(Piece piece : pieces){
                  if(piece.isWhite()==isWhiteTurn)
                        continue;

                  Iterator<Position> it = piece.legalMoves.iterator();
                  while(it.hasNext()){
                        Position legalMove = it.next();
                        move(piece, legalMove);
                        if(enemyKing.isUnderCheck(this))
                              it.remove();
                        undoLastMove();
                  }
            }
            
            // Check if enemy is under checkmate or stalemate
            if(enemyKing.isUnderCheckmate(this)){
                  if(currentPlayer==Settings.WHITE) System.out.println("Checkmate, white won!");
                  else System.out.println("Checkmate, black won!");
                  System.exit(0);
            }
            if(enemyKing.isUnderStalemate(this)){
                  System.out.println("Stalemate!");
                  System.exit(0);
            }
      }
}
