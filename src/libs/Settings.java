package libs;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Settings {
      public static final int ROWS = 8;
      public static final int COLS = 8;
      
      /** Dimension of the tile in pixels */
      public static final int TILE_SIZE = 640/5;
      /** Dimension of the icon in pixels */
      public static final int ICON_SIZE = 512/5;
      /** Dimension of the padding between Tile border and icon border in pixels */
      public static final int PADDING = (TILE_SIZE - ICON_SIZE) / 2;
      
      
      public static final Color DARK_TILE = new Color(119,149,86);
      public static final Color LIGHT_TILE = new Color(235,236,208);
      public static final Color HIGHLIGHT = new Color(255,255,0, 120);
      
      public static BufferedImage DOT_SPRITE = null;
      public static void loadDotSprite(){
            try { DOT_SPRITE = ImageIO.read(Settings.class.getResource("/_resourcepacks/default/dot.png")); }
            catch(IOException e) {System.out.println("Error loading dot sprite.\n" + e);}
      }
      public static final String DEFAULT_BLACK_IMG_PATH = "/_resourcepacks/default/img/black";
      public static final String DEFAULT_WHITE_IMG_PATH = "/_resourcepacks/default/img/white";
      
      
      public static final int WHITE = 1;
      public static final int BLACK = -1;
}
