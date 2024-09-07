//package internship;
import java.util.Scanner;

public class connecting_four_game {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static char[][] board = new char[ROWS][COLS];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        printBoard();

        while (true) {
            int column = getPlayerMove();
            dropDisc(column);
            printBoard();

            if (checkWin()) {
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            }

            switchPlayer();
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard() {
        System.out.println(" 1 2 3 4 5 6 7");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------------");
    }

    private static int getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        int column;

        while (true) {
            System.out.print("Player " + currentPlayer + ", enter your move (1-7): ");
            column = scanner.nextInt();

            if (isValidMove(column)) {
                break;
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }

        return column - 1;
    }

    private static boolean isValidMove(int column) {
        return column >= 1 && column <= COLS && board[0][column - 1] == ' ';
    }

    private static void dropDisc(int column) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][column] == ' ') {
                board[i][column] = currentPlayer;
                break;
            }
        }
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private static boolean checkWin() {
        // Check horizontally
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j <= COLS - 4; j++) {
                if (board[i][j] != ' '
                        && board[i][j] == board[i][j + 1]
                        && board[i][j] == board[i][j + 2]
                        && board[i][j] == board[i][j + 3]) {
                    return true;
                }
            }
        }

        // Check vertically
        for (int j = 0; j < COLS; j++) {
            for (int i = 0; i <= ROWS - 4; i++) {
                if (board[i][j] != ' '
                        && board[i][j] == board[i + 1][j]
                        && board[i][j] == board[i + 2][j]
                        && board[i][j] == board[i + 3][j]) {
                    return true;
                }
            }
        }

        // Check diagonally (bottom-left to top-right)
        for (int i = ROWS - 1; i >= 3; i--) {
            for (int j = 0; j <= COLS - 4; j++) {
                if (board[i][j] != ' '
                        && board[i][j] == board[i - 1][j + 1]
                        && board[i][j] == board[i - 2][j + 2]
                        && board[i][j] == board[i - 3][j + 3]) {
                    return true;
                }
            }
        }

        // Check diagonally (top-left to bottom-right)
        for (int i = 0; i <= ROWS - 4; i++) {
            for (int j = 0; j <= COLS - 4; j++) {
                if (board[i][j] != ' '
                        && board[i][j] == board[i + 1][j + 1]
                        && board[i][j] == board[i + 2][j + 2]
                        && board[i][j] == board[i + 3][j + 3]) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isBoardFull() {
        for (int j = 0; j < COLS; j++) {
            if (board[0][j] == ' ') {
                return false;
            }
        }
        return true;
    }
}
