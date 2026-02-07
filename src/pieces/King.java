package pieces;

import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import libs.*;
import main.Board;

public final class King extends Piece {
      boolean underCheck = false;
      
      public King(int color, Position startingPos) {
            super(color, startingPos);
            
            this.loadSprite(super.isWhite());
            underCheck = false;
      }
      
      @Override
      public void loadSprite(boolean isWhite) {
            if(isWhite){
                  try { super.scaleAndSetSprite(ImageIO.read( getClass().getResource(Settings.DEFAULT_WHITE_IMG_PATH + "/king.png") ) ); }
                  catch(IOException e) {System.out.println("Error loading sprite.\n" + e);}
            } else {
                  try { super.scaleAndSetSprite( ImageIO.read( getClass().getResource(Settings.DEFAULT_BLACK_IMG_PATH + "/king.png") ) ); }
                  catch(IOException e) {System.out.println("Error loading sprite.\n" + e);}
            }
      }
      
      
      @Override
      public void calcLegalMoves(Board board) {
            if(board==null)
                  throw new NullPointerException("Board is null");
            
            legalMoves.clear();
            
            for(int r=-1; r<=1; r++)
                  for(int c=-1; c<=1; c++){
                        if(r==0 && c==0)
                              continue;
                        int checkedRow = getPos().row()+r, checkedCol = getPos().col()+c;
                        if(!board.isValidTile(checkedRow, checkedCol))
                              continue;
                        Piece p = board.getPiece(checkedRow, checkedCol);
                        if(p!=null && p.isWhite()==this.isWhite())
                              continue;
                        board.move(this, checkedRow, checkedCol);
                        if(!isUnderCheck(board))
                              legalMoves.add(new Position(checkedRow,checkedCol));
                        board.undoLastMove();
                  }
      }
      
      @Override
      public boolean isUnderCheck(Board board) {
            int thisRow = getPos().row();
            int thisCol = getPos().col();
            
            if(pawnCheck(board, thisRow, thisCol))
                  return true;
            
            if(rook_queenCheck(board, thisRow, thisCol))
                  return true;
            
            if(bishop_queenCheck(board, thisRow, thisCol))
                  return true;
            
            if(knightCheck(board, thisRow, thisCol))
                  return true;
            
            return kingCheck(board, thisRow, thisCol);
      }
      
      private boolean pawnCheck(Board board, int thisRow, int thisCol){
            int offsetR;
            if(this.isWhite()) offsetR=-1;
            else offsetR=1;
            
            for(int offsetC=-1; offsetC<=1; offsetC+=2){
                  int checkedRow = thisRow+offsetR, checkedCol = thisCol+offsetC;
                  
                  if(!board.isValidTile(checkedRow, checkedCol))
                        continue;
                  if(board.getPiece(checkedRow, checkedCol) instanceof Pawn && board.getPiece(checkedRow, checkedCol).isWhite()!=this.isWhite())
                        return true;
            }
            
            return false;
      }
      
      private boolean rook_queenCheck(Board board, int thisRow, int thisCol){
            int[][] directions = { {0,1}, {1,0}, {0,-1}, {-1,0}};
            
            for(int[] dir : directions){
                  int checkedRow = thisRow + dir[0], checkedCol = thisCol + dir[1];

                  while(board.isValidTile(checkedRow, checkedCol) && board.getPiece(checkedRow, checkedCol)==null){
                        checkedRow += dir[0]; checkedCol += dir[1];
                  }
                  
                  if(!board.isValidTile(checkedRow, checkedCol))
                        continue;
                  
                  Piece p = board.getPiece(checkedRow, checkedCol);
                  if(p instanceof Rook || p instanceof Queen)
                        if(p.isWhite()!=this.isWhite())
                              return true;
            }
            
            return false;
      }
      
      private boolean bishop_queenCheck(Board board, int thisRow, int thisCol){
            int[][] directions = { {1,1}, {1,-1}, {-1,1}, {-1,-1}};
            
            for(int[] dir : directions){
                  int checkedRow = thisRow + dir[0], checkedCol = thisCol + dir[1];

                  while(board.isValidTile(checkedRow, checkedCol) && board.getPiece(checkedRow, checkedCol)==null){
                        checkedRow += dir[0]; checkedCol += dir[1];
                  }
                  
                  if(!board.isValidTile(checkedRow, checkedCol))
                        continue;
                  
                  Piece p = board.getPiece(checkedRow, checkedCol);
                  if(p instanceof Bishop || p instanceof Queen)
                        if(p.isWhite()!=this.isWhite())
                              return true;
            }
            
            return false;
      }
      
      private boolean knightCheck(Board board, int thisRow, int thisCol){
            // Two tile offset
            for(int twoDiff=-2; twoDiff<=2; twoDiff+=4)
                  for(int oneDiff=-1; oneDiff<=1; oneDiff+=2){
                        int checkedRow = thisRow + twoDiff, checkedCol = thisCol + oneDiff;
                        Piece p;
                        
                        if(!board.isValidTile(checkedRow, checkedCol)) {}
                        else {
                              p = board.getPiece(checkedRow, checkedCol);
                              if(p instanceof Knight && p.isWhite()!=this.isWhite())
                                    return true;
                        }
                        
                        checkedRow = thisRow + oneDiff; checkedCol = thisCol + twoDiff;
                        if(!board.isValidTile(checkedRow, checkedCol)) {}
                        else {
                              p = board.getPiece(checkedRow, checkedCol);
                              if(p instanceof Knight && p.isWhite()!=this.isWhite())
                                    return true;
                        }
                  }
            return false;
      }
      
      private boolean kingCheck(Board board, int thisRow, int thisCol){
            for(int r=-1; r<=1; r++)
                  for(int c=-1; c<=1; c++){
                        if(r==0 && c==0)
                              continue;
                        int checkedRow = thisRow + r, checkedCol = thisCol + c;
                        if(!board.isValidTile(checkedRow, checkedCol))
                              continue;
                        Piece p = board.getPiece(checkedRow, checkedCol);
                        if(p instanceof King && p.isWhite()!=this.isWhite())
                              return true;
                  }
            
            return false;
      }
}
