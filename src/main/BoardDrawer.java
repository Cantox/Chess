package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

import libs.*;
import pieces.Piece;

public class BoardDrawer extends JPanel {
      Piece[] pieces = null;
      Position selectedPiece = null;
      Position[] highlightedMoves = null;
      boolean isWhiteTurn = true;
      
      private boolean checkmate = false;
      private int winner = 0;
      private boolean stalemate = false;
      
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
            
            whiteBoard(g);
            
            g.setColor(Color.DARK_GRAY);
            for(int r=0; r<Settings.ROWS; r++)
                  g.fillRect(Settings.ROWS*Settings.TILE_SIZE, r*Settings.TILE_SIZE, Settings.TILE_SIZE, Settings.TILE_SIZE);
            
            blackBoard(g);
            
            if(checkmate)
                  drawCheckmate(g);
            
            if(stalemate)
                  drawStalemate(g);
      }
      private void whiteBoard(Graphics g){
            drawTiles(g,0);
            if(isWhiteTurn && selectedPiece!=null)
                  drawSelectedPieceHighligth(g, Settings.WHITE, 0);
            drawPieces(g, Settings.WHITE, 0);
            if(isWhiteTurn && highlightedMoves!=null)
                  drawHighlightedMoves(g, Settings.WHITE, 0);
      }
      private void blackBoard(Graphics g){
            int xOffset = (Settings.COLS+1)*Settings.TILE_SIZE;
            drawTiles(g,xOffset);
            if(!isWhiteTurn && selectedPiece!=null)
                  drawSelectedPieceHighligth(g, Settings.BLACK, xOffset);
            drawPieces(g, Settings.BLACK, xOffset);
            if(!isWhiteTurn && highlightedMoves!=null)
                  drawHighlightedMoves(g, Settings.BLACK, xOffset);
      }
      
      private void drawTiles(Graphics g, int xOffset){
            for(int r=0; r<Settings.ROWS; r++)
                  for(int c=0; c<Settings.COLS; c++){
                        if((r+c)%2==0)
                              g.setColor(Settings.LIGHT_TILE);
                        else
                              g.setColor(Settings.DARK_TILE);
                        
                        g.fillRect(xOffset + (c*Settings.TILE_SIZE), r*Settings.TILE_SIZE, Settings.TILE_SIZE, Settings.TILE_SIZE);
                  }
      }
      private void drawSelectedPieceHighligth(Graphics g, int orientation, int xOffset){
            int drawRow = (orientation == Settings.WHITE) ? selectedPiece.row() : 7 - selectedPiece.row();
            int drawCol = (orientation == Settings.WHITE) ? selectedPiece.col() : 7 - selectedPiece.col();
            g.setColor(Settings.HIGHLIGHT);
            g.fillRect(xOffset + (drawCol*Settings.TILE_SIZE), drawRow*Settings.TILE_SIZE, Settings.TILE_SIZE, Settings.TILE_SIZE);
      }
      private void drawHighlightedMoves(Graphics g, int orientation, int xOffset){
            for(Position move : this.highlightedMoves){
                  int drawRow = (orientation == Settings.WHITE) ? move.row() : 7 - move.row();
                  int drawCol = (orientation == Settings.WHITE) ? move.col() : 7 - move.col();
                  g.drawImage(Settings.DOT_SPRITE,
                                              xOffset + (drawCol*Settings.TILE_SIZE) + Settings.PADDING,
                                              (drawRow*Settings.TILE_SIZE) + Settings.PADDING,
                                              Settings.ICON_SIZE,
                                              Settings.ICON_SIZE,
                                              null);
            }
      }
      private void drawPieces(Graphics g, int orientation, int xOffset){
            for(Piece piece  : pieces){
                  Position p = piece.getPos();
                  
                  int drawRow = (orientation == Settings.WHITE) ? p.row() : 7 - p.row();
                  int drawCol = (orientation == Settings.WHITE) ? p.col() : 7 - p.col();
                  
                  g.drawImage(piece.getSprite(),
                                              xOffset + (drawCol*Settings.TILE_SIZE) + Settings.PADDING,
                                              (drawRow*Settings.TILE_SIZE) + Settings.PADDING,
                                              Settings.ICON_SIZE,
                                              Settings.ICON_SIZE,
                                              null);
            }
      }
      
      public void redraw(Piece[] pieces, Position selectedPiece, Position[] highlightedMoves, int currentPlayer){
            isWhiteTurn = currentPlayer==Settings.WHITE;
            this.pieces = pieces;
            this.selectedPiece = selectedPiece;
            this.highlightedMoves = highlightedMoves;
            repaint();
      }
      
      private void drawCheckmate(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            
            g2d.setColor(new Color(255,255,255, 150));
            g2d.fillRect(0, 0, 2*(Settings.COLS*Settings.TILE_SIZE) + Settings.TILE_SIZE, Settings.ROWS * Settings.TILE_SIZE);

            String[] lines;
            if(winner==Settings.WHITE)
                  lines = new String[]{"CHECKMATE", "White won"};
            else
                  lines = new String[]{"CHECKMATE", "Black won"};
            
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 48));
            
            FontMetrics fm = g2d.getFontMetrics();
            int totalHeight = lines.length * fm.getHeight();
            
            int startY = (getHeight() - totalHeight) / 2 + fm.getAscent();
            
            for(int i=0; i<lines.length; i++){
                  int textWidth = fm.stringWidth(lines[i]);
                  int x = (getWidth() - textWidth) / 2;
                  int y = startY + i * fm.getHeight();
                  g2d.drawString(lines[i], x, y);
            }
      }
      
      private void drawStalemate(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            
            g2d.setColor(new Color(255,255,255, 150));
            g2d.fillRect(0, 0, 2*(Settings.COLS*Settings.TILE_SIZE) + Settings.TILE_SIZE, Settings.ROWS * Settings.TILE_SIZE);
            
            String txt = "STALEMATE";
            g2d.setColor(Color.RED); // or whatever color you want
            g2d.setFont(new Font("Arial", Font.BOLD, 48));
            
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(txt);
            int textHeight = fm.getHeight();

            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - textHeight) / 2 + fm.getAscent();

            g2d.drawString(txt, x, y);
      }
      
      public void checkmate(int winner){
            this.checkmate = true;
            this.winner = winner;
            redraw(pieces, selectedPiece, highlightedMoves, winner);
      }
      public void stalemate(int currentPlayer){
            this.stalemate = true;
            redraw(pieces, selectedPiece, highlightedMoves, winner);
      }
}
