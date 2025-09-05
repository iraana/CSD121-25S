package lab3;

import lab3.ui.*;
import lab3.game.*;
import java.util.Scanner;

/**
 * Main class to run the Tic Tac Toe game.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Console console = new Console(scanner);
        boolean playAgain = true;

        // Loop to allow multiple games
        while (playAgain) {
            Board board = new Board();
            Player currentPlayer = null;

            System.out.println("Tic Tac Toe Time!");

            // Ask player to choose symbol and validate input
            while (true) {
                System.out.println("Choose your symbol: Enter 1 for X, 2 for O:");
                String input = scanner.nextLine().trim();
                if (input.equals("1")) {
                    currentPlayer = Player.X;
                    break;
                } else if (input.equals("2")) {
                    currentPlayer = Player.O;
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                }
            }

            // Main game loop
            while (true) {
                board.printBoard();
                System.out.println("Player " + currentPlayer.getSymbol() + ", it's your turn.");
                Position move = console.promptForPosition();

                if (board.placeMove(move, currentPlayer)) {
//                    board.printBoard();
                    char winner = board.checkWinner();
                    if (winner == currentPlayer.getSymbol()) {
                        board.printBoard();
                        console.showWinner(currentPlayer);
                        break;
                    } else if (board.checkDraw()) {
                        board.printBoard();
                        console.showDraw();
                        break;
                    }
                    currentPlayer = currentPlayer.next(); // Switch player
                } else {
                    System.out.println("That spot is already taken. Try again.");
                }
            }

            // Ask user if they want to play again
            while (true) {
                System.out.println("Play again? Enter 1 for Yes or 0 for No:");
                String again = scanner.nextLine().trim();
                if (again.equals("1")) {
                    playAgain = true;
                    break;
                } else if (again.equals("0")) {
                    playAgain = false;
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 1 or 0.");
                }
            }
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
