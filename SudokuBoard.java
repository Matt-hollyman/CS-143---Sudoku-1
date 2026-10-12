import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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