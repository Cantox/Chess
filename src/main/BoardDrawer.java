package main;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

import libs.*;
import pieces.Piece;

public class BoardDrawer extends JPanel {
      Piece[] pieces = null;
      Position selectedPiece = null;
      Position[] highlightedMoves = null;
      
      public BoardDrawer(){
            addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                              Main.handleClick(e.getX(), e.getY());
                        }
              });
      }
      
      @Override
      protected void paintComponent(Graphics g){
            super.paintComponent(g);
            
            drawTiles(g);
            if(selectedPiece!=null)
                  drawSelectedPieceHighligth(g);
            if(highlightedMoves!=null)
                  drawHighlightedMoves(g);
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
      private void drawSelectedPieceHighligth(Graphics g){
            g.setColor(Settings.HIGHLIGHT);
            g.fillRect(selectedPiece.col()*Settings.TILE_SIZE, selectedPiece.row()*Settings.TILE_SIZE, Settings.TILE_SIZE, Settings.TILE_SIZE);
      }
      private void drawHighlightedMoves(Graphics g){  
            for(Position move : this.highlightedMoves){
                  g.drawImage(Settings.DOT_SPRITE,
                                              (move.col()*Settings.TILE_SIZE) + Settings.PADDING,
                                              (move.row()*Settings.TILE_SIZE) + Settings.PADDING,
                                              Settings.ICON_SIZE,
                                              Settings.ICON_SIZE,
                                              null);
            }
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
      
      public void redraw(Piece[] pieces, Position selectedPiece, Position[] highlightedMoves){
            this.pieces = pieces;
            this.selectedPiece = selectedPiece;
            this.highlightedMoves = highlightedMoves;
            repaint();
      }
}
