
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class SudokuBoard {
    private char[][] board;

    public SudokuBoard(String fileName) {
        board = new char[9][9];

        try {
            Scanner fileScanner = new Scanner(new File(fileName));

            for (int row = 0; row < board.length; row++) {
                String currentLine = fileScanner.next();

                for (int column = 0; column < board[row].length; column++) {
                    char currentCharacter = currentLine.charAt(column);

                    if (currentCharacter == '.') {
                        board[row][column] = ' ';
                    } else {
                        board[row][column] = currentCharacter;
                    }
                }
            }
            fileScanner.close();

        } catch (FileNotFoundException exception) {
            throw new IllegalArgumentException("File not found: " + fileName, exception);
        }
    }

    //check that every cell contains a number from 1 to 9 or a space.
    private boolean isValidData() {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                char number = board[row][column];

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
        for (int row = 0; row < board.length; row++) {
            Set<Character> seenNumbers = new HashSet<Character>();

            for (int column = 0; column < board[row].length; column++) {
                char number = board[row][column];

                if (number != ' ') {
                    if (seenNumbers.contains(number)) {
                        return false;
                    }
                    seenNumbers.add(number);
                }
            }
        }
        return true;
    }

    //check that each column has no duplicate numbers from 1 to 9.
    private boolean isValidColumns() {
        for (int column = 0; column < board[0].length; column++) {
            Set<Character> seenNumbers = new HashSet<Character>();

            for (int row = 0; row < board.length; row++) {
                char number = board[row][column];

                if (number != ' ') {
                    if (seenNumbers.contains(number)) {
                        return false;
                    }
                    seenNumbers.add(number);
                }
            }
        }
        return true;
    }

    //check each minisquare
    private char[][] miniSquare(int squareNumber) {
        char[][] miniSquareBoard = new char[3][3];

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                miniSquareBoard[row][column] = board[(squareNumber - 1) / 3 * 3 + row]
                               [(squareNumber - 1) % 3 * 3 + column];
            }
        }
        return miniSquareBoard;
    }

    //check if each mini square is valid
    private boolean isValidMiniSquares() {
        for (int squareNumber = 1; squareNumber <= 9; squareNumber++) {
            char[][] miniSquareBoard = miniSquare(squareNumber);
            Set<Character> seenNumbers = new HashSet<Character>();

            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    char number = miniSquareBoard[row][column];

                    if (number != ' ') {
                        if (seenNumbers.contains(number)) {
                            return false;
                        }
                        seenNumbers.add(number);
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
        Map<Character, Integer> numberCounts = new HashMap<Character, Integer>();

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                char number = board[row][column];

                if (number >= '1' && number <= '9') {
                    if (numberCounts.containsKey(number)) {
                        numberCounts.put(number, numberCounts.get(number) + 1);
                    } else {
                        numberCounts.put(number, 1);
                    }
                }
            }
        }

        for (char number = '1'; number <= '9'; number++) {
            if (!numberCounts.containsKey(number) || numberCounts.get(number) != 9) {
                return false;
            }
        }

        return isValid();
    }

    public String toString() {
        String result = "";

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (column % 3 == 0) {
                    result = result + "| ";
                }

                result = result + board[row][column] + " ";
            }

            result = result + "|\n";

            if ((row + 1) % 3 == 0 && row < board.length - 1) {
                result = result + "\n";
            }
        }
        return result;
    }
}
