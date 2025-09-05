package lab3.ui;
import lab3.game.*;
import java.util.Scanner;

/**
 * The Console class handles all user interactions through the terminal.
 * - displays prompts and messages to the player.
 * - reads and validates user input.
 * - converts input into a valid Position object.
 */
public class Console {
    private final Scanner scanner;

    // Constructor that takes a Scanner object to read user input
    public Console(Scanner scanner) {
        this.scanner = scanner;
    }

    // Prompts user for a position, validates it, and returns a Position object
    public Position promptForPosition() {
        while (true) {
            System.out.print("Enter your move (row[1-3] column[1-3], use a space between): ");
            String line = scanner.nextLine().trim();
            String[] moves = line.split("\\s+"); // handles extra spaces
            if (moves.length != 2) {
                System.out.println("Please enter exactly two numbers.");
                continue;
            }

            try {
                int rowInput = Integer.parseInt(moves[0]);
                int colInput = Integer.parseInt(moves[1]);
                Row row = Row.userInputForRow(rowInput);
                Column col = Column.userInputForColumn(colInput);
                return new Position(row, col);
            } catch (Exception e) {
                System.out.println("Invalid input. Numbers must be between 1 and 3.");
            }
        }
    }

    /**
     * Displays a message saying which player has won the game.
     * @param player the player who won
     */
    public void showWinner(Player player) {
        System.out.println("Player " + player.getSymbol() + " wins!");
    }

    // Displays a draw message
    public void showDraw() {
        System.out.println("It's a draw!");
    }




// this repeat logic from board claas all we need in console class just getting input from user,
// all print board and next move logic is already written in Board class

//    // Static Scanner object "sc" to take inputs
//    public static Scanner sc = new Scanner(System.in);
//    // TODO: create a CLASS method to display a tictactoe board
//    public static void printBoard(char[][] board) {
//        System.out.println("---------------------");
//        for(int i=0; i<3; i++) {
//            System.out.print("| ");
//            for(int j=0; j<3; j++) {
//                System.out.println(board[i][j] + " | ");
//            }
//            System.out.println("-------------------------");
//        }
//    }
//    // TODO: create a CLASS method to get the next move from a player
//    public static int[] getNextMove(char player) {
//        System.out.println("Player " + player + ", please enter your move (0,1,2): ");
//        int row=-1, col=-1; // initializing row and col with defaul values.
//
//        // looping using while
//        while (true) {
//            // try block: for exception handling
//            try {
//                row = sc.nextInt(); // taking user input for row
//                col = sc.nextInt(); // taking user input for col
//
//                // checking for upper and lower limits, MAX:3
//                if (row >= 0 && row < 3 && col >= 0 && col < 3) {
//                    break;
//                }
//                // in-case of error
//                else {
//                    System.out.println("Invalid input. Available options are: 0, 1 and 2.");
//                }
//            }
//            catch(Exception e) {
//                System.out.println(e.getMessage()); // printing stack-trace.
//            }
//        }
//        return new int[] {row, col}; // return row and col values to occupy space on the board.
//    }
}