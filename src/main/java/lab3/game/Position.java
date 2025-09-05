package lab3.game;

/**
 * A class that represents a spot on the board using a Row and a Column.
 * It returns the row and column starting from 0 which match the positions in the board array.
 */

public class Position {
    private final Row row;
    private final Column col;

    /**
     * Constructs a Position with the given Row and Column.
     * @param row the row on the board (TOP, MIDDLE, BOTTOM)
     * @param col the column on the board (LEFT, MIDDLE, RIGHT)
     */
    public Position(Row row, Column col) {
        // here 'this.row' refers to the variable inside the class, and we're going to set it to the value passed into the constructor.
        this.row = row;
        this.col = col;
    }

    //Returns the index of the row.
    public int row() {
        return row.getIndex();
    }

    // Returns the index of the column.
    public int col() {
        return col.getIndex();
    }
}