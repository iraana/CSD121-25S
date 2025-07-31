package lab6.data;

/**
 * Class representing a flashcard used in the language learning app.
 * Each flashcard contains an English word and its corresponding Ukrainian translation.
 */

public class Flashcard {
    private String englishWord;
    private String ukrainianWord;

    /**
     * Constructs a Flashcard with the specified English and Ukrainian words.
     * @param englishWord The word in English
     * @param ukrainianWord The corresponding word in Ukrainian
     */
    public Flashcard(String englishWord, String ukrainianWord) {
        this.englishWord = englishWord;
        this.ukrainianWord = ukrainianWord;
    }

    /**
     * Gets the English word on the flashcard.
     * @return The English word
     */
    public String getEnglishWord() {
        return englishWord;
    }

    /**
     * Gets the Ukrainian word on the flashcard.
     * @return The Ukrainian word
     */
    public String getUkrainianWord() {
        return ukrainianWord;
    }
}
