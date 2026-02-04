package pieces.rook;

import libs.Position;

public class WhiteRook extends Rook {
      
      public WhiteRook(Position startingPos) {
            super(startingPos);
            
            super.setSprite(super.LoadAndScaleSprite("/img/white/rook.png"));
            
            super.setIsWhite(true);
      }
}
