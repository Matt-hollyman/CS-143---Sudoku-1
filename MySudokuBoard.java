
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class MySudokuBoard {
    private char[][] bd;

    public MySudokuBoard(String fn) {
        bd = new char[9][9];

        try {
            Scanner sc = new Scanner(new File(fn));

            for (int r = 0; r < bd.length; r++) {
                String ln = sc.next();

                for (int c = 0; c < bd[r].length; c++) {
                    char ch = ln.charAt(c);

                    if (ch == '.') {
                        bd[r][c] = ' ';
                    } else {
                        bd[r][c] = ch;
                    }
                }
            }
            sc.close();

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found: " + fn, e);
        }
    }

    //check that every cell contains a number from 1 to 9 or a space.
    private boolean isValidData() {
        for (int r = 0; r < bd.length; r++) {
            for (int c = 0; c < bd[r].length; c++) {
                char number = bd[r][c];

                if (number != ' ' &&
                    (number < '1' || number > '9')) {
                    return false;
                }
            }
        }
        return true;
    }

    //check that each row has no duplicate numbers from 1 to 9.
    private boolean isValidRows() {
        for (int r = 0; r < bd.length; r++) {
            Set<Character> numbers = new HashSet<Character>();

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

    //check that each column has no duplicate numbers from 1 to 9.
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

    //combine all the method(four helpers)
    public boolean isValid() {
        return isValidData()
            && isValidRows()
            && isValidColumns()
            && isValidMiniSquares();
    }

    //Check if the Sudoku puzzle is solved
    public boolean isSolved() {
        Map<Character, Integer> counts = new HashMap<Character, Integer>();

        for (int r = 0; r < bd.length; r++) {
            for (int c = 0; c < bd[r].length; c++) {
                char number = bd[r][c];

                if (number >= '1' && number <= '9') {
                    if (counts.containsKey(number)) {
                        counts.put(number, counts.get(number) + 1);
                    } else {
                        counts.put(number, 1);
                    }
                }
            }
        }

        for (char number = '1'; number <= '9'; number++) {
            if (!counts.containsKey(number) || counts.get(number) != 9) {
                return false;
            }
        }

        return isValid();
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
