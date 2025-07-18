package lab5;

import lab5.game.*;
import lab5.players.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OmolaPlayerTest {

    @Test
    void testOmolaTakesWinningMove() {
        Board board = new Board("X-O-XO---");
        OmolaPlayer omola = new OmolaPlayer("Omola");
        Position nextMove = omola.pickNextMove(board);
        assertEquals(new Position(Row.Bottom, Col.Right), nextMove, "Omola should take the winning move at 3 3");
    }

    @Test
    void testOmolaBlocksOpponent() {
        Board board = new Board("XX-O-----");
        OmolaPlayer omola = new OmolaPlayer("Omola");
        Position nextMove = omola.pickNextMove(board);
        assertEquals(new Position(Row.Top, Col.Right), nextMove, "Omola should block opponent at 1 3 not to win");
    }

    @Test
    void testOmolaWinAndBlockMove() {
        Board board = new Board("XO-XO----");
        OmolaPlayer omola = new OmolaPlayer("Omola");
        Position nextMove = omola.pickNextMove(board);
        assertEquals(new Position(Row.Bottom, Col.Left), nextMove, "Omola should win at 3 1"); // because we check for win first and then for blocking opponent
    }

    @Test
    void testOmolaWithNoWinOrBlockMove() {
        Board board = new Board("X--O-----");
        OmolaPlayer omola = new OmolaPlayer("Omola");
        Position nextMove = omola.pickNextMove(board);
        assertEquals(new Position(Row.Top, Col.Middle), nextMove, "Omola should pick the first available move at 1 2");
    }

}
