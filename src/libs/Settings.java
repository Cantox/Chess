package libs;

import java.awt.Color;

public class Settings {
      public static final int ROWS = 8;
      public static final int COLS = 8;
      
      /** Dimension of the tile in pixels */
      public static final int TILE_SIZE = 80;
      /** Dimension of the icon in pixels */
      public static final int ICON_SIZE = 64;
      /** Dimension of the padding between Tile border and icon border in pixels */
      public static final int PADDING = (TILE_SIZE - ICON_SIZE) / 2;
      
      
      public static final Color DARK_TILE = new Color(119,149,86);
      public static final Color LIGHT_TILE = new Color(235,236,208);
}
