package pieces.rook;

import libs.Position;
import libs.Settings;

public class WhiteRook extends Rook {
      
      public WhiteRook(Position startingPos) {
            super(startingPos);
            
            super.setSprite(super.LoadAndScaleSprite(Settings.DEFAULT_PACK_PATH + "white/rook.png"));
            
            super.setIsWhite(true);
      }
}
