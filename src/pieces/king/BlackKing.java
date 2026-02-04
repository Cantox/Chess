package pieces.king;

import java.util.ArrayList;
import libs.Position;
import libs.Settings;
import main.Board;
import pieces.Piece;

public class BlackKing extends King {
      
      public BlackKing(Position startingPos) {
            super(startingPos);
            
            super.setSprite(super.LoadAndScaleSprite(Settings.DEFAULT_PACK_PATH + "black/king.png"));
            
            super.setIsWhite(false);
      }
      
      @Override
      public Piece copy(){
            return new BlackKing(new Position(getPos().row(), getPos().col()));
      }
      
      @Override
      public boolean isUnderCheck(Board board) {
            for(Piece piece : board.whitePieces)
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
                        
                        if(b[row+r][col+c]!=null && !b[row+r][col+c].isWhite()) // Check for tile occupied by "teamate"
                              continue;
                        
                        boolean tileAttacked = false; // Check for tile attacked
                        for(int i=0; i<board.whitePieces.size() && !tileAttacked; i++)
                              for(Position legalMove : board.whitePieces.get(i).getLegalMoves())
                                    if(legalMove.row()==row+r && legalMove.col()==col+c)
                                          tileAttacked=true;
                        if(tileAttacked)
                              continue;
                        
                        if(nextToKing(row+r,col+c,b)) // Check if tile is next to king
                              continue;
                        
                        if( (b[row+r][col+c]!=null && b[row+r][col+c].isWhite()) || b[row+r][col+c]==null) {
                              Board clonedBoard = board.copy(); // Clone
                              
                              if(clonedBoard.board[row+r][col+c]!=null && clonedBoard.board[row+r][col+c].isWhite())
                                    clonedBoard.whitePieces.remove(clonedBoard.board[row+r][col+c]); // Eat piece
                              
                              clonedBoard.board[row+r][col+c] = this.copy(); // Put the king in the new pos
                              clonedBoard.board[row+r][col+c].setPos(new Position(row+r,col+c)); // Update king's pos
                              clonedBoard.blackPieces.add( clonedBoard.board[row+r][col+c]); // Add new king to list
                              clonedBoard.blackPieces.remove( clonedBoard.board[row][col]); // Remove old king from list
                              clonedBoard.board[row][col]  = null; // Remove the king from its current pos
                              
                              
                              for(int boardRow=0; boardRow<Settings.ROWS; boardRow++) // Recalculate legal moves
                                    for(Piece piece : clonedBoard.board[boardRow]){
                                          if(piece!=null && !(piece instanceof King))
                                                piece.calculateLegalMoves(clonedBoard);
                                    }
                              
                              if(clonedBoard.board[row+r][col+c].isUnderCheck(clonedBoard)) // Check if king in new pos is under check
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
