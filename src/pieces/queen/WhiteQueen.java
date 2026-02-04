package pieces.queen;

import libs.Position;
import libs.Settings;
import pieces.Piece;

public class WhiteQueen extends Queen {
      public WhiteQueen(Position startingPos) {
            super(startingPos);
            
            super.setSprite(super.LoadAndScaleSprite(Settings.DEFAULT_PACK_PATH + "white/queen.png"));
            
            super.setIsWhite(true);
      }
      
      @Override
      public Piece copy(){
            return new WhiteQueen(new Position(getPos().row(), getPos().col()));
      }
      
}
