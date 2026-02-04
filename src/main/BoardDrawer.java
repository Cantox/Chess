package main;

import java.awt.Graphics;
import javax.swing.JPanel;

import libs.*;
import pieces.Piece;

public class BoardDrawer extends JPanel {
      Piece[] pieces;
      Position[] highlightedTiles;
      
      @Override
      protected void paintComponent(Graphics g){
            super.paintComponent(g);
            
            drawTiles(g);
            drawHighlightedTiles(g);
            drawPieces(g);
      }
      private void drawTiles(Graphics g){
            for(int r=0; r<Settings.ROWS; r++)
                  for(int c=0; c<Settings.COLS; c++){
                        if((r+c)%2==0)
                              g.setColor(Settings.LIGHT_TILE);
                        else
                              g.setColor(Settings.DARK_TILE);
                        
                        g.fillRect(c*Settings.TILE_SIZE, r*Settings.TILE_SIZE, Settings.TILE_SIZE, Settings.TILE_SIZE);
                  }
      }
      private void drawHighlightedTiles(Graphics g){
            g.setColor(Settings.HIGHLIGHT);
            for(Position tile : this.highlightedTiles)
                  g.fillRect(tile.col()*Settings.TILE_SIZE, tile.row()*Settings.TILE_SIZE, Settings.TILE_SIZE, Settings.TILE_SIZE);
      }
      private void drawPieces(Graphics g){
            for(Piece piece  : pieces){
                  Position p = piece.getPos();
                  g.drawImage(piece.getSprite(),
                                              (p.col()*Settings.TILE_SIZE) + Settings.PADDING,
                                              (p.row()*Settings.TILE_SIZE) + Settings.PADDING,
                                              Settings.ICON_SIZE,
                                              Settings.ICON_SIZE,
                                              null);
            }
      }
      
      public void reDraw(Piece[] pieces, Position[] highlightedTiles){
            this.pieces = pieces;
            this.highlightedTiles = highlightedTiles;
            repaint();
      }
}
