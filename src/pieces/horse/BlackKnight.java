package pieces.horse;

import libs.Position;
import libs.Settings;

public class BlackKnight extends Knight {
      
      public BlackKnight(Position startingPos) {
            super(startingPos);
            
            super.setSprite(super.LoadAndScaleSprite(Settings.DEFAULT_PACK_PATH + "black/knight.png"));
            
            super.setIsWhite(false);
      }
      
}
