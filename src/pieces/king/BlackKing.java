package pieces.king;

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
      public boolean isUnderCheck(Board board) {
            for(Piece piece : board.getWhitePieces())
                  if(piece.getLegalMoves().contains(this.getPos()))
                        return true;
            
            return false;
      }
}
