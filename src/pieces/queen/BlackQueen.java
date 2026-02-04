package pieces.queen;

import libs.Position;
import libs.Settings;
import pieces.Piece;

public class BlackQueen extends Queen {
      public BlackQueen(Position startingPos) {
            super(startingPos);
            
            super.setSprite(super.LoadAndScaleSprite(Settings.DEFAULT_PACK_PATH + "black/queen.png"));
            
            super.setIsWhite(false);
      }
      
      @Override
      public Piece copy(){
            return new BlackQueen(new Position(getPos().row(), getPos().col()));
      }
      
}
