package pieces.bishop;

import libs.Position;
import libs.Settings;
import pieces.Piece;

public class BlackBishop extends Bishop {
      
      public BlackBishop(Position startingPos) {
            super(startingPos);
            
            super.setSprite(super.LoadAndScaleSprite(Settings.DEFAULT_PACK_PATH + "black/bishop.png"));
            
            super.setIsWhite(false);
      }
      
      @Override
      public Piece copy(){
            return new BlackBishop(new Position(getPos().row(), getPos().col()));
      }
      
}
