package pieces.rook;

import libs.Position;
import libs.Settings;
import pieces.Piece;

public class BlackRook extends Rook {
      
      public BlackRook(Position startingPos) {
            super(startingPos);
            
            super.setSprite(super.LoadAndScaleSprite(Settings.DEFAULT_PACK_PATH + "black/rook.png"));
            
            super.setIsWhite(false);
      }
      
      @Override
      public Piece copy(){
            return new BlackRook(new Position(getPos().row(), getPos().col()));
      }
      
}
