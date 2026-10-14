import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//set four private helpers
//check that every cell contains a number from 0 to 9.
private boolean isValidData() {

}
//check that each row has no duplicate numbers from 1 to 9.
private boolean isValidRows() {
   
}
//check that each column has no duplicate numbers from 1 to 9.
private boolean isValidColumns() {

}
//check that each 3x3 mini-square has no duplicate numbers from 1 to 9.
private boolean isValidMiniSquares() {

}

//is valid method is to check every numbers are in the range1-9
private boolean isValidData() {
   for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[r].length; c++) {
         if (board[r][c] < 0 || board[r][c] > 9) {
            return false;
         }
      }
   }
   return true;
}


public class SudokuBoard {
    private char[][] bd;
    public SudokuBoard(String fn) throws FileNotFoundException {
        bd = new char[9][9];
        Scanner sc = new Scanner(new File(fn));
        for (int r = 0; r < bd.length; r++) {
            String ln = sc.next();
            for (int c = 0; c < bd[r].length; c++) {
                char ch = ln.charAt(c);
                
                if (ch == '.') {
                    bd[r][c] = ' ';
                } else {
                    bd[r][c] = ch; }
        }
         } sc.close();
    }

    public String toString() {
        String result = "";
        for (int r = 0; r < bd.length; r++) {
            for (int c = 0; c < bd[r].length; c++) {
               if (c % 3 == 0) {
                    result = result + "| ";
                }
            result = result + bd[r][c] + " ";
            }
            result = result + "|\n";
            if ((r + 1) % 3 == 0 && r < bd.length - 1) {
                result = result + "\n";
            }
        }
        return result;
    }
}