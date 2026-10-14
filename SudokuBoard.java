import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

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

//isValidRows: Make sure that no row contains a duplicate value.
private boolean isValidRows() {
   for (int r = 0; r < board.length; r++) {
      Set<Integer> numbers = new HashSet<Integer>();
       
       //check for each row
       for (int c = 0; c < bd[r].length; c++) {
         char number = bd[r][c];

         if (number != ' ') {
            if (numbers.contains(number)) {
               return false;
            }
            numbers.add(number);
         }
      }

   }

   return true;
}
//check each columns
private boolean isValidColumns() {
   for (int c = 0; c < bd[0].length; c++) {
      Set<Character> numbers = new HashSet<Character>();

      for (int r = 0; r < bd.length; r++) {
         char number = bd[r][c];

         if (number != ' ') {
            if (numbers.contains(number)) {
               return false;
            }
            numbers.add(number);
         }
      }
   }
   return true;
}

//check each minisquare
private char[][] miniSquare(int spot) {
   char[][] mini = new char[3][3];

   for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
         mini[r][c] = bd[(spot - 1) / 3 * 3 + r]
                         [(spot - 1) % 3 * 3 + c];
      }
   }
   return mini;
}

//check if each mini square is valid
private boolean isValidMiniSquares() {
   for (int spot = 1; spot <= 9; spot++) {
      char[][] mini = miniSquare(spot);
      Set<Character> numbers = new HashSet<Character>();

      for (int r = 0; r < 3; r++) {
         for (int c = 0; c < 3; c++) {
            char number = mini[r][c];

            if (number != ' ') {
               if (numbers.contains(number)) {
                  return false;
               }
               numbers.add(number);
            }
         }
      }
   }
   return true;
}

//
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