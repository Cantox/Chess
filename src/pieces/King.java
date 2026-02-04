package pieces;

import libs.Position;

public class King extends Piece {
      public King(Position startingPos){
            super(startingPos);
      }
      
      @Override
      public boolean isUnderCheck() {return false;}
}
