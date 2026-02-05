package libs;

public record Position(int row, int col) {
      public Position sum(Position p){
            return new Position(this.row + p.row(), this.col + p.col());
      }
      
      public Position sub(Position p){
            return new Position(this.row - p.row(), this.col - p.col());
      }
      
      public Position mul(Position p){
            return new Position(this.row * p.row(), this.col * p.col());
      }
      
      public Position div(Position p){
            return new Position(this.row / p.row(), this.col / p.col());
      }
      
      public boolean isEqual(Position p){
            return this.row == p.row() && this.col == p.col();
      }
}
