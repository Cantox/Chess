package pieces.horse;

import libs.Position;
import libs.Settings;
import pieces.Piece;

public class WhiteKnight extends Knight {
      
      public WhiteKnight(Position startingPos) {
            super(startingPos);
            
            super.setSprite(super.LoadAndScaleSprite(Settings.DEFAULT_PACK_PATH + "white/knight.png"));
            
            super.setIsWhite(true);
      }
      
      @Override
      public Piece copy(){
            return new WhiteKnight(new Position(getPos().row(), getPos().col()));
      }
      
}
