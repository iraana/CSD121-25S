package lab4.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Position pos(Row r, Col c) {
        return new Position(r, c);
    }

    @Test
    void startingBoardIsEmpty() {
        Board board = new Board();
        assertFalse(board.isFull());
        assertEquals(Board.Status.InProgress, board.getStatus());
        assertFalse(board.isOccupiedAt(pos(Row.Top, Col.Left)));
        assertFalse(board.isOccupiedAt(pos(Row.Middle, Col.Right)));
        assertFalse(board.isOccupiedAt(pos(Row.Top, Col.Left)));
    }

    @Test
    void statusIsInProgress() {
        Board board = new Board();
        board.placeX(pos(Row.Top, Col.Middle));
        board.placeO(pos(Row.Bottom, Col.Middle));
        board.placeX(pos(Row.Bottom, Col.Right));
        assertEquals(Board.Status.InProgress, board.getStatus());
        assertFalse(board.isFull());
    }

    @Test
    void occupiedAt() {
        Board board = new Board();
        Position p = pos(Row.Top, Col.Left);
        Position p1 = pos(Row.Middle, Col.Left);
        assertFalse(board.isOccupiedAt(p));
        board.placeX(p);
        assertTrue(board.isOccupiedAt(p));
        assertFalse(board.isOccupiedAt(p1));
        board.placeO(p1);
        assertTrue(board.isOccupiedAt(p1));
    }

    @Test
    void isOccupiedAtThrowsExceptionForNull() {
        Board board = new Board();
        assertThrows(NullPointerException.class, () -> board.isOccupiedAt(null), "Invalid input");
    }

    @Test
    void placingXOTwiceInSamePositionFails() {
        Board board = new Board();
        Position p = pos(Row.Top, Col.Left);
        board.placeX(p);
        board.placeO(p);
        assertTrue(board.isOccupiedAt(p));
    }


    @Test
    void boardIsFullAfterNineMoves() {
        Board board = new Board();
        for(Row r : Row.values()) {
            for(Col c : Col.values()) {
                board.placeX(pos(r, c)); // I used only X because I just needed 9 cells filled doesn't metter if X or O
            }
        }
        assertTrue(board.isFull());
    }

    @Test
    void xWinsAcrossTopRow() {
        Board board = new Board();
        board.placeX(pos(Row.Top, Col.Left));
        board.placeX(pos(Row.Top, Col.Middle));
        board.placeX(pos(Row.Top, Col.Right));
        assertEquals(Board.Status.XWins, board.getStatus());
    }

    @Test
    void xWinsAcrossLeftColumn() {
        Board board = new Board();
        board.placeX(pos(Row.Top, Col.Left));
        board.placeX(pos(Row.Middle, Col.Left));
        board.placeX(pos(Row.Bottom, Col.Left));
        assertEquals(Board.Status.XWins, board.getStatus());
    }

    @Test
    void oWinsAcrossTopRow() {
        Board board = new Board();
        board.placeO(pos(Row.Top, Col.Left));
        board.placeO(pos(Row.Top, Col.Middle));
        board.placeO(pos(Row.Top, Col.Right));
        assertEquals(Board.Status.OWins, board.getStatus());
    }

    @Test
    void oWinsAcrossLeftColumn() {
        Board board = new Board();
        board.placeO(pos(Row.Top, Col.Left));
        board.placeO(pos(Row.Middle, Col.Left));
        board.placeO(pos(Row.Bottom, Col.Left));
        assertEquals(Board.Status.OWins, board.getStatus());
    }

    @Test
    void xWinsTopLeftToBottomRightDiagonal() {
        Board board = new Board();
        board.placeX(pos(Row.Top, Col.Left));
        board.placeX(pos(Row.Middle, Col.Middle));
        board.placeX(pos(Row.Bottom, Col.Right));
        assertEquals(Board.Status.XWins, board.getStatus());
    }

    @Test
    void oWinsTopRightToBottomLeftDiagonal() {
        Board board = new Board();
        board.placeO(pos(Row.Top, Col.Right));
        board.placeO(pos(Row.Middle, Col.Middle));
        board.placeO(pos(Row.Bottom, Col.Left));
        assertEquals(Board.Status.OWins, board.getStatus());
    }

    @Test
    void checkDraw() {
        Board board = new Board();

        board.placeX(pos(Row.Top, Col.Left));
        board.placeO(pos(Row.Top, Col.Middle));
        board.placeX(pos(Row.Top, Col.Right));

        board.placeX(pos(Row.Middle, Col.Left));
        board.placeX(pos(Row.Middle, Col.Middle));
        board.placeO(pos(Row.Middle, Col.Right));

        board.placeO(pos(Row.Bottom, Col.Left));
        board.placeX(pos(Row.Bottom, Col.Middle));
        board.placeO(pos(Row.Bottom, Col.Right));

        assertEquals(Board.Status.Draw, board.getStatus());
        assertTrue(board.isFull());
    }

    @Test
    void toStringHasThreeRowsWithXOAndDots() {
        Board board = new Board();
        board.placeX(pos(Row.Top, Col.Left));
        board.placeO(pos(Row.Middle, Col.Left));
        String result = board.toString();
        assertTrue(result.contains("X"));
        assertTrue(result.contains("O"));
        assertTrue(result.contains("."));
        assertEquals(3, result.split("\n").length);
    }


}
