package pieces;

import libs.Position;

public class WhitePawn extends Pawn {
      public WhitePawn(Position startingPos){
            super(startingPos);
            
            super.setSprite(super.LoadAndScaleSprite("/img/white/pawn.png"));
            
            super.setIsWhite(true);
      }
}
