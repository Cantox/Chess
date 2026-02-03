package pieces;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import libs.*;

public class Piece {
      // Attributes
      private BufferedImage sprite = null;
      private Position startingPos;
      private Position pos;
      private ArrayList<Position> legalMoves = new ArrayList();
      
      // Constructor
      public Piece(Position startingPos) {
            this.startingPos = startingPos;
            this.pos = startingPos;
      }

      // Getter / Setter
      public BufferedImage getSprite() {
            return sprite;
      }
      public void setSprite(BufferedImage sprite) {
            this.sprite = sprite;
      }

      public Position getStartingPos() {
            return startingPos;
      }
      public void setStartingPos(Position startingPos) {
            this.startingPos = startingPos;
      }
      
      public Position getPos() {
            return pos;
      }
      public void setPos(Position pos) {
            this.pos = pos;
      }

      public ArrayList<Position> getLegalMoves() {
            return legalMoves;
      }
      public void setLegalMoves(ArrayList<Position> legalMoves) {
            this.legalMoves = legalMoves;
      }
      
      public BufferedImage LoadAndScaleSprite(String path){
            BufferedImage loadedSprite;
            
            try {
                  // Load the sprite
                  loadedSprite = ImageIO.read(getClass().getResource(path));

                  // Create a new BufferedImage of desired size
                  BufferedImage scaledSprite = new BufferedImage(
                      Settings.ICON_SIZE,           // width
                      Settings.ICON_SIZE,           // height
                      BufferedImage.TYPE_INT_ARGB   // supports transparency
                  );

                  // Draw the original sprite scaled into the new image
                  Graphics2D g2d = scaledSprite.createGraphics();
                  g2d.drawImage(loadedSprite, 0, 0, Settings.ICON_SIZE, Settings.ICON_SIZE, null);
                  g2d.dispose(); // always dispose Graphics2D

                  return scaledSprite;
            } catch(IOException e) {System.out.println(e);}
            
            return null;
      }
}
