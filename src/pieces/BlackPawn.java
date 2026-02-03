package pieces;

import libs.Position;

public class BlackPawn extends Pawn {
      public BlackPawn(Position startingPos){
            super(startingPos);
            
            super.setSprite(super.LoadAndScaleSprite("/img/black/pawn.png"));
            
            super.setIsWhite(false);
      }
      
      
      
}
