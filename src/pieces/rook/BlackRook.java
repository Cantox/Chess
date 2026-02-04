package pieces.rook;

import libs.Position;
<<<<<<< HEAD
import libs.Settings;
import pieces.Piece;
=======
>>>>>>> parent of efb3a4c (added all pieces no king logic)

public class BlackRook extends Rook {
      
      public BlackRook(Position startingPos) {
            super(startingPos);
            
            super.setSprite(super.LoadAndScaleSprite("/img/black/rook.png"));
            
            super.setIsWhite(false);
      }
      
      @Override
      public Piece copy(){
            return new BlackRook(new Position(getPos().row(), getPos().col()));
      }
      
}
