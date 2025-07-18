package lab5;

import lab5.game.*;
import lab5.players.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CircePlayerTest {

    @Test
    public void testPickCenterFirstIfAvailable() {
        CircePlayer circe = new CircePlayer("Circe");
        Board emptyBoard = new Board("---------");
        Position nextMove = circe.pickNextMove(emptyBoard);
        assertEquals(new Position(Row.Middle, Col.Middle), nextMove,"Circe should always pick the center position at first when available");
    }

    @Test
    public void testPickTopMiddleIfCenterTaken() {
        CircePlayer circe = new CircePlayer("Circe");
        Board board = new Board("----X----");
        Position nextMove = circe.pickNextMove(board);
        assertEquals(new Position(Row.Top, Col.Middle), nextMove, "Circe should pick top-middle when center is taken");
    }

    @Test
    public void testPickNextAvailableClockwise() {
        CircePlayer circe = new CircePlayer("Circe");
        Board board = new Board("-XO-X----");
        Position nextMove = circe.pickNextMove(board);
        assertEquals(new Position(Row.Middle, Col.Right), nextMove, "Circe should pick middle-right when center, top-middle and top-right are taken");
    }

    @Test
    public void testThrowsIfBoardIsFull() {
        CircePlayer circe = new CircePlayer("Circe");
        Board fullBoard = new Board("XOXOXOXOX");
        assertThrows(IllegalStateException.class, () -> {
            circe.pickNextMove(fullBoard);
        }, "Circe should throw an message when no moves are available");
    }


}
