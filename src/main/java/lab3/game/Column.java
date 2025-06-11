package lab3.game;


/**
 * Enum representing the three columns on a TicTacToe board.
 */
public enum Column {
    //Each column is associated with an index value : LEFT = 0, MIDDLE = 1, RIGHT = 2.
    LEFT(0), MIDDLE(1), RIGHT(2);

    //We will use those index values to get needed column in our bord array
    private final int index;

    Column(int index) {
        this.index = index;
    }

    //Returns the column index for use in our board array.
    public int getIndex() {
        return index;
    }
    //Converts a user input into the corresponding column.
    public static Column userInputForColumn(int input) {
        if (input == 1) {
            return LEFT;
        } else if (input == 2 ) {
            return MIDDLE;
        } else if (input == 3 ) {
            return RIGHT;
        } else throw new IllegalArgumentException("Invalid column");
    }
}