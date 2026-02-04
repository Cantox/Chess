package pieces.rook;

import libs.Position;

public class BlackRook extends Rook {
      
      public BlackRook(Position startingPos) {
            super(startingPos);
            
            super.setSprite(super.LoadAndScaleSprite("/img/black/rook.png"));
            
            super.setIsWhite(false);
      }
      
}
