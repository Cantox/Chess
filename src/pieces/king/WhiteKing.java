package pieces.king;

import java.util.ArrayList;
import libs.Position;
import libs.Settings;
import main.Board;
import pieces.Piece;

public class WhiteKing extends King {
      
      public WhiteKing(Position startingPos) {
            super(startingPos);
            
            super.setSprite(super.LoadAndScaleSprite(Settings.DEFAULT_PACK_PATH + "white/king.png"));
            
            super.setIsWhite(false);
      }
      
      @Override
      public boolean isUnderCheck(Board board) {
            for(Piece piece : board.getBlackPieces())
                  if(piece.getLegalMoves().contains(this.getPos()))
                        return true;
            
            return false;
      }
      
      @Override
      public void calculateLegalMoves(Board board) {
            int row = super.getPos().row();
            int col = super.getPos().col();
            
            Piece[][] b = board.getBoard();
            
            ArrayList<Position> moves = new ArrayList();
            
            for(int r=-1; r<=1; r++)
                  for(int c=-1; c<=1; c++){
                        if(!validTile(row+r, col+c)) // Check for valid tile
                              continue;
                        
                        if(r==0 && c==0) // Skip king's tile
                              continue;
                        
                        if(b[row+r][col+c]!=null && b[row+r][col+c].isWhite()) // Check for tile occupied by "teamate"
                              continue;
                        
                        boolean tileAttacked = false; // Check for tile attacked
                        for(int i=0; i<board.blackPieces.size() && !tileAttacked; i++)
                              for(Position legalMove : board.blackPieces.get(i).getLegalMoves())
                                    if(legalMove.row()==row+r && legalMove.col()==col+c){
                                          tileAttacked=true;
                                    }
                        if(tileAttacked)
                              continue;
                        
                        if(nextToKing(row+r,col+c,b)) // Check if tile is next to king
                              continue;
                        
                        if(b[row+r][col+c]!=null && !b[row+r][col+c].isWhite()) {
                              Piece[][] clonedBoard = b.clone();
                              ArrayList<Piece> clonedBlackPieces = (ArrayList<Piece>) board.blackPieces.clone();
                              ArrayList<Piece> clonedWhitePieces = (ArrayList<Piece>) board.whitePieces.clone();
                              
                              clonedBlackPieces.remove(clonedBoard[row+r][col+c]); // Eat piece
                              
                              clonedBoard[row+r][col+c] = this; // Move king
                              clonedWhitePieces.remove(this);
                              clonedWhitePieces.add(clonedBoard[row+r][col+c]);
                              
                              for(int boardRow=0; boardRow<Settings.ROWS; boardRow++) // Recalculate legal moves
                                    for(Piece piece : clonedBoard[boardRow]){
                                          if(piece!=null && !(piece instanceof King)){
                                                Board newBoard = new Board(true);
                                                newBoard.board = clonedBoard;
                                          }
                                    }
                              
                              if(this.isUnderCheck(clonedBoard)) // Check if king in new pos is under check
                                    continue;
                        }      
                        
                        moves.add(new Position( row+r,col+c));
                  }
            
            super.setLegalMoves(moves);
      }
      
      private boolean validTile(int r, int c){
            return r>=0  && r<Settings.ROWS && c>=0 && c<Settings.COLS;
      }
      
      private boolean nextToKing(int row, int col, Piece[][] b){
            for(int r=-1; r<=1; r++)
                  for(int c=-1; c<=1; c++){
                        if(!validTile(row+r,col+c))
                              continue;
                        
                        if(b[row+r][col+c] == this)
                              continue;
                        
                        if(b[row+r][col+c] instanceof King)
                              return true;
                  }
            return false;
      }
}
