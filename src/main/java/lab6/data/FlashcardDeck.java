package lab6.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages a deck of flashcards for the language learning app.
 */
public class FlashcardDeck {
    private List<Flashcard> flashcards;
    private int currentIndex;

    /**
     * Constructs a FlashcardDeck from a list of flashcards. The deck is shuffled to randomize the order.
     * @param flashcards List of flashcards in the deck
     */
    public FlashcardDeck(List<Flashcard> flashcards) {
        this.flashcards = new ArrayList<>(flashcards);
        Collections.shuffle(this.flashcards);
        this.currentIndex = 0;
    }

    /**
     * Returns the current flashcard from the deck.
     * @return The current Flashcard or null if all cards have been shown
     */
    public Flashcard getCurrent() {
        if (this.currentIndex < this.flashcards.size()) {
            return this.flashcards.get(currentIndex);
        }
        return null;
    }

    /**
     * Checks whether the given answer is correct
     * @param answer The user's answer to check
     * @return true if the answer matches the Ukrainian word; false otherwise
     */
    public boolean checkAnswer(String answer) {
        return getCurrent().getUkrainianWord().equalsIgnoreCase(answer.trim());
    }

    /**
     * Moves to the next flashcard in the deck.
     * @return true if there are more cards left; false if at the end of the deck
     */
    public boolean showNextCard(){
        currentIndex++;
        return currentIndex < this.flashcards.size();
    }

    /**
     * Checks whether all flashcards have been shown
     * @return true if the deck is complete; false otherwise
     */
    public boolean isComplete() {
        return this.currentIndex >= this.flashcards.size();
    }

}
