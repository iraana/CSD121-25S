package lab6.controller;

import lab6.data.*;

/**
 *  FlashcardController connects the flashcard data with the user interface
 */
public class FlashcardController {
    private FlashcardDeck deck;

    /**
     * Constructs a FlashcardController for a given deck
     * @param deck The FlashcardDeck to be controlled
     */
    public FlashcardController(FlashcardDeck deck) {
        this.deck = deck;
    }

    /**
     * Gets the English word from the current flashcard.
     * @return The English word, or null if no current card exists
     */
    public String getCurrentEnglishWord() {
        Flashcard flashcard = deck.getCurrent();
        return flashcard != null ? flashcard.getEnglishWord() : null;
    }

    /**
     * Gets the Ukrainian word from the current flashcard.
     * @return The Ukrainian word, or null if no current card exists
     */
    public String getCurrentUkrainianWord() {
        Flashcard flashcard = deck.getCurrent();
        return flashcard != null ? flashcard.getUkrainianWord() : null;
    }

    /**
     * Checks whether the user's answer is correct
     * @param answer The answer provided by the user
     * @return true if the answer is correct; false otherwise
     */
    public boolean checkAnswer(String answer) {
        return deck.checkAnswer(answer);
    }

    /**
     * Moves to the next flashcard in the deck
     * @return true if there are more cards to show; false if at the end
     */
    public boolean showNextCard(){
        return deck.showNextCard();
    }

    /**
     * Checks whether all flashcards have been shown
     * @return true if all cards have been completed; false otherwise
     */
    public boolean isComplete() {
        return deck.isComplete();
    }

}
