package pieces.bishop;

import libs.Position;
import libs.Settings;

public class WhiteBishop extends Bishop {
      
      public WhiteBishop(Position startingPos) {
            super(startingPos);
            
            super.setSprite(super.LoadAndScaleSprite(Settings.DEFAULT_PACK_PATH + "white/bishop.png"));
            
            super.setIsWhite(true);
      }
      
}
