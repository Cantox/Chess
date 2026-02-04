package board;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

import pieces.*;
import libs.*;

public class Board extends JPanel {
      public final Piece[][] board = new Piece[Settings.ROWS][Settings.COLS];
      private final ArrayList<Position> highlightedSquares = new ArrayList();
      
      public Board(boolean empty) {
            addMouseListener(new MouseAdapter(){
                  @Override
                  public void mousePressed(MouseEvent e){
                        handleClick(e.getX(), e.getY());
                  }
            });
            
            if(!empty){
                  Piece[] pieces = new PieceSet().getPieces();
                  for(Piece piece : pieces)
                        board[piece.getPos().row()][piece.getPos().col()] = piece;
            }
      }

      public Piece[][] getBoard() {
            return board;
      }
      
      @Override
      protected void paintComponent(Graphics g){
            updateLegalMoves();
            
            super.paintComponent(g);
            
            for(int r=0; r<Settings.ROWS; r++)
                  for(int c=0; c<Settings.COLS; c++){
                        if( (r+c)%2==0 )
                              g.setColor(Settings.LIGHT_TILE);
                        else
                              g.setColor(Settings.DARK_TILE);
                        
                        g.fillRect(c * Settings.TILE_SIZE, r * Settings.TILE_SIZE, Settings.TILE_SIZE, Settings.TILE_SIZE);
                  }
            
            drawHighlightedSquares(g);
            drawPieces(g);
      }
      private void drawHighlightedSquares(Graphics g){
            for(Position square : highlightedSquares){
                  g.setColor(Settings.HIGHLIGHT);
                  g.fillRect(square.col() * Settings.TILE_SIZE, square.row() * Settings.TILE_SIZE, Settings.TILE_SIZE, Settings.TILE_SIZE);
            }
      }
      private void drawPieces(Graphics g){
            for(int r=0; r<Settings.ROWS; r++)
                  for(Piece piece : board[r])
                        if(piece != null){
                              Position p = piece.getPos();
                              int x = (p.col() * Settings.TILE_SIZE) + Settings.PADDING;
                              int y = (p.row() * Settings.TILE_SIZE) + Settings.PADDING;
                              
                              g.drawImage(
                                      piece.getSprite(), 
                                      x,
                                      y,
                                      Settings.ICON_SIZE, 
                                      Settings.ICON_SIZE, 
                                      null);
                        }
      }

      private Position selectedPos = null;
      private void handleClick(int mouseX, int mouseY){
            // Calculate board relative coords
            int col = mouseX / Settings.TILE_SIZE;
            int row = mouseY / Settings.TILE_SIZE;
            
            // Check if click happened inside board
            if(row<0 || row>=Settings.ROWS || col<0 || col>=Settings.COLS)
                  return;
            
            Position clicked = new Position(row,col);
            
            if(selectedPos == null){ // If is null means it's the first click
                  if(board[row][col] != null){
                        selectedPos = clicked;
                        for(Position p : board[row][col].getLegalMoves())
                              highlightedSquares.add(p);
                  }
            } else { // If there is already a position saved means it's the second
                  movePiece(selectedPos, clicked);
                  selectedPos = null;
                  highlightedSquares.clear();
            }
            
            repaint();
      }
      private void movePiece(Position start, Position dest){
            Piece piece = board[start.row()][start.col()];
            
            // If the destination isn't a legal move it returns early
            if(!piece.getLegalMoves().contains(dest))
                  return;
            
            // Remove the piece from its current position
            board[start.row()][start.col()] = null;
            
            // Change its position and update board
            piece.setPos(dest);
            board[dest.row()][dest.col()] = piece;
      }
      
      private void updateLegalMoves(){
            Position[] kingPos = new Position[2];
            
            // Update legal moves
            for(int r=0; r<Settings.ROWS; r++)
                  for(Piece piece : board[r]){
                        if(piece == null) continue;
                        
                        // Promotions
                        if(piece.getPos().row() == 0 && piece.canPromote()){/*piece = new WhiteQueen(piece.getPos());*/}
                        if(piece.getPos().row() == Settings.ROWS-1 && piece.canPromote()){/*piece = new BlackQueen(piece.getPos());*/}
                        
                        // Update legal moves
                        if(piece instanceof King) {
                              if(kingPos[0] == null) kingPos[0] = piece.getPos();
                              else kingPos[1] = piece.getPos();
                        }
                        else
                              piece.calculateLegalMoves(this);
                  }
                  
            // Handle king
      }
}
