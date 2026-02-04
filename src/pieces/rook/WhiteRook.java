package pieces.rook;

import libs.Position;
<<<<<<< HEAD
import libs.Settings;
import pieces.Piece;
=======
>>>>>>> parent of efb3a4c (added all pieces no king logic)

public class WhiteRook extends Rook {
      
      public WhiteRook(Position startingPos) {
            super(startingPos);
            
            super.setSprite(super.LoadAndScaleSprite("/img/white/rook.png"));
            
            super.setIsWhite(true);
      }
      
      @Override
      public Piece copy(){
            return new WhiteRook(new Position(getPos().row(), getPos().col()));
      }
}
