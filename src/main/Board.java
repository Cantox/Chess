package main;

import pieces.king.King;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

import libs.*;
import pieces.Piece;
import pieces.queen.*;

public class Board extends JPanel {
      public final Piece[][] board = new Piece[Settings.ROWS][Settings.COLS];
      private final ArrayList<Position> highlightedSquares = new ArrayList();
      
      public final ArrayList<Piece> blackPieces = new ArrayList();
      public final ArrayList<Piece> whitePieces = new ArrayList();
      
      public Board(boolean empty) {
            if(!empty){
                  addMouseListener(new MouseAdapter(){
                        @Override
                        public void mousePressed(MouseEvent e){
                              handleClick(e.getX(), e.getY());
                        }
                  });
                  
                  Piece[] pieces = new PieceSet().getPieces();
                  for(Piece piece : pieces){
                        board[piece.getPos().row()][piece.getPos().col()] = piece;
                        
                        if(piece.isWhite()) whitePieces.add(piece);
                        else blackPieces.add(piece);
                  }
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
            
            if(selectedPos == null){
                  if(board[row][col] != null){
                        selectedPos = clicked;
                        for(Position p : board[row][col].getLegalMoves())
                              highlightedSquares.add(p);
                  }
            } else {
                  movePiece(selectedPos, clicked);
                  selectedPos = null;
                  highlightedSquares.clear();
            }
            
            repaint();
      }
      private void movePiece(Position start, Position dest){
            Piece piece = board[start.row()][start.col()];
            
            // If can't do the move, early return
            if(!piece.getLegalMoves().contains(dest))
                  return;
            
            board[start.row()][start.col()] = null;      
            
            // Remove "eaten" piece from list piece
            Piece destPiece = board[dest.row()][dest.col()];
            if(destPiece!=null)
                  if(destPiece.isWhite())
                        whitePieces.remove(destPiece);
                  else
                        blackPieces.remove(destPiece);
            
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
                        if(piece.canPromote()){
                              Position p = piece.getPos();
                              
                              if(piece.isWhite()){
                                    whitePieces.remove(piece);
                                    piece = new WhiteQueen(p);
                                    whitePieces.add(piece);
                              } else {
                                    blackPieces.remove(piece);
                                    piece = new BlackQueen(p);
                                    blackPieces.add(piece);
                              }
                        }
                        
                        // Legal moves
                        if(piece instanceof King) {
                              if(kingPos[0] == null) kingPos[0] = piece.getPos();
                              else kingPos[1] = piece.getPos();
                        }
                        else
                              piece.calculateLegalMoves(this);
                  }
            
            // Update unmovable pieces that are protecting the king
            for(Position pos : kingPos){
                  Piece king = board[pos.row()][pos.col()];
                  if(king.isWhite())
                        tryRemovingWhitePieces(king);
                  else
                        tryRemovingBlackPieces(king);
            }
            
            // Update kings legal moves
            for(Position pos : kingPos){
                  board[pos.row()][pos.col()].calculateLegalMoves(this);
            }
            
            // Handle king
            /*
            LET'S SAY I'M DOING THE WHITE KING
            I DON'T HAVE TO TOUCH THE KING'S LEGAL MOVES BECAUSE THEY ALREADY EXCLUDE THE ATTACKED SQUARES
            1- Create a list of all the attacking black pieces ( legalMoves.contatins(king.pos) )
            1.1- If the list is empty, i check if there are any legal moves doable by any of the white pieces (king included). If there aren't, it's a stall.
            2- Somehow calculate the movement paths that are threathening the king
            3- Update the legal moves of the white pieces to include only the ones that are on the threathening paths or on the attacking pieces themselves
            4- While updating the legal moves, use a flag to check if there is any move that can be done to save the king (flag up if i save a move)
            5- If there are no moves to defend the king (check flag) and the king can't move (already checked before while updating its legal moves), call checkmate
            */
      }
      
      private void tryRemovingWhitePieces(Piece king){
            // Make the clone
            Board boardClone = this.copy();
            
            // Check every white piece
            for(Piece whitePiece : whitePieces){
                  if(whitePiece == king)
                        continue;
                  
                  // Remove the piece
                  boardClone.whitePieces.remove(whitePiece);
                  boardClone.board[whitePiece.getPos().row()][whitePiece.getPos().col()] = null;
                  
                  if(king.isUnderCheck(boardClone)) // If king is uder check removing that piece
                        whitePiece.clearLegalMoves(); // Clear legal moves of that piece (because i can't move it)
                  
                  // Re-add the piece
                  boardClone.whitePieces.add(whitePiece);
                  boardClone.board[whitePiece.getPos().row()][whitePiece.getPos().col()] = whitePiece;
            }
      }
      private void tryRemovingBlackPieces(Piece king){
            // Make the clone
            Board boardClone = this.copy();
            
            // Check every white piece
            for(Piece blackPiece : blackPieces){
                  if(blackPiece == king)
                        continue;
                  
                  // Remove the piece
                  boardClone.blackPieces.remove(blackPiece);
                  boardClone.board[blackPiece.getPos().row()][blackPiece.getPos().col()] = null;
                  
                  if(king.isUnderCheck(boardClone)) // If king is uder check removing that piece
                        blackPiece.clearLegalMoves(); // Clear legal moves of that piece (because i can't move it)
                  
                  // Re-add the piece
                  boardClone.blackPieces.add(blackPiece);
                  boardClone.board[blackPiece.getPos().row()][blackPiece.getPos().col()] = blackPiece;
            }
      }
      
      
      public Board copy(){
            Board clone = new Board(true);
            for(int r=0; r<Settings.ROWS; r++)
                  for(Piece piece : board[r]){
                        if(piece==null)
                              continue;
                        
                        clone.board[piece.getPos().row()][piece.getPos().col()] = piece.copy();
                        if(piece.isWhite())
                              clone.whitePieces.add(piece.copy());
                        else
                              clone.blackPieces.add(piece.copy());
                  }
            
            return clone;
      }
}
