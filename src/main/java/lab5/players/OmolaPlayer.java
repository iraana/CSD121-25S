package lab5.players;

import lab5.game.*;
import java.util.List;

public class OmolaPlayer extends Player {

    public OmolaPlayer(String name) {
        super(name);
    }

    /**
     * This method decides the next move Omola should make on the current game board.
     * If there's a move that would win the game for Omola, take it. If there's a move for opponent to win, Omola blocks it.
     * Otherwise, he just takes the first available open position.
     * @param currentBoard The current state of the game board
     * @return The position Omola chooses to play next
     */
    @Override
    public Position pickNextMove(Board currentBoard) {
        PlayerToken omolaToken = currentBoard.getNextTurnToken();
        PlayerToken opponentToken = omolaToken.opponent();

        // List of all currently empty positions on the board
        List<Position> emptyCells = currentBoard.getEmptyCells();

        // Finding a winning move for Omola
        for (Position pos : emptyCells) {
            Board testBoard = new Board(currentBoard); // a copy of the board to simulate placing Omola's token
            testBoard.place(pos, omolaToken);
            // Check if this move would make Omola win
            if (testBoard.getWinner() == omolaToken) {
                return pos; // if yes, taking this moe
            }
        }

        // Blocking a winning move for the opponent
        for (Position pos : emptyCells) {
            Board testBoard = new Board(currentBoard);
            testBoard.place(pos, opponentToken);
            if (testBoard.getWinner() == opponentToken) {
                return pos; // if the opponent would win by taking this position, we block them and our token there
            }
        }

        // Pick first available position if no winning or blocking moves are possible
        return emptyCells.get(0);
    }
}
