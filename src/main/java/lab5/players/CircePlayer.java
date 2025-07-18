package lab5.players;

import lab5.game.Board;
import lab5.game.Col;
import lab5.game.Position;
import lab5.game.Row;

public class CircePlayer extends Player {

    public CircePlayer(String name) {
        super(name);
    }

    /**
     * Chooses the next move based on Circe's strategy.
     * Circe starts from center, then goes clockwise from top-middle till top-left
     * @param currentBoard The current state of the game board
     * @return The position on the board where the player wants to place their token
     */
    @Override
    public Position pickNextMove(Board currentBoard) {
        Position[] nextMoveOrder = {
                new Position(Row.Middle, Col.Middle), // center position
                new Position(Row.Top, Col.Middle),    // top-middle position
                new Position(Row.Top, Col.Right),     // top-right position
                new Position(Row.Middle, Col.Right),  // middle-right position
                new Position(Row.Bottom, Col.Right),  // bottom-right position
                new Position(Row.Bottom, Col.Middle), // bottom-middle position
                new Position(Row.Bottom, Col.Left),   // bottom-left position
                new Position(Row.Middle, Col.Left),   // middle-left position
                new Position(Row.Top, Col.Left)       // top-left position
        };

        for (Position pos : nextMoveOrder) {
            if (currentBoard.isEmptyAt(pos)) { // i used isEmptyAt instead of getEmptyCells as in hint, because getEmptyCells would give us a list of all empty positions but isEmptyAt allows us to check each position for empty cell in Circe's order
                return pos;
            }
        }

        throw new IllegalStateException("No valid moves left for Circe player.");
    }
}
