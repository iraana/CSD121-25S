package lab3.game;

/**
 * This class represents two players in the game(X and O).
 */
public enum Player {
    X, O;

    // Returns the other player.
    // If this player is X, returns O and if this player is O, returns X.
    public Player next() {
        if (this == X) { // same here with 'this', it refers to the current player and just defines who X or O has to be next
            return O;
        } else {
            return X;
        }
    }

    // Returns the symbol for the player(X or O).
    public char getSymbol() {
        if (this == X) {
            // I used this here, because in Java 'this' is a keyword that refers to the current object.
            // The one that is calling the method or using the variable.
            // So here 'this' means the player that is using this method
            return 'X';
        } else {
            return 'O';
        }
    }
}