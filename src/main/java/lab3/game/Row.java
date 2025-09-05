package lab3.game;

/**
 * Enum representing the three rows on a TicTacToe board.
 */
public enum Row {
    //Each row is associated with an index value : TOP = 0, MIDDLE = 1, BOTTOM = 2.
    TOP(0), MIDDLE(1), BOTTOM(2);

    //We will use those index values to get needed row in our bord array
    private final int index;

    Row(int index) {
        this.index = index;
    }

    //Returns the row index for use in our board array.
    public int getIndex() {
        return index;
    }

    //Converts a user input into the corresponding row.
    public static Row userInputForRow(int input) {
        if (input == 1) {
            return TOP;
        } else if (input == 2 ) {
            return MIDDLE;
        } else if (input == 3 ) {
            return BOTTOM;
        } else throw new IllegalArgumentException("Invalid row");
    }
}