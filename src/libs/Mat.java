package libs;

public class Mat {
      public static void print(int[][] mat){
            for(int r=0; r<mat.length; r++){
                  for(int c=0; c<mat[0].length; c++){
                        if(c!=mat[0].length-1)
                              System.out.print(" " + mat[r][c] + " |");
                        else
                              System.out.println(" " + mat[r][c]);
                  }
                  if(r!=mat.length-1){
                        for(int i=0; i<mat[0].length; i++)
                              System.out.print("--- ");
                        System.out.println();
                  }
            }
      }
      
      
}
