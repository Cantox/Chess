package pieces.horse;

import libs.Position;
import libs.Settings;

public class WhiteKnight extends Knight {
      
      public WhiteKnight(Position startingPos) {
            super(startingPos);
            
            super.setSprite(super.LoadAndScaleSprite(Settings.DEFAULT_PACK_PATH + "white/knight.png"));
            
            super.setIsWhite(true);
      }
      
}
