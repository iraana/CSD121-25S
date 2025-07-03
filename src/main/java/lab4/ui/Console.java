package lab4.ui;

import lab4.game.*;

import java.util.Scanner;

/**
 * Import JColor to complete task 3 in lab4
 */
import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

/**
 * Helper methods for doing console-based user interaction
 */
public class Console {

    public static void println(String message) {
        System.out.println(colorize(message, BRIGHT_YELLOW_TEXT()));
    }

    /**
     * Prompt the user for input using the given promptMessage
     * @param promptMessage The message to prompt the user with
     * @return The user's response
     */
    public static String prompt(String promptMessage) {
        System.out.print(colorize(promptMessage, BRIGHT_YELLOW_TEXT()));
        var scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Display the given game board
     * @param board A tictactoe game board
     */
    public static void showBoard(Board board) {
        System.out.print(board);
    }

    /**
     * Repeatedly prompt the user for a position on which to place their next token.
     * If they enter an invalid response or an already-taken position they are re-prompted.
     * @param prompt The prompt to display to the user
     * @param board The current state of the game board
     * @return The position selected by the user
     */
    public static Position promptForPosition(String prompt, Board board) {


        var scanner = new Scanner(System.in);
        final String helpMessage = colorize("Input must be in the format 'row column', e.g., '1 2' or 't m' for the top middle cell.", BRIGHT_RED_TEXT());

        while ( true ) {
            System.out.print(colorize(prompt, BRIGHT_YELLOW_TEXT()));
            var input = scanner.nextLine().trim();

            if ( input.length() != 3 ) {
                System.out.println(helpMessage);
                continue;
            }

            var parts = input.split(" ");

            if ( parts.length != 2 ) {
                System.out.println(helpMessage);
                continue;
            }

            // The .from methods may throw if the user entered invalid location text, so we try/catch
            try {

                var pos = new Position(Row.from(parts[0]), Col.from(parts[1]));

                if (board.isOccupiedAt(pos)) {
                    System.out.println(colorize("That position is already taken.", BOLD(), BRIGHT_RED_TEXT(), YELLOW_BACK()));
                    continue;
                }

                return pos;
            } catch ( IllegalArgumentException e ) {
                System.out.println(helpMessage);
            }
        }
    }
}
